package parser.linker;

import java.util.ArrayList;
import java.util.List;

import model.IArrow;
import model.IFile;
import nodes.Arrow;

public class ImplementsLinker extends Linker{
	
	public static String TYPE = "Implements";
	
	public ImplementsLinker(List<IFile> files, boolean includeAll){
		this.files = files;
		this.includeAll = includeAll;
	}
	
	@Override
	public List<IArrow> link() {
		List<IArrow> implemArrows = new ArrayList<IArrow>();
		for(IFile file : this.files){
			if(file.getInterfaces() != null){
				for(IFile interf : file.getInterfaces()){
					String left = file.getName();
					String right = interf.getName();
					if(includeAll) {
						addArrow(left, right, implemArrows);
					} else {
						if(getFileNames().contains(right))
							addArrow(left, right, implemArrows);
					}
				}
			}
		}
		return implemArrows;
	}

	private void addArrow(String left, String right, List<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(left);
		arrow.setEnd(right);
		arrow.setType(ImplementsLinker.TYPE);
		arrows.add(arrow);		
	}
}