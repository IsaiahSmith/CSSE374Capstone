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
	public void addName(String name) {
		this.Name = name;
	}

	@Override
	public void addType(String type) {
		this.Type = type;
	}
	
	@Override
	public void addAccessLevel(String accessLevel) {
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
		return this.AccessLevel + " " + this.Type + " " + this.Name;
	}
}
