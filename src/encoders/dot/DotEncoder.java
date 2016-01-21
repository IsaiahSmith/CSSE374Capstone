package encoders.dot;

import java.util.List;

import encoders.IEncoder;
import model.IModel;


public class DotEncoder implements IEncoder {
	
	@Override
	public StringBuilder encode(IModel model, boolean includeAll){
		ADot dot = new StandardDot(includeAll);
		dot = new DotClassDecorator(dot, model);
		dot = new DotImplementsDecorator(dot, model);
		dot = new DotInheritanceDecorator(dot, model);
		dot = new DotUsesDecorator(dot, model);
		dot = new DotAssociationDecorator(dot, model);
		return dot.getDot().append("\n}");
	}
	
	

}
