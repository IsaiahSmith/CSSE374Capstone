package model;

import java.util.List;

public interface IFile extends INode {
	public void setSuperName(String superName);
	public void addMethod(IMethod method);
	public void addField(IInnerNode field);
	public void addImplements(IFile inface);
	
	public String getSuperName();
	public List<IMethod> getMethods();
	public List<IInnerNode> getFields();
	public List<IFile> getInterfaces();
	
}
