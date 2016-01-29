package model;

import java.util.List;

public interface IPattern {
	public void setName(String name);
	public void setNode(String file);
	public void setArrow(IArrow arrow);
	
	public String getName();
	public String getNode();
	public IArrow getArrow();
}