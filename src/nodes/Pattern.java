package nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IArrow;
import model.IFile;
import model.IPattern;

public class Pattern implements IPattern {
	
	private String name;
	private List<IFile> nodes;
	private List<IArrow> arrows;
	
	public Pattern(String name){
		this.name = name;
		this.arrows = new ArrayList<IArrow>();
		this.nodes = new ArrayList<IFile>();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addNode(IFile file) {
		this.nodes.add(file);
	}

	@Override
	public void addArrow(IArrow arrow) {
		this.arrows.add(arrow);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<IFile> getNodes() {
		return this.nodes;
	}

	@Override
	public boolean containsArrow(IArrow arrow) {
		return this.arrows.contains(arrow);
	}

}
