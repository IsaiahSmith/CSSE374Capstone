package encoders;

import model.IModel;

public interface IEncoder {
	public StringBuilder encode(IModel model, boolean includeAll);
}
