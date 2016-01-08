package project;

import java.util.List;

public class DotAssociationDecorator extends DotDecorator {

	ADot toBeDecorated;
	private List<ClassBuilder> classes;
	
	public DotAssociationDecorator(ADot toBeDecorated, List<ClassBuilder> classes) {
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeAssociation(){
		StringBuilder temp = new StringBuilder("");
		
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeAssociation());
	}

}
