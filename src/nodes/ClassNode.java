package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IClass;
import model.IInnerNode;
import model.IMethod;
import model.Iinterface;

public class ClassNode implements IClass {

	private String Name;
	private String Type;
	private String SuperName;
	private List<IMethod> Methods;
	private List<IInnerNode> Fields;
	private List<Iinterface> Interfaces;
	
	public ClassNode() {
		this.Name = new String();
		this.Type = "Class";
		this.SuperName = new String();
		this.Methods = new ArrayList<IMethod>();
		this.Fields = new ArrayList<IInnerNode>();
		this.Interfaces = new ArrayList<Iinterface>();
	}
	
	@Override
	public void addName(String name) {
		this.Name = name;
	}
	
	@Override
	public void addType(String type) {
		this.Type = type;
	}
	
	@Override
	public void addSuperName(String superName) {
		this.SuperName = superName;
	}

	@Override
	public void addMethod(IMethod method) {
		this.Methods.add(method);
	}
	
	@Override
	public void addField(IInnerNode field) {
		this.Fields.add(field);
	}
	
	@Override
	public void addImplements(Iinterface inface) {
		this.Interfaces.add(inface);

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
	public String getSuperName() {
		return this.SuperName;
	}

	@Override
	public List<IMethod> getMethods() {
		return this.Methods;
	}

	@Override
	public List<IInnerNode> getFields() {
		return this.Fields;
	}

	@Override
	public List<Iinterface> getInterfaces() {
		return this.Interfaces;
	}
	
	@Override
	public String toString() {
		String str = new String();
		str += "--"+this.Type+"--";
		str += "\n" + this.Name;
		if(!this.SuperName.equals(null))
			str += " extends " + this.SuperName;
		if(!this.Interfaces.isEmpty()) {
			for(Iinterface inface:this.Interfaces)
				str += inface.getName() + ", ";
			str = str.substring(str.length()-2);
		}
		if(!this.Fields.isEmpty()) {
			str += "\n";
			for(IInnerNode field:this.Fields)
				str += "\n" + field.toString();
		}
		if(!this.Methods.isEmpty()) {
			str += "\n";
			for(IMethod method:this.Methods)
				str += "\n" + method.toString();
		}
			
		return str;
	}

}
