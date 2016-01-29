package arrowDesignBuilder;

import java.util.ArrayList;
import java.util.List;

import arrows.Arrow;
import arrows.IArrow;
import model.IFile;

public class InheritanceLinker extends Linker {
	
	public InheritanceLinker(List<IFile> files, String type, boolean includeAll){
		this.files = files;
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
					if(true /*check if right is in files*/)
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
		arrow.setType("Implements");
		arrows.add(arrow);		
	}
}
