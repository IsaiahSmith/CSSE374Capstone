package encoders.text;

import encoders.IEncoder;
import model.IModel;

public class TextEncoder implements IEncoder {

	@Override
	public StringBuilder encode(IModel model) {
		StringBuilder str = new StringBuilder();
		return str.append(model.toString());
	}

}
