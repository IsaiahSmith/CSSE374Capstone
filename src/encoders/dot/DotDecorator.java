package encoders.dot;

import java.util.ArrayList;
import java.util.List;

import model.IModel;
import model.INode;

public abstract class DotDecorator implements Dot {
	protected final Dot decoratedDot;
	protected IModel model;
	
	public DotDecorator(Dot dot, IModel model) {
		this.decoratedDot = dot;
		this.model = model;
	}
	public StringBuilder getDot() {
		return this.decoratedDot.getDot();
	}
	
	List<String> getFileNames() {
		List<String> names = new ArrayList<String>();
		for(INode node:model.getFiles()){
			names.add(node.getName());
		}
		return names;
	}
}
