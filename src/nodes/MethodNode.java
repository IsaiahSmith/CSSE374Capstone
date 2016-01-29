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
	private String Visibility;
	private List<INode> Args;
	private List<IMethod> InnerMethods;
	private List<ITypeInsn> TypeInsns;
	private List<String> Modifiers;
	
	private String ClassName;
	private String Signature;
	private IMethod Parent;
	
	
	public MethodNode() {
		this.Name = new String();
		this.Type = new String();
		this.Visibility = new String();
		this.Args = new ArrayList<INode>();
		this.InnerMethods = new ArrayList<IMethod>();
		this.TypeInsns = new ArrayList<ITypeInsn>();
		this.Modifiers = new ArrayList<String>();
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
	public void setVisibility(String accessLevel) {
		this.Visibility = accessLevel;
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
	public void setParent(IMethod parent) {
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
	public String getVisibility() {
		return this.Visibility;
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
		str += this.Visibility + " " + typeSplit[typeSplit.length-1] + " " + this.Name + "(";
		if(this.Args.size()!=0) {
			for(INode arg:this.Args)
				str += arg.toString() + ", ";
			str = str.substring(0, str.length()-2);
		}
		str += ")";
		
		return str;
	}
	
	@Override
	public boolean equals(Object method) {
		//check first to see if method is a MethodNode object
		if(!method.getClass().toString().equals(this.getClass().toString()))
			return false;
		MethodNode m = (MethodNode)method;
		
//		System.out.println("\nMethod ClassName: "+m.getClassName());
//		System.out.println("This ClassName: "+this.getClassName());
//		System.out.println(m.getClassName().equals(this.getClassName()));
		//check if methods have the same ClassName
		if(!m.getClassName().equals(this.getClassName()))
			return false;
		
//		System.out.println("Method name: "+m.getName());
//		System.out.println("This name: "+this.getName());
//		System.out.println(m.getName().equals(this.getName()));
		//check if both methods have the same name
		if(!m.getName().equals(this.getName()))
			return false;
		
//		System.out.println("Method args: "+m.getArgs().toString());
//		System.out.println("This args: "+this.getArgs().toString());
		//check if both methods have the same arg types
		if(m.getArgs().size() != this.getArgs().size()) {
			return false;
		} else {
			for(int i=0; i<m.getArgs().size(); i++) {
				if(!m.getArgs().get(i).getType().equals(this.getArgs().get(i).getType()))
					return false;
			}
		}
		
		return true;
	}
	@Override
	public void addModifier(String modifier) {
		this.Modifiers.add(modifier);
	}
	@Override
	public List<String> getModifiers() {
		return this.Modifiers;
	}
	
}
