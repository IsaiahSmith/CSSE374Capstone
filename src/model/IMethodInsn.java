package model;

public interface IMethodInsn {
	
	public void setOwner(String owner);
	public void setName(String name);
	public void setDesc(String desc);
	
	public String getOwner();
	public String getName();
	public String getDesc();
	
}
