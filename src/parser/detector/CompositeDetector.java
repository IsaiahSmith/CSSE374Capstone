package parser.detector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.INode;
import model.IPattern;
import nodes.Pattern;

public class CompositeDetector extends Detector {
	public static String PATTERN = "Composite";
	private ArrayList<IPattern> patterns;

	public CompositeDetector(List<IFile> files) {
		this.files = files;
		this.patterns = new ArrayList<IPattern>();
	}

	@Override
	public List<IPattern> detect() {
		findComposite();
		return this.patterns;
	}

	private void findComposite() {
		for (IFile file : this.files) {
			if (!file.getSuperName().equals("java/lang/Object")) {
				// file has a super, check if it has a field with collection of
				// that super
				List<IFile> superInterfaces = getSuperInterfaces(sanitize(file.getSuperName()));
				if(superInterfaces != null){
					// super has interfaces
					List<String> superIntfNames = new ArrayList<String>();
					for(IFile intrf : superInterfaces) {
						superIntfNames.add(sanitize(intrf.getName()));
					}
					for (IInnerNode field : file.getFields()) {
						if (field.getSig() != null) {
							String[] split = field.getSig().split("\\.");
							String innerType = split[split.length - 1];
							String[] superSplit = file.getSuperName().split("\\/");
							String superName = superSplit[superSplit.length - 1];
							// actually want to check if has collection with super or super's interfaces
							if (superName.equals(innerType)
									|| (checkDelegated(file, superIntfNames)
									&& checkKeyOperations(file, superIntfNames))) {
								// check if methods are delegated
								IPattern composite = new Pattern("Composite");
								composite.setNode(file.getName());
								this.patterns.add(composite);

								IPattern component = new Pattern("Composite:Component");
								component.setNode(file.getSuperName());
								this.patterns.add(component);

								addLeafs(file.getSuperName(), file, superIntfNames);
							}
						}
					}
				}else{
					for (IInnerNode field : file.getFields()) {
						if (field.getSig() != null) {
							String[] split = field.getSig().split("\\.");
							String innerType = split[split.length - 1];
							String[] superSplit = file.getSuperName().split("\\/");
							String superName = superSplit[superSplit.length - 1];
							// actually want to check if has collection with super
							if (superName.equals(innerType) 
									&& checkDelegated(file, null)
									&& checkKeyOperations(file, null)) {
								// check if methods are delegated
								IPattern composite = new Pattern("Composite");
								composite.setNode(file.getName());
								this.patterns.add(composite);
								
								IPattern component = new Pattern("Composite:Component");
								component.setNode(file.getSuperName());
								this.patterns.add(component);
								
								addLeafs(file.getSuperName(), file, null);
							}
						}
					}
				}
			} else {
				// file has no super class, move on

			}
		}
	}


	private List<IFile> getSuperInterfaces(String superName) {
		List<IFile> result = new ArrayList<IFile>();
		for(IFile potentialSuper : this.files) {
			if(sanitize(potentialSuper.getName()).equals(superName)) {
				IFile actualSuper = potentialSuper;
//				System.out.println("Super w/ interfaces Name: "+sanitize(actualSuper.getName()));
//				System.out.println("Super's interfaces: "+actualSuper.getInterfaces());
				if(actualSuper.getSuperName() != null) {
					for(IFile potSuper : this.files) {
						if(sanitize(potSuper.getName()).equals(sanitize(actualSuper.getSuperName()))) {
							result.add(potSuper);
						}
					}
				}
				result.addAll(actualSuper.getInterfaces());
				return result;
			}
		}
		return null;		
	}

	private void addLeafs(String superName, IFile file, List<String> intfNames) {
		for (IFile potentialLeaf : files) {
			if ((potentialLeaf.getSuperName().equals(superName) 
					&& !potentialLeaf.equals(file) 
					&& !checkDelegated(potentialLeaf, intfNames)
					&& checkKeyOperations(potentialLeaf, intfNames))
					|| potentialLeaf.getSuperName().equals(sanitize(file.getSuperName()))) {
				IPattern leaf = new Pattern("Composite:Leaf");
				leaf.setNode(potentialLeaf.getName());
				this.patterns.add(leaf);
			}
		}
	}

	private boolean checkDelegated(IFile file, List<String> intfNames) {
		List<IMethod> delegated = new ArrayList<IMethod>();
		if(intfNames == null) {
			for(IFile potentialSuper: files) {
				if(sanitize(potentialSuper.getName()).equals(sanitize(file.getSuperName()))) {
					IFile actualSuper = potentialSuper;
					List<IMethod> superMethods = actualSuper.getMethods();
					for(IMethod method : superMethods) {
						List<INode> args = method.getArgs();
						for(INode arg : args) {
							if(sanitize(arg.getType()).equals(sanitize(actualSuper.getName()))
									|| sanitize(method.getType()).equals(sanitize(actualSuper.getName()))) {
								delegated.add(method);
							}
						}
					}
				}
			}
			for(IMethod m : delegated) {
				if(holds(m, file.getMethods()) == false){
					return false;
				}
			}
			return true;
		}else{
			for(IFile potentialSuper: files) {
				if(sanitize(potentialSuper.getName()).equals(sanitize(file.getSuperName()))) {
					IFile actualSuper = potentialSuper;
					List<IMethod> superMethods = actualSuper.getMethods();
					for(IMethod method : superMethods) {
						List<INode> args = method.getArgs();
						for(INode arg : args) {
							if(sanitize(arg.getType()).equals(sanitize(actualSuper.getName()))
									|| sanitize(method.getType()).equals(sanitize(actualSuper.getName()))
									|| intfNames.contains(sanitize(arg.getType()))
									|| intfNames.contains(sanitize(method.getType()))) {
								delegated.add(method);
							}
						}
					}
				}
			}
			for(IMethod m : delegated) {
				if(holds(m, file.getMethods()) == false){
					return false;
				}
			}
			return true;
		}
	}
	
	private boolean checkKeyOperations(IFile file, List<String> intfNames) {
		List<IMethod> keyOps = new ArrayList<IMethod>();
		if(intfNames == null) {
			for(IFile potentialSuper: files) {
				if(sanitize(potentialSuper.getName()).equals(sanitize(file.getSuperName()))) {
					IFile actualSuper = potentialSuper;
					List<IMethod> superMethods = actualSuper.getMethods();
					for(IMethod method : superMethods) {
						List<INode> args = method.getArgs();
						for(INode arg : args) {
							if(!sanitize(arg.getType()).equals(sanitize(actualSuper.getName()))
									&& !sanitize(method.getType()).equals(sanitize(actualSuper.getName()))) {
								keyOps.add(method);
							}
						}
					}
				}
			}
			for(IMethod m : keyOps) {
				if(holds(m, file.getMethods()) == false){
					return false;
				}
			}
			return true;
		}else {
			for(IFile potentialSuper: files) {
				if(sanitize(potentialSuper.getName()).equals(sanitize(file.getSuperName()))) {
					IFile actualSuper = potentialSuper;
					List<IMethod> superMethods = actualSuper.getMethods();
					for(IMethod method : superMethods) {
						List<INode> args = method.getArgs();
						for(INode arg : args) {
							if(!sanitize(arg.getType()).equals(sanitize(actualSuper.getName()))
									&& !sanitize(method.getType()).equals(sanitize(actualSuper.getName()))
									&& !intfNames.contains(sanitize(arg.getType()))
									&& !intfNames.contains(sanitize(method.getType()))
									&& !method.getName().equals("<init>")
									&& method.getVisibility().equals("public")) {
								keyOps.add(method);
							}
						}
					}
				}
			}
			for(IMethod m : keyOps) {
				if(holds(m, file.getMethods()) == false){
					return false;
				}
			}
			return true;
		}
	}
	
	private boolean holds(IMethod m, List<IMethod> methods) {
		for(IMethod method : methods) {
			if(method.toString().equals(m.toString())){
				return true;
			}
		}
		return false;
	}
	
}
