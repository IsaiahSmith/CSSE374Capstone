package parser.linker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.IArrow;
import model.IFile;
import model.IInnerNode;
import nodes.Arrow;

public class AssociationLinker extends Linker{
	
	public static String TYPE = "Association";
	
	public AssociationLinker(Set<IFile> files, boolean includeAll){
		this.files = files;
		this.includeAll = includeAll;
	}
	
	@Override
	public Set<IArrow> link() {
		Set<IArrow> assocArrows = new HashSet<IArrow>();
		for(IFile file : this.files) {
			for(IInnerNode field : file.getFields()){
				String left = file.getName();
				String right = field.getType();
				if(includeAll){
					addArrow(left, right, assocArrows);
				}else{
					if(getFileNames().contains(sanitize(right))){
						addArrow(left, right, assocArrows);
					}
				}
			}
		}
		return assocArrows;
	}

	private void addArrow(String left, String right, Set<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(sanitize(left));
		arrow.setEnd(sanitize(right));
		arrow.setType(AssociationLinker.TYPE);
		arrows.add(arrow);
		
	}
}
