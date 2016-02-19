package parser.linker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.IArrow;
import model.IFile;
import nodes.Arrow;

public class InheritanceLinker extends Linker {
	
	public static String TYPE = "Inheritance";
	
	public InheritanceLinker(Set<IFile> files, boolean includeAll){
		this.files = files;
		this.includeAll = includeAll;
	}

	@Override
	public Set<IArrow> link() {
		Set<IArrow> inherArrows = new HashSet<IArrow>();
		for(IFile c : this.files){
			if(c.getSuperName() != null){
				String left = c.getName();
				String right = c.getSuperName();
				if(includeAll){
					addArrow(left, right, inherArrows);
				}else{
					if(getFileNames().contains(sanitize(right)))
						addArrow(left, right, inherArrows);
				}
			}
		}
		return inherArrows;
	}
	
	private void addArrow(String left, String right, Set<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(sanitize(left));
		arrow.setEnd(sanitize(right));
		arrow.setType(InheritanceLinker.TYPE);
		arrows.add(arrow);		
	}
}
