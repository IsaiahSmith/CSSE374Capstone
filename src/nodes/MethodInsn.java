package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IMethod;
import model.IMethodInsn;
import model.INode;

public class MethodInsn implements IMethodInsn{

	
	private IMethod Method;
	private String Owner;
	private String Name;
	private List<INode> args;
	
	public MethodInsn() {
		this.Method = new MethodNode();
		this.Owner = new String();
		this.Name = new String();
		this.args = new ArrayList<INode>();
	}
	
	@Override
	public void setOwner(String owner) {
		this.Owner = owner;
	}

	@Override
	public void setName(String name) {
		this.Name = name;
	}

	public void setArgs(List<IArgument> args)
	
	@Override
	public void setMethod(String owner, String name, String desc) {
		
	}

	@Override
	public String getOwner() {
		return this.Owner;
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public String getDesc() {
		return this.Desc;
	}
	
	@Override
	public String toString() {
		return this.Owner+" "+this.Name+" "+this.Desc;
	}
}
