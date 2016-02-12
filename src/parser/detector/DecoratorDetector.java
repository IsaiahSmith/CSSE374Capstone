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
import nodes.FileNode;
import nodes.Pattern;

public class DecoratorDetector extends Detector{
	public static String PATTERN = "Decorator";
	private List<IPattern> decorators;
	
	public DecoratorDetector(List<IFile> files) {
		this.files = files;
		this.decorators = new ArrayList<IPattern>();
	}
	
	@Override
	public List<IPattern> detect() {
		findDecorator();		
		
		return this.decorators;
	}
	
//	public String findDecoratorAndComponent(IFile file) {
//		
//		// aggregate a list of names of the classes superName and all interfaces
//		// check to see if has a constructor whos arguments are that class or interface
//		// check to see if the file has any methods in common with its component
//		// if it does, make sure the function is deferred
//		// after that final check, add the decorator detected and its component to the patterns list
//		// return something that will allow looping through 
//		return null;
//	}
	//given a supposed decorator and component pair

	private void findDecorator() {
		for(IFile file : this.files) {
			for(IMethod method : file.getMethods()) {
				for(INode arg:method.getArgs()){
					for(IInnerNode field : file.getFields()) {
						if(sanitize(arg.getType()).equals(sanitize(field.getType())) /*&& !field.IsCollection()*/){
							
							IFile component = null;
							for(IFile temp : files) {
								if(sanitize(temp.getName()).equals(sanitize(field.getType()))) {
									component = temp;
								}
							}
							
							boolean isDecorator = false;
							if(component != null) {
								isDecorator = checkComponent(file, component);
								
							}
							if(isDecorator) {
//								System.out.println("Stop, what is going on");
								//run function that adds components and decorators and all that stuff
								
								IPattern decorator = new Pattern("Decorator");
								// guess we are adding duplicates?
								IPattern decorated = new Pattern("Decorator:Component");
								IArrow decorates = new Arrow();
								decorates.setOrigin(sanitize(file.getName()));
								decorates.setEnd(sanitize(component.getName()));
								decorates.setType("Association"); // Don't know if this is the right type, it is the one depicted on the milestone page
								decorator.setArrow(decorates);
								decorator.setNode(file.getName());
								decorated.setNode(component.getName());
								
								this.decorators.add(decorated);
								this.decorators.add(decorator);
								
								for(IFile child : files) {
									if(sanitize(child.getSuperName()).equals(sanitize(file.getName()))) {
										//screw it I'm just adding all children.
										IPattern childDecorator = new Pattern("Decorator");
										childDecorator.setNode(child.getName());
										this.decorators.add(childDecorator);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean checkComponent(IFile dec, IFile component) {
		
		// first preform sanity check and make sure this is a decorator.
		if(sanitize(dec.getSuperName()).equals("") && dec.getInterfaces().isEmpty()) {
			return false;
		} else if (sanitize(dec.getSuperName()).equals("")) { // statement for the standard decorator
			for(IFile infaze : dec.getInterfaces()) {
				if(sanitize(infaze.getName()).equals(sanitize(component.getName()))) {
					IPattern decorator = new Pattern("Decorator");
					IPattern decorated = new Pattern("Decorator:Component");
					IArrow decorates = new Arrow();
					decorates.setOrigin(sanitize(dec.getName()));
					decorates.setEnd(sanitize(component.getName()));
					decorates.setType("Association"); // Don't know if this is the right type, it is the one depicted on the milestone page
					decorator.setArrow(decorates);
					decorator.setNode(dec.getName());
					decorated.setNode(component.getName());
					
					this.decorators.add(decorated);
					this.decorators.add(decorator);
					
					return true;
				}
			}
			
			for(IFile infaze : dec.getInterfaces()) {
				// Check to see if files contains this interface
				IFile nextDec = null;
				for(IFile f : this.files) {
					if(sanitize(f.getName()).equals(sanitize(infaze.getName()))) {
						nextDec = f;
					}
				}
				
				if(nextDec != null) {
					if(checkComponent(nextDec, component)) {
						IPattern decorator = new Pattern("Decorator");
						// guess we are adding duplicates?
						IPattern decorated = new Pattern("Decorator:Component");
						IArrow decorates = new Arrow();
						decorates.setOrigin(sanitize(nextDec.getName()));
						decorates.setEnd(sanitize(component.getName()));
						decorates.setType("Association"); // Don't know if this is the right type, it is the one depicted on the milestone page
						decorator.setArrow(decorates);
						decorator.setNode(nextDec.getName());
						decorated.setNode(component.getName());
						
						this.decorators.add(decorated);
						this.decorators.add(decorator);
						
						return true;
					}
				}
			}
		} else if (dec.getInterfaces().isEmpty()) {
			if(sanitize(dec.getSuperName()).equals(sanitize(component.getName()))) {
				return true;
			} else {
				IFile superClass = null;
				for(IFile f : files) {
					if(sanitize(f.getName()).equals(sanitize(dec.getSuperName()))) {
						superClass = f;
						break;
					}
				}
				
				if(superClass != null) {
					if(checkComponent(superClass, component)) {
						IPattern decorator = new Pattern("Decorator");
						// guess we are adding duplicates?
						IPattern decorated = new Pattern("Decorator:Component");
						IArrow decorates = new Arrow();
						decorates.setOrigin(sanitize(superClass.getName()));
						decorates.setEnd(sanitize(component.getName()));
						decorates.setType("Association"); // Don't know if this is the right type, it is the one depicted on the milestone page
						decorator.setArrow(decorates);
						decorator.setNode(superClass.getName());
						decorated.setNode(component.getName());
						
						this.decorators.add(decorated);
						this.decorators.add(decorator);
						
						return true;
					}
				}
			}
		} else {
			if(sanitize(dec.getSuperName()).equals(sanitize(component.getName()))) {
				return true;
			} else {
				IFile superClass = null;
				for(IFile f : this.files) {
					if(sanitize(f.getName()).equals(sanitize(dec.getSuperName()))) {
						superClass = f;
					}
				}
				
				if(superClass != null) {
					if(checkComponent(superClass, component)) {
						IPattern decorator = new Pattern("Decorator");
						// guess we are adding duplicates?
						IPattern decorated = new Pattern("Decorator:Component");
						IArrow decorates = new Arrow();
						decorates.setOrigin(sanitize(superClass.getName()));
						decorates.setEnd(sanitize(component.getName()));
						decorates.setType("Association"); // Don't know if this is the right type, it is the one depicted on the milestone page
						decorator.setArrow(decorates);
						decorator.setNode(superClass.getName());
						decorated.setNode(component.getName());
						
						this.decorators.add(decorated);
						this.decorators.add(decorator);
						
						return true;
					}
				}
			}
			
			for(IFile infaze : dec.getInterfaces()) {
				if(sanitize(infaze.getName()).equals(sanitize(component.getName()))) {
					return true;
				}
			}
			
			for(IFile infaze : dec.getInterfaces()) {
				IFile nextDec = null;
				for(IFile f : this.files) {
					if(sanitize(f.getName()).equals(sanitize(infaze.getName()))) {
						nextDec = f;
					}
				}
				
				if(nextDec != null){
					if(checkComponent(nextDec, component)) {
						
						IPattern decorator = new Pattern("Decorator");
						// guess we are adding duplicates?
						IPattern decorated = new Pattern("Decorator:Component");
						IArrow decorates = new Arrow();
						decorates.setOrigin(sanitize(nextDec.getName()));
						decorates.setEnd(sanitize(component.getName()));
						decorates.setType("Association"); // Don't know if this is the right type, it is the one depicted on the milestone page
						decorator.setArrow(decorates);
						decorator.setNode(nextDec.getName());
						decorated.setNode(component.getName());
						
						this.decorators.add(decorated);
						this.decorators.add(decorator);
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
}
