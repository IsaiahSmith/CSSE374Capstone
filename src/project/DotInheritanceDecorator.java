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
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"empty\"\n\t]\n\t");

		for(ClassBuilder c : classes){
			if(c.superName != null){
				String left = c.name;
				String right = c.superName;
				String[] leftSplit = left.split("/");
				String[] rightSplit = right.split("/");
				left = leftSplit[leftSplit.length-1];
				right = rightSplit[rightSplit.length-1];
				temp.append(" " + left + " -> " + right+"\n\t");
			}
		}
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeInheritance());
	}
}
