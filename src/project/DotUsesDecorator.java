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
		StringBuilder temp = new StringBuilder("");
		
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeUses());
	}
	

}
