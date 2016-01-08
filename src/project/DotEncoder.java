package project;

import java.util.List;

public class DotEncoder implements IEncoder {

	@Override
	public StringBuilder encode(List<ClassBuilder> Classes){
		IDot dot = new StandardDot();
		//dot = new DotClassDecorator(dot, Classes);
		//dot = new DotInheritanceDecorator(dot, /*Inheritance graph*/);
		//dot = new DotImplementsDecorator(dot, /*Implements graph*/);
		return dot.getDot();
	}
	

}
