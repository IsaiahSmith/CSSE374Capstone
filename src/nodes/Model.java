package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IModel;
import model.INode;

public class Model implements IModel {

	private List<INode> Nodes;
	
	public Model() {
		this.Nodes = new ArrayList<INode>();
	}
	
	@Override
	public void addNode(INode node) {
		this.Nodes.add(node);
	}

	@Override
	public List<INode> getNodes() {
		return this.Nodes;
	}
	
	@Override
	public String toString() {
		String str = new String();
		for(INode node:Nodes)
			str += node.toString() + "\n\n";
		return str;
	}

}
