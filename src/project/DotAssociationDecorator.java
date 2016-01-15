package project;

import java.util.List;
import java.util.Map;

public class DotAssociationDecorator extends DotDecorator {

	ADot toBeDecorated;
	
	public DotAssociationDecorator(ADot toBeDecorated, List<ClassBuilder> classes) {
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeAssociation(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"vee\" \n\tstyle = \"filled\"]\n\t");
		List<String> fileNames = getFileNames();
		for(ClassBuilder c : classes){
			for(Map<String, String> field : c.fields){
				String left = c.name;
				String right = field.get("Type");
				right = right.replaceAll("\\.", "/");
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
		left = leftSplit[leftSplit.length - 1];
		right = rightSplit[rightSplit.length - 1];
		return " " + left + " -> " + right+"\n\t";
	}
	
	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeAssociation());
	}

}
