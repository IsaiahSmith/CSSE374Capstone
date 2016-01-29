package encoders.dot;

import java.util.List;

import encoders.IEncoder;
import model.IModel;


public class DotEncoder implements IEncoder {
	
	
	@Override
	public StringBuilder encode(IModel model){
		Dot dot = new StandardDot();
		dot = new DotFileDecorator(dot, model);
		dot = new DotArrowDecorator(dot, model);
		return new StringBuilder(dot.getDot().append("}").toString().replaceAll("\\.", "_").replaceAll("/", "_"));
	}
	
	

}
