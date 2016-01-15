package model;

import java.util.List;

public interface IModel {
	public void addClass(INode cls);
	public List<INode> getNodes();
}
