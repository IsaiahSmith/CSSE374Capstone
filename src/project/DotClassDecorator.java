package project;

import java.util.List;
import java.util.Map;

public class DotClassDecorator extends DotDecorator {
	
	ADot toBeDecorated;
	
	private List<ClassBuilder> classes;

	public DotClassDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeClasses(){
		System.out.println("In make classes.");
		StringBuilder temp = new StringBuilder("");
		for(ClassBuilder c : classes){
			System.out.println("Appending class: " + c.name);
			temp.append(buildString(c.name, c.fields, c.methods, c.isClass));
		}
		return temp;
	}

	private String buildString(String name, List<Map<String, String>> fields, List<Map<String, String>> methods, boolean isClass) {
		
		String str = name + " [ \n\t\tlabel = \"{";
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
					+ m.get("ReturnType");
		}
		str += "\\l}\" \n\t]\n\t";
		return str;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeClasses());
	}
}
