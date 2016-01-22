package encoders.dot;

import java.util.List;
import java.util.Map;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.IModel;
import model.INode;

public class DotClassDecorator extends DotDecorator {

	public DotClassDecorator(ADot toBeDecorated, IModel model){
		this.toBeDecorated = toBeDecorated;
		this.model = model;
	}
	
	public StringBuilder makeClasses(){
		StringBuilder temp = new StringBuilder("");
		for(IFile node : model.getFiles()){
			temp.append(composeFile(node));
		}
		return temp;
	}
	
	private String composeFile (IFile node) {
		String nameBlock = buildNameBlock(node.getName(), node.getType());
		String fieldsBlock = buildFieldsBlock(node.getFields());
		String methodsBlock = buildMethodsBlock(node.getMethods());
		
		// I have not split and formatted the tag name, want to see if it will still work.
		String composedStr = node.getName()+" [ \n\t\tlabel = \"{" 
				+ nameBlock + "|" 
				+ fieldsBlock + "|"
				+ methodsBlock
				+ "\\l}\" \n\t]\n\t";
		
		return composedStr;
	}
	
	private String buildNameBlock(String name, String fileType) {
		String nameBlock = new String();
		String[] nameSplit = name.split("_");
		if(fileType.equals("interface")){
			nameBlock += "\\<\\<interface\\>\\>\\l";
		} else if(fileType.equals("abstract")) {
			nameBlock += "\\<\\<abstract\\>\\>\\l";
		}
		nameBlock += nameSplit[nameSplit.length-1];
		return nameBlock;
	}
	
	private String buildFieldsBlock(List<IInnerNode> fields) {
		String fieldsBlock = new String();
		for(IInnerNode field:fields) {
			String type = field.getType();
			String[] typeSplit = type.split("_");
			
			if(typeSplit.length>0) {
				type = typeSplit[typeSplit.length-1];
			}
			fieldsBlock+= field.getAccessLevel() + " " 
					+ field.getName() + " : "
					+ type + "\\l";
		}
		return fieldsBlock;
	}
	
	private String buildMethodsBlock(List<IMethod> methods) {
		String methodsBlock = new String();
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
					methodsBlock += accessLevel + " ";
				}
				
				methodsBlock += methodName
						+ "(";
				
				if(m.getArgs().size() != 0) {
					args = m.getArgs();
					for(INode arg : args){
						methodsBlock += arg.toString()+", ";
					}
					methodsBlock = methodsBlock.substring(0, methodsBlock.length()-2);
				}
				methodsBlock += ")";
				methodsBlock += " : "
						+ returnType
						+ "\\l";
			}
		}
		return methodsBlock;
		
	}
	
	private String buildString(String name, List<IInnerNode> fields, List<IMethod> methods, String fileType) {
		String nameBlock = new String();
		String fieldsBlock = new String();
		String methodsBlock = new String();
		
		String[] nameSplit = name.split("_");
		String str = nameSplit[nameSplit.length-1] + " [ \n\t\tlabel = \"{";
		if(fileType.equals("interface")){
			str += "\\<\\<interface\\>\\>\\l";
		} else if(fileType.equals("abstract")) {
			str += "\\<\\<abstract\\>\\>\\l";
		}
		
		str += nameSplit[nameSplit.length-1] + "|";
		nameBlock += nameSplit[nameSplit.length-1];
		
		for(IInnerNode field:fields) {
			String type = field.getType();
			String[] typeSplit = type.split("_");
			
			if(typeSplit.length>0) {
				type = typeSplit[typeSplit.length-1];
			}
			str+= field.getAccessLevel() + " " 
					+ field.getName() + " : " 
					+ type + "\\l";
			fieldsBlock+= field.getAccessLevel() + " " 
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
					methodsBlock += accessLevel + " ";
				}
				
				str += methodName
						+ "(";
				methodsBlock += methodName
						+ "(";
				
				if(m.getArgs()!=null) {
					args = m.getArgs();
					for(INode arg:args){
						str+=arg.toString()+", ";
						methodsBlock += arg.toString()+", ";
					}
					str = str.substring(0, str.length()-2);
					methodsBlock += methodsBlock.substring(0, methodsBlock.length()-2);
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
