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
	private List<IMethod> InnerMethods;
	private List<ITypeInsn> TypeInsns;
	
	private String ClassName;
	private String Signature;
	private IMethod Parent;
	
	public MethodNode() {
		this.Name = new String();
		this.Type = new String();
		this.AccessLevel = new String();
		this.Args = new ArrayList<INode>();
		this.InnerMethods = new ArrayList<IMethod>();
		this.TypeInsns = new ArrayList<ITypeInsn>();
		this.ClassName = new String();
		this.Signature = new String();
		this.Parent = null;
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
	public void addInnerMethod(IMethod method) {
		this.InnerMethods.add(method);
	}
	@Override
	public void addTypeInsn(ITypeInsn typeInsn) {
		this.TypeInsns.add(typeInsn);
	}
	
	@Override
	public void setClassName(String className) {
		this.ClassName = className;
	}
	
	@Override
	public void setSignature(String signature) {
		this.Signature = signature;
	}
	
	@Override
	public void addParent(IMethod parent) {
		this.Parent = parent;
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
	
//	@Override
//	public List<IMethodInsn> getMethodInsn() {
//		return this.MethodInsns;
//	}
	@Override
	public List<ITypeInsn> getTypeInsn() {
		return this.TypeInsns;
	}
	
	@Override
	public String getClassName() {
		return this.ClassName;
	}
	
	@Override
	public String getSignature() {
		return this.Signature;
	}
	
	@Override
	public IMethod getParent() {
		return this.Parent;
	}

	@Override
	public List<IMethod> getInnerMethods() {
		return this.InnerMethods;
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
