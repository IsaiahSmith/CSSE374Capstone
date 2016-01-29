package model;

import java.util.List;

public interface IPattern {
	public void setName(String name);
	public void addNode(IFile file);
	public void addArrow(String originNode, String endNode);
	
	public String getName();
	public List<IFile> getNodes();
	public boolean containsArrow(String origin, String end);
}
