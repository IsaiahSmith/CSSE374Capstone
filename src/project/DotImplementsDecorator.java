package project;

import java.util.List;

public class DotImplementsDecorator extends DotDecorator {
	ADot toBeDecorated;
	private List<ClassBuilder> classes;

	public DotImplementsDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeImplements(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"empty\" \n\t\tstyle= \"dashed\"\n\t]\n\t");
	
		for(ClassBuilder c : classes){
			if(c.inter != null){
				for(String i : c.inter){
					String left = c.name;
					String right = i;
					String[] leftSplit = left.split("/");
					String[] rightSplit = right.split("/");
					left = leftSplit[leftSplit.length-1];
					right = rightSplit[rightSplit.length-1];
					temp.append(left + "->" + right + "\n\t");
				}
			}
		}
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeImplements());
	}
}
