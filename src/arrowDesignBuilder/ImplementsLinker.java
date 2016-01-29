package arrowDesignBuilder;

import java.util.ArrayList;
import java.util.List;

import arrows.Arrow;
import arrows.IArrow;
import model.IFile;

public class ImplementsLinker extends Linker{
	
	public ImplementsLinker(List<IFile> files, String type, boolean includeAll){
		this.files = files;
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
						if(true /*check if right is in files*/)
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
		arrow.setType("Implements");
		arrows.add(arrow);		
	}
}
