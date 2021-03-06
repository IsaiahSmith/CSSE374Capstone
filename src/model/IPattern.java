package model;

public interface IPattern {
	public void setName(String name);
	public void setType(String type);
	public void setNode(String file);
	public void setArrow(IArrow arrow);
	public void setInstance(int instance);
	public void setRoot();
	
	public String getName();
	public String getType();
	public String getNode();
	public IArrow getArrow();
	public int getInstance();
	public boolean isRoot();
}