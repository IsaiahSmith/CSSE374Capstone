package arrowDesignBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.IArrow;
import model.IFile;
import model.IMethod;
import model.INode;
import model.ITypeInsn;
import nodes.Arrow;

public class UsesLinker extends Linker {

	public UsesLinker(List<IFile> files, String type, boolean includeAll){
		this.files = files;
	}
	
	@Override
	public List<IArrow> link() {
		List<IArrow> usesArrows = new ArrayList<IArrow>();
		for(IFile c : this.files){
			List<String> uses = new ArrayList<String>();
			if(c.getMethods() != null){
				for(IMethod method: c.getMethods()){
					uses.add(method.getType());
					for(INode argument: method.getArgs()) {
						uses.add(argument.getType());
					}
					for(IMethod mInsn:method.getInnerMethods()) {
						uses.add(mInsn.getType());
					}
					for(ITypeInsn tInsn: method.getTypeInsn()) {
						uses.add(tInsn.getType());
					}
				}
				Set<String> usesSet = new HashSet<String>(uses);
				String left = c.getName();
				for(String use: usesSet) {
					if(includeAll){
						addArrow(left, use, usesArrows);
					}else{
						if(true /*check if right is in files*/)
							addArrow(left, use, usesArrows);
					}
				}
			}
		}
		return usesArrows;
	}
	
	private void addArrow(String left, String right, List<IArrow> arrows) {
		IArrow arrow = new Arrow();
		arrow.setOrigin(left);
		arrow.setEnd(right);
		arrow.setType("Uses");
		arrows.add(arrow);		
	}
}
