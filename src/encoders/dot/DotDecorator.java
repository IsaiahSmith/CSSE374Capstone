package encoders.dot;

import java.util.ArrayList;
import java.util.List;

import model.IModel;
import model.INode;

public abstract class DotDecorator extends ADot {
	IModel model;
	ADot toBeDecorated;
	public abstract StringBuilder getDot();
	
	List<String> getFileNames() {
		List<String> names = new ArrayList<String>();
		for(INode node:model.getFiles()){
			names.add(node.getName());
		}
		return names;
	}
}
