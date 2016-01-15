package project;

import java.util.List;

public class DotEncoder implements IEncoder {
	
	@Override
	public StringBuilder encode(List<ClassBuilder> classes){
		ADot dot = new StandardDot();
		dot = new DotClassDecorator(dot, classes);
		dot = new DotImplementsDecorator(dot, classes);
		dot = new DotInheritanceDecorator(dot, classes);
		//dot = new DotUsesDecorator(dot, classes);
		dot = new DotAssociationDecorator(dot, classes);
		return dot.getDot().append("\n}");
	}
	
	

}
