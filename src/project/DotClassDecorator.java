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
		String[] nameSplit = name.split("/");

		String str = nameSplit[nameSplit.length-1] + " [ \n\t\tlabel = \"{";
		if(!isClass){
			str += "\\<\\<interface\\>\\>\\l";
		}
		str += nameSplit[nameSplit.length-1] + "|";
		for(int i = 0; i < fields.size(); i++) {
			String type = fields.get(i).get("Type");
			String[] typeSplit = type.split("\\.");
			
			if(typeSplit.length>0) {
				type = typeSplit[typeSplit.length-1];
			}
			str+= fields.get(i).get("AccessLevel") + " " 
					+ fields.get(i).get("Name") + " : " 
					+ type + "\\l";
		}
		str += " | ";
		for(Map<String, String> m : methods){
			String accessLevel = m.get("Accesslevel");
			String methodName = m.get("Name");
			String returnType = m.get("ReturnType");
			String[] argTypes;
			String[] returnTypeSplit = returnType.split("\\.");
			returnType = returnTypeSplit[returnTypeSplit.length-1];
			
			if(m.get("Accesslevel")==null) {
				accessLevel="";
			}
			
			if(!methodName.equals("<init>")){
				
				if(m.get("Accesslevel")!=null) {
					str += accessLevel + " ";
				}
				
				str += methodName
						+ "(";
				
				if(m.get("args")!=null) {
					argTypes = m.get("args").split(";");
					for(int i=0; i<argTypes.length;i++){
						str+="arg"+i+" "+argTypes[i]+", ";
					}
					str = str.substring(0, str.length()-2);
				}
				str+=")";
				str += " : "
						+ returnType
						+ "\\l";
			}
		}
		str += "\\l}\" \n\t]\n\t";
		return str;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeClasses());
	}
}
