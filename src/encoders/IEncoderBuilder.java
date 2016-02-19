package encoders;

public interface IEncoderBuilder {
	public IEncoder Build(String encoder);
	public void addStyles(/*Options format*/);
}
