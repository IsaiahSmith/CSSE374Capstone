package model;

import java.util.List;

public interface IMethod extends IInnerNode{
	public void addArg(INode arg);
	public void addMethodInsn(IMethodInsn methodInsn);
	public void addTypeInsn(ITypeInsn typeInsn);
	
	public List<INode> getArgs();
	public List<IMethodInsn> getMethodInsn();
	public List<ITypeInsn> getTypeInsn();
}
