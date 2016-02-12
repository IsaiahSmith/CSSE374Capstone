package encoders.dot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.IModel;
import model.INode;
import model.IPattern;

public class DotFileDecorator extends DotDecorator{

	private Map<String, String> patternDraw;
	
	public DotFileDecorator(Dot dot, IModel model) {
		super(dot, model);
		this.patternDraw = new HashMap<String, String>();
		this.patternDraw.put("Singleton", "color=blue");
		this.patternDraw.put("Decorator", "fillcolor=green, style=filled");
		this.patternDraw.put("Decorator:Component", "fillcolor=green, style=filled");
		this.patternDraw.put("Adapter", "fillcolor=red, style=filled");
		this.patternDraw.put("Adapter:Adaptee", "fillcolor=red, style=filled");
		this.patternDraw.put("Adapter:Target", "fillcolor=red, style=filled");
		this.patternDraw.put("Composite", "fillcolor=yellow, style=filled");
		this.patternDraw.put("Composite:Component", "fillcolor=yellow, style=filled");
		this.patternDraw.put("Composite:Leaf", "fillcolor=yellow, style=filled");
	}
	
	public StringBuilder getDot() {
		String str = new String();
		for(IFile file:this.model.getFiles()) {
			str+=composeFile(file);
		}
		return super.getDot().append(str);
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
				+ "\\l}\"";
		for(IPattern pattern:model.getPatterns()) {
			
			if(pattern.getNode().equals(node.getName()))
			{
				composedStr+=",\n\t\t"
						+patternDraw.get(pattern.getName());
			}
		}
		composedStr+="\n\t]\n\t";
		
		return composedStr;
	}
	
	private String buildNameBlock(String name, String fileType) {
		String nameBlock = new String();
		String[] nameSplit = name.split("_");
		if(fileType.equals("interface")){
			nameBlock += "\\<\\<interface\\>\\>\\n";
		} else if(fileType.equals("abstract")) {
			nameBlock += "\\<\\<abstract\\>\\>\\n";
		}
		nameBlock += nameSplit[nameSplit.length-1];
		for(IPattern pattern:this.model.getPatterns()){
			if(name.equals(pattern.getNode())) {
				String[] patternNameSplit = pattern.getName().split(":");
				nameBlock += "\\n\\<\\<"+patternNameSplit[patternNameSplit.length-1]+"\\>\\>";
			}
		}
		
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
			fieldsBlock+= field.getVisibility() + " " 
					+ field.getName() + " : "
					+ type + "\\l";
		}
		return fieldsBlock;
	}

	private String buildMethodsBlock(List<IMethod> methods) {
		String methodsBlock = new String();
		for(IMethod m : methods){
			String accessLevel = m.getVisibility();
			String methodName = m.getName();
			String returnType = m.getType();
			List<INode> args;
			String[] returnTypeSplit = returnType.split("_");
			returnType = returnTypeSplit[returnTypeSplit.length-1];
			
			if(m.getVisibility()==null) {
				accessLevel="";
			}
			
			if(!methodName.equals("<init>")&&!methodName.equals("<clinit>")){
				
				if(m.getVisibility()!=null) {
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
}
