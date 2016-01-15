package nodes;

import model.INode;

public class ArgumentNode implements INode {
	private String Name;
	private String Type;
	
	public ArgumentNode() {
		this.Name = new String();
		this.Type = new String();
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
	public String getName() {
		return this.Name;
	}

	@Override
	public String getType() {
		return this.Type;
	}

}
