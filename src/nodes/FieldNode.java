package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IInnerNode;

public class FieldNode implements IInnerNode {

	private String Name;
	private String Type;
	private String Visibility;
	private List<String> Modifiers;
	private Object value;
	private String signature;
	
	public FieldNode() {
		this.Name = new String();
		this.Type = new String();
		this.signature = new String();
		this.Visibility = new String();
		this.Modifiers = new ArrayList<String>();
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
	public String getType() {
		return this.Type;
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public String getVisibility() {
		return this.Visibility;
	}

	@Override
	public String toString() {
		String[] typeSplit = this.Type.split("_");
		return this.Visibility + " " + typeSplit[typeSplit.length-1] + " " + this.Name;
	}

	@Override
	public void addModifier(String modifier) {
		this.Modifiers.add(modifier);
		
	}

	@Override
	public List<String> getModifiers() {
		return this.Modifiers;
	}

	@Override
	public void setSig(String type) {
		this.signature = type;
	}

	@Override
	public String getSig() {
		return this.signature;
	}
}
