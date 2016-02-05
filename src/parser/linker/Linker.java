package parser.linker;

import java.util.ArrayList;
import java.util.List;

import model.IArrow;
import model.IFile;

public abstract class Linker {
	List<IFile> files;
	boolean includeAll;
	
	public abstract List<IArrow> link();
	
	List<String> getFileNames() {
		List<String> names = new ArrayList<String>();
		for(IFile file : files){
			names.add(sanitize(file.getName()));
		}
		return names;
	}
	public static String sanitize(String input) {
		return input.replaceAll("\\.", "_").replaceAll("/", "_");
	}
}
