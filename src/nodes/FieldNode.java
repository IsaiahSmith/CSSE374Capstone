package nodes;

import model.IInnerNode;

public class FieldNode implements IInnerNode {

	private String Name;
	private String Type;
	private String AccessLevel;
	
	public FieldNode() {
		this.Name = new String();
		this.Type = new String();
		this.AccessLevel = new String();
	}
	
	@Override
	public void setName(String name) {
		this.Name = name;
	}

	@Override
	public void setType(String type) {
		this.Type = type;
	}
	
	@Override
	public void setAccessLevel(String accessLevel) {
		this.AccessLevel = accessLevel;
	}

	@Override
	public String getType() {
		return this.Type;
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public String getAccessLevel() {
		return this.AccessLevel;
	}

	@Override
	public String toString() {
		String[] typeSplit = this.Type.split("_");
		return this.AccessLevel + " " + typeSplit[typeSplit.length-1] + " " + this.Name;
	}
}
