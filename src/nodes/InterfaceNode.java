package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IMethod;
import model.Iinterface;

public class InterfaceNode implements Iinterface {

	private String Name;
	private String SuperName;
	private List<IMethod> Methods;
	
	public InterfaceNode() {
		this.Name = new String();
		this.SuperName = new String();
		this.Methods = new ArrayList<IMethod>();
	}
	
	@Override
	public void addName(String name) {
		this.Name = name;
	}

	@Override
	public void addType(String type) {
		throw new UnsupportedOperationException("Cannot set type of interface");
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
	public String getType() {
		return "Interface";
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public String getSuperName() {
		return this.SuperName;
	}

	@Override
	public List<IMethod> getMethods() {
		return this.Methods;
	}

}
