package project;

import java.util.ArrayList;
import java.util.List;

public abstract class DotDecorator extends ADot {
	List<ClassBuilder> classes;
	public abstract StringBuilder getDot();
	
	List<String> getFileNames() {
		List<String> names = new ArrayList<String>();
		for(ClassBuilder cls:classes){
			names.add(cls.name);
		}
		return names;
	}
}
