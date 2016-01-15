package project;

import model.IModel;

public class TextEncoder implements IEncoder {

	@Override
	public StringBuilder encode(IModel model, boolean includeAll) {
		StringBuilder str = new StringBuilder();
		return str.append(model.toString());
	}

}
