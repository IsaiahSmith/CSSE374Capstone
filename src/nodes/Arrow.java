package nodes;

import model.IArrow;

public class Arrow implements IArrow {
	private String type;
	private String origin;
	private String end;
	
	public Arrow(){
		this.type = "";
		this.origin = "";
		this.end = "";
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

	@Override
	public boolean equals(IArrow arrow) {
		if(this.origin.equals(arrow.getOrigin()) && 
				this.end.equals(arrow.getEnd()) &&
				this.type.equals(arrow.getType())){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		if(this.equals(new Arrow()))
			return "<NULL_ARROW>";
		String str = "";
		str += this.type;
		str += ": ";
		str += this.origin;
		str += " -> ";
		str += this.end;
		return str;
	}
}
