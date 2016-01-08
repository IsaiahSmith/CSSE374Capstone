package project;

import java.util.List;

public class DotClassDecorator extends DotDecorator {
	
	private List<ClassBuilder> classes;

	public DotClassDecorator(IDot toBeDecorated, List<ClassBuilder> classes){
		super(toBeDecorated);
		this.classes = classes;
	}
	
	public StringBuilder makeClasses(){
		
		return null;
	}
}
