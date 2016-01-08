package project;

import java.util.List;

public class DotInheritanceDecorator extends DotDecorator{
	
	ADot toBeDecorated;
	private List<ClassBuilder> classes;

	public DotInheritanceDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeInheritance(){
		StringBuilder temp = new StringBuilder("edge [ arrowhead = 'empty']");

		for(ClassBuilder c : classes){
			if(c.superName != null){
				temp.append(" " + c.name + " -> " + c.superName);
			}
		}
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeInheritance());
	}
}
