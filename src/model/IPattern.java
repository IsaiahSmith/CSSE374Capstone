package model;

import java.util.List;

public interface IPattern {
	public void setName(String name);
	public void addNode(IFile file);
	public void addArrow(IArrow arrow);
	
	public String getName();
	public List<IFile> getNodes();
	public boolean containsArrow(IArrow arrow);
}