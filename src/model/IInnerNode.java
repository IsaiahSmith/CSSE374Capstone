package model;

import java.util.List;

public interface IInnerNode extends INode{
	public void setVisibility(String accessLevel);
	public void addModifier(String modifier);
	
	public String getVisibility();
	public List<String> getModifiers();
}
