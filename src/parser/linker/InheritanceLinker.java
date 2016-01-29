package parser.linker;

import java.util.ArrayList;
import java.util.List;

import model.IArrow;
import model.IFile;
import nodes.Arrow;

public class InheritanceLinker extends Linker {
	
	public static String TYPE = "Inheritance";
	
	public InheritanceLinker(List<IFile> files, boolean includeAll){
		this.files = files;
		this.includeAll = includeAll;
	}

	@Override
	public List<IArrow> link() {
		List<IArrow> inherArrows = new ArrayList<IArrow>();
		for(IFile c : this.files){
			if(c.getSuperName() != null){
				String left = c.getName();
				String right = c.getSuperName();
				if(includeAll){
					addArrow(left, right, inherArrows);
				}else{
					if(getFileNames().contains(right))
						addArrow(left, right, inherArrows);
				}
			}
		}
		return inherArrows;
	}
	
	private void addArrow(String left, String right, List<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(left);
		arrow.setEnd(right);
		arrow.setType(InheritanceLinker.TYPE);
		arrows.add(arrow);		
	}
}
