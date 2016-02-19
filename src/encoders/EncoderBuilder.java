package encoders;

import java.util.HashMap;
import java.util.Map;

import encoders.dot.DotEncoder;
import encoders.sdedit.SDEditEncoder;
import encoders.text.TextEncoder;

public class EncoderBuilder implements IEncoderBuilder {

	Map<String, IEncoder> encoders;
	
	public EncoderBuilder() {
		this.encoders = new HashMap<String, IEncoder>();
		this.encoders.put("DotEncoder", new DotEncoder());
		this.encoders.put("TextEncoder", new TextEncoder());
		this.encoders.put("SDEditEncoder", new SDEditEncoder());
	}
	
	@Override
	public IEncoder Build(String encoder) {
		
		return this.encoders.get(encoder);
	}

	@Override
	public void addStyles() {
		// TODO Auto-generated method stub

	}

}
