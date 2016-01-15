package project;

import java.util.List;

import model.IFile;
import model.IModel;

public class DotImplementsDecorator extends DotDecorator {
	ADot toBeDecorated;

	public DotImplementsDecorator(ADot toBeDecorated, IModel model){
		this.toBeDecorated = toBeDecorated;
		this.model = model;
	}
	
	public StringBuilder makeImplements(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"empty\" \n\t\tstyle= \"dashed\"\n\t]\n\t");
		List<String> fileNames = getFileNames();
		for(IFile c : model.getFiles()){
			if(c.getInterfaces() != null){
				for(IFile i : c.getInterfaces()){
					String left = c.getName();
					String right = i.getName();
					if(includeAll) {
						temp.append(addArrow(left, right));
					} else {
						if(fileNames.contains(right))
							temp.append(addArrow(left, right));
					}
				}
			}
		}
		return temp;
	}
	
	private String addArrow(String left, String right) {
		String[] leftSplit = left.split("/");
		String[] rightSplit = right.split("/");
		left = leftSplit[leftSplit.length-1];
		right = rightSplit[rightSplit.length-1];
		return left + "->" + right + "\n\t";
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeImplements());
	}
}
