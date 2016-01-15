package model;

import java.util.List;

public interface IMethod extends IInnerNode{
	public void addArg(INode arg);
	public List<INode> getArgs();
}
