package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IMethod;
import model.IMethodInsn;
import model.INode;
import model.ITypeInsn;

public class MethodNode implements IMethod {

	
	private String Name;
	private String Type;
	private String AccessLevel;
	private List<INode> Args;
	private List<IMethodInsn> MethodInsns;
	private List<ITypeInsn> TypeInsns;
	
	public MethodNode() {
		this.Name = new String();
		this.Type = new String();
		this.AccessLevel = new String();
		this.Args = new ArrayList<INode>();
		this.MethodInsns = new ArrayList<IMethodInsn>();
		this.TypeInsns = new ArrayList<ITypeInsn>();
	}
	@Override
	public void setName(String name) {
		this.Name = name;
	}
	
	@Override
	public void setType(String type) {
		this.Type = type;
	}
	
	@Override
	public void setAccessLevel(String accessLevel) {
		this.AccessLevel = accessLevel;
	}
	
	@Override
	public void addArg(INode arg) {
		this.Args.add(arg);
	}
	
	@Override
	public void addMethodInsn(IMethodInsn methodInsn) {
		this.MethodInsns.add(methodInsn);
	}
	@Override
	public void addTypeInsn(ITypeInsn typeInsn) {
		this.TypeInsns.add(typeInsn);
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public String getType() {
		return this.Type;
	}

	@Override
	public String getAccessLevel() {
		return this.AccessLevel;
	}

	@Override
	public List<INode> getArgs() {
		return this.Args;
	}
	
	@Override
	public List<IMethodInsn> getMethodInsn() {
		return this.MethodInsns;
	}
	@Override
	public List<ITypeInsn> getTypeInsn() {
		return this.TypeInsns;
	}

	@Override
	public String toString() {
		String str = new String();
		String[] typeSplit = this.Type.split("_");
		str += this.AccessLevel + " " + typeSplit[typeSplit.length-1] + " " + this.Name + "(";
		if(this.Args.size()!=0) {
			for(INode arg:this.Args)
				str += arg.toString() + ", ";
			str = str.substring(0, str.length()-2);
		}
		str += ")";
		
		return str;
	}
	
}
