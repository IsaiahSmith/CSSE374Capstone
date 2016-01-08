package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DotUsesDecorator extends DotDecorator {

	ADot toBeDecorated;
	private List<ClassBuilder> classes;
	
	public DotUsesDecorator(ADot toBeDecorated, List<ClassBuilder> classes) {
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeUses() {
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"vee\" \n\t\tstyle= \"dashed\"\n\t]\n\t");
		
		for(ClassBuilder c : classes){
			if(c.methods != null){
				for(Map<String, String> m : c.methods) {
					if(c.arguments != null){
						for(Map<String, List<String>> arg : c.arguments){
							List<String> listArgTypes = arg.get(m.get("Name"));
							
						}
						
					}
				}
			}
			
			
			
//			Set<String> argTypes = new HashSet<String>();
//			for(String s : argTypes) {
//				String right = s;
//				String[] rightSplit = right.split("\\.");
//				right = rightSplit[rightSplit.length-1];
//				temp.append(c.name + "->" + right + "\n\t");
//			}
		}
		
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeUses());
	}
	

}
