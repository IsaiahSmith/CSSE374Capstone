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
	public void setName(String name) {
		this.Name = name;
	}

	@Override
	public void setType(String type) {
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
	
	@Override
	public String toString() {
		String[] typeSplit = this.Type.split("_");
		return typeSplit[typeSplit.length-1] + " " + this.Name;
	}

}
