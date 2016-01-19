package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.IFile;
import model.IMethod;
import model.IMethodInsn;
import model.IModel;
import model.INode;
import model.ITypeInsn;

public class DotUsesDecorator extends DotDecorator {

	ADot toBeDecorated;
	
	public DotUsesDecorator(ADot toBeDecorated, IModel model) {
		this.toBeDecorated = toBeDecorated;
		this.model = model;
	}
	
	public StringBuilder makeUses() {
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"vee\" \n\t\tstyle= \"dashed\"\n\t]\n\t");
		List<String> uses = new ArrayList<String>();
		for(IFile c : model.getFiles()){
			if(c.getMethods() != null){
				for(IMethod method: c.getMethods()){
					uses.add(method.getType());
					for(INode argument: method.getArgs()) {
						uses.add(argument.getType());
					}
					for(IMethodInsn mInsn:method.getMethodInsn()) {
						uses.add(mInsn.getDesc());
					}
					for(ITypeInsn tInsn: method.getTypeInsn()) {
						uses.add(tInsn.getType());
					}
				}
				Set<String> usesSet = new HashSet<String>(uses);
				String left = c.getName();
				for(String use: usesSet) {
					temp.append(addArrow(left, use));
				}
			}
		}
		return temp;
	}
	
	private String addArrow(String left, String right){
		String[] leftSplit = left.split("_");
		String[] rightSplit = right.split("_");
		left = leftSplit[leftSplit.length - 1];
		right = rightSplit[rightSplit.length - 1];
		return " " + left + " -> " + right+"\n\t";
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeUses());
	}
	

}
