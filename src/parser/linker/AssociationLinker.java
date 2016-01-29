package parser.linker;

import java.util.ArrayList;
import java.util.List;

import model.IArrow;
import model.IFile;
import model.IInnerNode;
import nodes.Arrow;

public class AssociationLinker extends Linker{
	
	public static String TYPE = "Association";
	
	public AssociationLinker(List<IFile> files, boolean includeAll){
		this.files = files;
		this.includeAll = includeAll;
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
					if(getFileNames().contains(right)){
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
		arrow.setType(AssociationLinker.TYPE);
		arrows.add(arrow);
		
	}
}
