package pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IFile;

public class Pattern implements IPattern {
	
	private String name;
	private List<IFile> nodes;
	private Map<String, String> arrows;
	
	public Pattern(String name){
		this.name = name;
		this.arrows = new HashMap<String, String>();
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
	public void addArrow(String originNode, String endNode) {
		this.arrows.put(originNode, endNode);
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
	public boolean containsArrow(String origin, String end) {
		return this.arrows.get(origin).equals(end);
	}

}
