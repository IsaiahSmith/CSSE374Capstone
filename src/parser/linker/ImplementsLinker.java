package parser.linker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.IArrow;
import model.IFile;
import nodes.Arrow;

public class ImplementsLinker extends Linker{
	
	public static String TYPE = "Implements";
	
	public ImplementsLinker(Set<IFile> files, boolean includeAll){
		this.files = files;
		this.includeAll = includeAll;
	}
	
	@Override
	public Set<IArrow> link() {
		Set<IArrow> implemArrows = new HashSet<IArrow>();
		for(IFile file : this.files){
			if(file.getInterfaces() != null){
				for(IFile interf : file.getInterfaces()){
					String left = file.getName();
					String right = interf.getName();
					if(includeAll) {
						addArrow(left, right, implemArrows);
					} else {
						if(getFileNames().contains(sanitize(right)))
							addArrow(left, right, implemArrows);
					}
				}
			}
		}
		return implemArrows;
	}

	private void addArrow(String left, String right, Set<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(sanitize(left));
		arrow.setEnd(sanitize(right));
		arrow.setType(ImplementsLinker.TYPE);
		arrows.add(arrow);		
	}
}
