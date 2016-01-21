package encoders.dot;

import java.util.List;
import java.util.Map;

import model.IFile;
import model.IInnerNode;
import model.IModel;

public class DotAssociationDecorator extends DotDecorator {

	ADot toBeDecorated;
	
	public DotAssociationDecorator(ADot toBeDecorated, IModel model) {
		this.toBeDecorated = toBeDecorated;
		this.model = model;
	}
	
	public StringBuilder makeAssociation(){
		StringBuilder temp = new StringBuilder();
		List<String> fileNames = getFileNames();
		for(IFile c : model.getFiles()){
			for(IInnerNode field : c.getFields()){
				String left = c.getName();
				String right = field.getType();
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
//		String[] leftSplit = left.split("_");
//		String[] rightSplit = right.split("_");
//		left = leftSplit[leftSplit.length - 1];
//		right = rightSplit[rightSplit.length - 1];
		return " " + left + " -> " + right+" [arrowhead=\"vee\" style = \"filled\" ]"+"\n\t";
	}
	
	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeAssociation());
	}

}
