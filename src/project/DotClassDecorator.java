package project;

import java.util.List;
import java.util.Map;

public class DotClassDecorator extends DotDecorator {
	
	private List<ClassBuilder> classes;
	private String str = "";
	private StringBuilder stringBuild;	

	public DotClassDecorator(IDot toBeDecorated, List<ClassBuilder> classes){
		super(toBeDecorated);
		this.stringBuild = this..getDot();
		this.classes = classes;
		makeClasses();
	}
	
	public void makeClasses(){
		System.out.println("In make classes.");
		for(ClassBuilder c : classes){
			System.out.println("Appending class: " + c.name);
			stringBuild.append(buildString(c.name, c.fields, c.methods, c.isClass));
		}
	}

	private String buildString(String name, List<Map<String, String>> fields, List<Map<String, String>> methods, boolean isClass) {
		this.str += name + " [ label = \"{";
		if(!isClass){
			str += "interface\\l";
		}
		str += name + "|";
		for(int i = 0; i < fields.size(); i++) {
			str+= fields.get(i).get("AccessLevel") + " " 
					+ fields.get(i).get("Name") + " : " 
					+ fields.get(i).get("Type") + "\\l";
		}
		str += " | ";
		for(Map<String, String> m : methods){
			str += m.get("Accesslevel") 
					+ m.get("Name");
			if(m.get("args")!=null)
				str+=m.get("args");
			str+=")";
			str += " : "
					+ m.get("ReturnType") + "\\l}\" ]";
		}
		return str;
	}

	@Override
	public StringBuilder getDot() {
		return stringBuild;
	}
}
