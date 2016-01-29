package arrowDesignBuilder;

import java.util.ArrayList;
import java.util.List;

import arrows.Arrow;
import arrows.IArrow;
import model.IFile;
import model.IInnerNode;

public class AssociationLinker extends Linker{

	public AssociationLinker(List<IFile> files, String type, boolean includeAll){
		this.files = files;
	}
	
	@Override
	public List<IArrow> link() {
		List<IArrow> assocArrows = new ArrayList<IArrow>();
		for(IFile file : this.files){
			for(IInnerNode field : file.getFields()){
				String left = file.getName();
				String right = field.getType();
				if(includeAll){
					addArrow(left, right, assocArrows);
				}else{
					if(true/*check if right is in this.files*/){
						addArrow(left, right, assocArrows);
					}
				}
			}
		}
		return assocArrows;
	}

	private void addArrow(String left, String right, List<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(left);
		arrow.setEnd(right);
		arrow.setType("Association");
		arrows.add(arrow);
		
	}
}
