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
			names.add(file.getName());
		}
		return names;
	}
}
