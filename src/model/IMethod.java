package model;

import java.util.List;

public interface IMethod extends IInnerNode{
	public void addArg(INode arg);
	public void addTypeInsn(ITypeInsn typeInsn);
	public void addInnerMethod(IMethod method);
	public void setClassName(String className);
	public void setSignature(String signature);
	public void setParent(IMethod parent);
	
	public List<INode> getArgs();
	public List<ITypeInsn> getTypeInsn();
	public List<IMethod> getInnerMethods();
	public String getClassName();
	public String getSignature();
	public IMethod getParent();
	
	public boolean equals(Object obj);
}
