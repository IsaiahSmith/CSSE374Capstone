package arrowDesignBuilder;

import java.util.List;

import arrows.IArrow;
import model.IFile;

public abstract class Linker {
	List<IFile> files;
	String type;
	boolean includeAll;
	
	public abstract List<IArrow> link();
	
//	List<String> getFileNames() {
//		List<String> names = new ArrayList<String>();
//		for(INode node:model.getFiles()){
//			names.add(node.getName());
//		}
//		return names;
//	}
}
