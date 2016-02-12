package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IFile;
import model.IInnerNode;
import model.IMethod;

public class FileNode implements IFile {

	private String Name;
	private String Type;
	private String SuperName;
	private String visibility;
	private List<IMethod> Methods;
	private List<IInnerNode> Fields;
	private List<IFile> Interfaces;
	private List<String> Modifiers;
	
	public FileNode() {
		this.Name = new String();
		this.Type = new String();
		this.SuperName = new String();
		this.visibility = new String();
		this.Methods = new ArrayList<IMethod>();
		this.Fields = new ArrayList<IInnerNode>();
		this.Interfaces = new ArrayList<IFile>();
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
	
	public void setSuperName(String superName) {
		this.SuperName = superName;
	}

	public void addMethod(IMethod method) {
		this.Methods.add(method);
	}
	
	public void addField(IInnerNode field) {
		this.Fields.add(field);
	}
	
	public void addImplements(IFile inface) {
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

	public String getSuperName() {
		return this.SuperName;
	}

	public List<IMethod> getMethods() {
		return this.Methods;
	}

	public List<IInnerNode> getFields() {
		return this.Fields;
	}

	public List<IFile> getInterfaces() {
		return this.Interfaces;
	}
	
	@Override
	public String toString() {
		String str = new String();
		str += "--"+this.Type+"--";
		String[] nameSplit = this.Name.split("_");
		str += "\n" + nameSplit[nameSplit.length-1];
		if(!this.SuperName.equals("")) {
			String[] superNameSplit = this.SuperName.split("_");
			str += " extends " + superNameSplit[superNameSplit.length-1];
		}
		if(!this.Interfaces.isEmpty()) {
			str += " implements ";
			for(IFile inface:this.Interfaces) {
				String[] infaceSplit = inface.getName().split("_");
				str += infaceSplit[infaceSplit.length-1] + ", ";
			}
			str = str.substring(0, str.length()-2);
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

	@Override
	public void setVisibility(String visibility) {
		this.visibility = visibility;
		
	}

	@Override
	public String getVisibility() {
		return this.visibility;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSig() {
		// TODO Auto-generated method stub
		return null;
	}

}
