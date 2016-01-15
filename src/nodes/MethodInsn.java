package nodes;

import model.IMethodInsn;

public class MethodInsn implements IMethodInsn{

	private String Owner;
	private String Name;
	private String Desc;
	
	public MethodInsn() {
		this.Owner = new String();
		this.Name = new String();
		this.Desc = new String();
	}
	
	@Override
	public void setOwner(String owner) {
		this.Owner = owner;
	}

	@Override
	public void setName(String name) {
		this.Name = name;
	}

	@Override
	public void setDesc(String desc) {
		this.Desc = desc;
	}

	@Override
	public String getOwner() {
		return this.Owner;
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public String getDesc() {
		return this.Desc;
	}
	
	@Override
	public String toString() {
		return this.Owner+" "+this.Name+" "+this.Desc;
	}
}
