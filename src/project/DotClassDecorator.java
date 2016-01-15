package project;

import java.util.List;
import java.util.Map;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.IModel;
import model.INode;

public class DotClassDecorator extends DotDecorator {
	
	ADot toBeDecorated;

	public DotClassDecorator(ADot toBeDecorated, IModel model){
		this.toBeDecorated = toBeDecorated;
		this.model = model;
	}
	
	public StringBuilder makeClasses(){
		StringBuilder temp = new StringBuilder("");
		for(IFile node : model.getFiles()){
			temp.append(buildString(node.getName(), node.getFields(), node.getMethods(), node.getType()));
		}
		return temp;
	}

	private String buildString(String name, List<IInnerNode> fields, List<IMethod> methods, String fileType) {
		String[] nameSplit = name.split("/");

		String str = nameSplit[nameSplit.length-1] + " [ \n\t\tlabel = \"{";
		if(fileType.equals("interface")){
			str += "\\<\\<interface\\>\\>\\l";
		} else if(fileType.equals("abstract")) {
			str += "\\<\\<abstract\\>\\>\\l";
		}
		str += nameSplit[nameSplit.length-1] + "|";
		for(IInnerNode field:fields) {
			String type = field.getType();
			String[] typeSplit = type.split("_");
			
			if(typeSplit.length>0) {
				type = typeSplit[typeSplit.length-1];
			}
			str+= field.getAccessLevel() + " " 
					+ field.getName() + " : " 
					+ type + "\\l";
		}
		str += " | ";
		for(IMethod m : methods){
			String accessLevel = m.getAccessLevel();
			String methodName = m.getName();
			String returnType = m.getType();
			List<INode> args;
			String[] returnTypeSplit = returnType.split("_");
			returnType = returnTypeSplit[returnTypeSplit.length-1];
			
			if(m.getAccessLevel()==null) {
				accessLevel="";
			}
			
			if(!methodName.equals("<init>")){
				
				if(m.getAccessLevel()!=null) {
					str += accessLevel + " ";
				}
				
				str += methodName
						+ "(";
				
				if(m.getArgs()!=null) {
					args = m.getArgs();
					for(INode arg:args){
						str+=arg.toString()+", ";
					}
					str = str.substring(0, str.length()-2);
				}
				str+=")";
				str += " : "
						+ returnType
						+ "\\l";
			}
		}
		str += "\\l}\" \n\t]\n\t";
		return str;
	}

	@Override
	public StringBuilder getDot() {
		
		return toBeDecorated.getDot().append(makeClasses());
	}
}
