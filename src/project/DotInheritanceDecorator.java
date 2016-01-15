package project;

import java.util.List;

public class DotInheritanceDecorator extends DotDecorator{
	
	ADot toBeDecorated;

	public DotInheritanceDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeInheritance(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"empty\"\n\tstyle = \"filled\"]\n\t");
		List<String> fileNames = getFileNames();
		for(ClassBuilder c : classes){
			if(c.superName != null){
				String left = c.name;
				String right = c.superName;
				if(includeAll){
					temp.append(addArrow(left, right));
				}else{
					if(fileNames.contains(right))
						temp.append(addArrow(left, right));
				}
			}
		}
		return temp;
	}
	
	private String addArrow(String left, String right){
		String[] leftSplit = left.split("/");
		String[] rightSplit = right.split("/");
		left = leftSplit[leftSplit.length-1];
		right = rightSplit[rightSplit.length-1];
		return " " + left + " -> " + right+"\n\t";
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeInheritance());
	}
}
