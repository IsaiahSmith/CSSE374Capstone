package project;

import java.util.List;

import model.IFile;
import model.IModel;

public class DotInheritanceDecorator extends DotDecorator{
	
	ADot toBeDecorated;

	public DotInheritanceDecorator(ADot toBeDecorated, IModel model){
		this.toBeDecorated = toBeDecorated;
		this.model = model;
	}
	
	public StringBuilder makeInheritance(){
		StringBuilder temp = new StringBuilder();
		List<String> fileNames = getFileNames();
		for(IFile c : model.getFiles()){
			if(c.getSuperName() != null){
				String left = c.getName();
				String right = c.getSuperName();
				if(includeAll){
					temp.append(addArrow(left, right));
				}else{
					if(fileNames.contains(right))
						temp.append(addArrow(left, right));
				}
			}
		}
		return temp;
	}
	
	private String addArrow(String left, String right){
//		String[] leftSplit = left.split("/");
//		String[] rightSplit = right.split("/");
//		left = leftSplit[leftSplit.length-1];
//		right = rightSplit[rightSplit.length-1];
		return " " + left + " -> " + right+"[arrowhead=\"empty\" style = \"filled\" ]"+"\n\t";
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeInheritance());
	}
}
