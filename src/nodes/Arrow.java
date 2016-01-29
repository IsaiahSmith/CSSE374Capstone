package nodes;

import model.IArrow;

public class Arrow implements IArrow {
	private String type;
	private String origin;
	private String end;
	
	public Arrow(){
		
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getOrigin() {
		return this.origin;
	}

	@Override
	public String getEnd() {
		return this.end;
	}
}
