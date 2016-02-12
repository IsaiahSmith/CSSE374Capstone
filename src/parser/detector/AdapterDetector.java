package parser.detector;

import java.util.ArrayList;
import java.util.List;

import model.IArrow;
import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.INode;
import model.IPattern;
import nodes.Arrow;
import nodes.Pattern;

public class AdapterDetector extends Detector{
	public static String PATTERN = "Adapter";
	private List<IPattern> patterns;
	
	public AdapterDetector(List<IFile> files) {
		this.files = files;
		this.patterns = new ArrayList<IPattern>();
	}
	
	@Override
	public List<IPattern> detect() {
		detectAdapter();
//		System.out.println("check patterns");
		return patterns;
	}
	
	public void detectAdapter() {
		for(IFile file : this.files) {
			for(IMethod method : file.getMethods()) {
				for(INode arg:method.getArgs()) {
					for(IInnerNode field : file.getFields()) {
						if(sanitize(arg.getType()).equals(sanitize(field.getType()))) {
							
							
							IFile adaptee = null;
							for(IFile temp:this.files) {
								if(sanitize(temp.getName()).equals(sanitize(field.getType()))) {
									adaptee = temp;
									break;
								}
							}
							boolean checkOne = false;
							IFile target = null;
							int EItotal = 0;
							int Atotal = 0;
							
							if(adaptee != null && file != null) {
								
//								System.out.println("Adaptee: "+adaptee.getName()+"\nAdapter: "+file.getName()+"\n");
								target = findTarget(file, adaptee, true);
								//System.out.println("Target: "+target.getName());
								
								if(target != null) {
									// loop through files and for each file that extends or implements file, add one to EItotal
									for(IFile temp : files) {
										if(sanitize(temp.getName()).equals(sanitize(file.getSuperName()))) {
											EItotal++;
										}
										for(IFile fileInfaze:file.getInterfaces()) {
											if(sanitize(fileInfaze.getName()).equals(sanitize(temp.getName()))) {
												EItotal++;
											}
										}
										
										// if the temp file contains a field of type file
										for(IInnerNode fileField:file.getFields()) {
											if(sanitize(fileField.getType()).equals(sanitize(temp.getName()))) {
												Atotal++;
											}
										}
									}
									
									if(EItotal == 1 && Atotal == 1) {
										checkOne = true;
									}
								}
							}
							
//							System.out.println("EItotal: "+EItotal);
//							System.out.println("Atotal: "+Atotal);
							
							boolean checkTwo = false;
							
							if(checkOne) {
								
//								System.out.println(file.getName()+" Has made it past check one!");
								
								for(IMethod m:file.getMethods()) {
									if(!m.getName().equals("init")) {
										
										for(IMethod innerMethod:m.getInnerMethods()) {
											if(sanitize(innerMethod.getClassName()).equals(sanitize(field.getType()))) {
												checkTwo = true;
											}
										}
									}
								}
							}
							
							if(checkTwo) {
//								System.out.println(file.getName()+" Has made it past check two!");
								
								IPattern pAdapter = new Pattern("Adapter");
								IPattern pAdaptee = new Pattern("Adapter:Adaptee");
								IPattern pTarget = new Pattern("Adapter:Target");
								IArrow adapts =  new Arrow();
								
								pAdapter.setNode(file.getName());
								adapts.setOrigin(sanitize(file.getName()));
								adapts.setEnd(sanitize(adaptee.getName()));
								adapts.setType("Association");
								pAdapter.setArrow(adapts);
								
								pAdaptee.setNode(adaptee.getName());
								
								pTarget.setNode(target.getName());
								
								patterns.add(pAdapter);
								patterns.add(pAdaptee);
								patterns.add(pTarget);
							}
						}
					}
				}
			}
		}
	}

	private IFile findTarget(IFile adapter, IFile adaptee, boolean base) {
		if((adapter.getSuperName().equals("") || sanitize(adapter.getSuperName()).equals("java_lang_Object"))
				&& adapter.getInterfaces().isEmpty() && base) {
			return null;
		} else if(adapter.getSuperName().equals("") || sanitize(adapter.getSuperName()).equals("java_lang_Object")) {
			
			if(adapter.getInterfaces().size() != 1) {
				return adapter;
			} else {
				IFile infaze = adapter.getInterfaces().get(0);
				if(!sanitize(infaze.getName()).equals(sanitize(adaptee.getName()))) {
					
					IFile target = null;
					for(IFile temp:files) {
						if(sanitize(temp.getName()).equals(sanitize(infaze.getName()))) {
							target = temp;
							break;
						}
					}
					
					if(target!=null) {
						if(findTarget(target, adaptee, false) != null) {
							return target;
						} else {
							return null;
						}
					}
				}
			}
		} else if(adapter.getInterfaces().isEmpty()) {
			if(!sanitize(adapter.getSuperName()).equals(sanitize(adaptee.getName()))) {
				IFile target = null;
				for(IFile temp:files) {
					if(sanitize(adapter.getSuperName()).equals(sanitize(temp.getName()))) {
						target = temp;
					}
				}
				
				if(target != null) {
					if(findTarget(target, adaptee, false) != null) {
						return target;
					} else {
						return null;
					}
				}
			}
		}
		
		
		return null;
	}
	
}
