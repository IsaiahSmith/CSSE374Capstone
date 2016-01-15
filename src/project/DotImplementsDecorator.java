package project;

import java.util.List;

public class DotImplementsDecorator extends DotDecorator {
	ADot toBeDecorated;

	public DotImplementsDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeImplements(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"empty\" \n\t\tstyle= \"dashed\"\n\t]\n\t");
		List<String> fileNames = getFileNames();
		for(ClassBuilder c : classes){
			if(c.inter != null){
				for(String i : c.inter){
					String left = c.name;
					String right = i;
					if(includeAll) {
						temp.append(addArrow(left, right));
					} else {
						if(fileNames.contains(right))
							temp.append(addArrow(left, right));
					}
				}
			}
		}
		return temp;
	}
	
	private String addArrow(String left, String right) {
		String[] leftSplit = left.split("/");
		String[] rightSplit = right.split("/");
		left = leftSplit[leftSplit.length-1];
		right = rightSplit[rightSplit.length-1];
		return left + "->" + right + "\n\t";
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeImplements());
	}
}
