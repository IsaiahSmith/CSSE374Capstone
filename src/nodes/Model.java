package nodes;

import java.util.ArrayList;
import java.util.List;

import arrows.IArrow;
import model.IFile;
import model.IModel;
import model.INode;
import pattern.IPattern;

public class Model implements IModel {

	private List<IFile> Files;
	private List<IArrow> arrows;
	private List<IPattern> patterns;
	
	public Model() {
		this.Files = new ArrayList<IFile>();
	}
	
	@Override
	public void addFile(IFile file) {
		this.Files.add(file);
	}

	@Override
	public List<IFile> getFiles() {
		return this.Files;
	}
	
	@Override
	public String toString() {
		String str = new String();
		for(INode node:Files)
			str += node.toString() + "\n\n";
		return str;
	}

}
