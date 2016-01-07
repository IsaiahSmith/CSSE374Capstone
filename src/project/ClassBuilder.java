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
}
