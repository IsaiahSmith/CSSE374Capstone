package project;

import java.util.List;
import java.util.Map;

public class DotAssociationDecorator extends DotDecorator {

	ADot toBeDecorated;
	private List<ClassBuilder> classes;
	
	public DotAssociationDecorator(ADot toBeDecorated, List<ClassBuilder> classes) {
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeAssociation(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"vee\" \n\tstyle = \"filled\"]\n\t");
		for(ClassBuilder c : classes){
			for(Map<String, String> field : c.fields){
				if(field.get("Type").getClass().isLocalClass()){
					String left = c.name;
					String right = field.get("Type");
					String[] leftSplit = left.split("/");
					String[] rightSplit = right.split("/");
					left = leftSplit[leftSplit.length - 1];
					right = rightSplit[rightSplit.length - 1];
					String[] periodSplit = right.split("\\.");
					right = periodSplit[periodSplit.length - 1];
					temp.append(" " + left + " -> " + right+"\n\t");					
				}
			}
		}
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeAssociation());
	}

}
