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
			}
		Set<String> usesSet = new HashSet<String>(uses);
			
			
//			Set<String> argTypes = new HashSet<String>();
//			for(String s : argTypes) {
//				String right = s;
//				String[] rightSplit = right.split("\\.");
//				right = rightSplit[rightSplit.length-1];
//				temp.append(c.name + "->" + right + "\n\t");
//			}
		}
		
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeUses());
	}
	

}
