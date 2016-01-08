package project;

import java.util.List;

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
				//TODO
			}
		}
		
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeUses());
	}
	

}
