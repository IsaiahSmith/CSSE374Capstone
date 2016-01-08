package project;

import java.util.List;

public class DotInheritanceDecorator extends DotDecorator{
	
	private List<ClassBuilder> classes;
	private StringBuilder stringBuild;

	public DotInheritanceDecorator(IDot toBeDecorated, List<ClassBuilder> classes){
		super(toBeDecorated);
		this.classes = classes;
		this.stringBuild = new StringBuilder();
		makeInheritance();
	}
	
	public void makeInheritance(){
		stringBuild.append("edge [ arrowhead = 'empty']");
		for(ClassBuilder c : classes){
			if(c.superName != null){
				stringBuild.append(" " + c.name + " -> " + c.superName);
			}
		}
	}

	@Override
	public StringBuilder getDot() {
		return stringBuild;
	}
}
