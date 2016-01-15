package model;

import java.util.List;

public interface Iinterface extends INode{
	public void addSuperName(String superName);
	public void addMethod(IMethod method);
	
	public String getSuperName();
	public List<IMethod> getMethods();
}
