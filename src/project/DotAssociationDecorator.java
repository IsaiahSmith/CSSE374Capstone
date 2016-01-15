package project;

import java.util.List;
import java.util.Map;

import org.objectweb.asm.Type;

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
				String left = c.name;
				String right = field.get("Type");
				String[] leftSplit = left.split("/");
				String[] rightSplit = right.split("/");
				left = leftSplit[leftSplit.length - 1];
				right = rightSplit[rightSplit.length - 1];
				try {
					Class<?> rightClass = Class.forName(right);
					if(!rightClass.toString().split(" ")[1].split("\\.")[0].equals("java")){
						String[] periodSplit = right.split("\\.");
						right = periodSplit[periodSplit.length - 1];
						temp.append(" " + left + " -> " + right+"\n\t");											
					}
				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
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
