package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassBuilder {
	public String name;
	public String superName;
	public boolean isClass;
	public List<String> inter;
	
	public List<Map<String, String>> fields;
	public List<Map<String, String>> methods;
	
	public ClassBuilder() {
		name = "";
		superName = "";
		isClass = true;
		inter = new ArrayList<String>();
		
		fields = new ArrayList<Map<String, String>>();
		methods = new ArrayList<Map<String, String>>();
	}
	
	@Override
	public String toString() {
		String str = "";
		
		if(isClass)
			str+="---Class---\n";
		else
			str+="---Interface--\n";
		
		str+="Name: "+name+"\n";
		
		str+="SuperName: "+superName+"\n";
		
		if(inter.size()!=0)
			str+="interface(s): "+inter.toString()+"\n";
		
		if(fields.size() != 0) {
			str+="Field(s):\n";
			for(int i = 0; i<fields.size(); i++) {
				str+="\t"+fields.get(i).get("AccessLevel")+" "+fields.get(i).get("Name")+"\n";
			}
		}
		
		str+="Method(s):\n";
		for(int i = 0; i<methods.size(); i++) {
			str+="\t";
			if(methods.get(i).get("Accesslevel") != null)
				str+=methods.get(i).get("Accesslevel")+" ";
			str+=methods.get(i).get("ReturnType") + " ";
			str+=methods.get(i).get("Name")+"(";
			if(methods.get(i).get("args")!=null)
				str+=methods.get(i).get("args");
			str+=")\n";
		}
		
		return str;
	}
}
