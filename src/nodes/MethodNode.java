package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IMethod;
import model.INode;

public class MethodNode implements IMethod {

	
	private String Name;
	private String Type;
	private String AccessLevel;
	private List<INode> Args;
	
	public MethodNode() {
		this.Name = new String();
		this.Type = new String();
		this.AccessLevel = new String();
		this.Args = new ArrayList<INode>();
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
	public void addAccessLevel(String accessLevel) {
		this.AccessLevel = accessLevel;
	}
	
	@Override
	public void addArg(INode arg) {
		this.Args.add(arg);
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
	public String toString() {
		String str = new String();
		str += this.AccessLevel + " " + this.Type + " " + this.Name + "(";
		for(INode arg:this.Args)
			str += arg.toString() + ", ";
		str = str.substring(0, str.length()-2) + ")";
		return str;
	}
}
