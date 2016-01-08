package project;

import java.util.List;

public class DotImplementsDecorator extends DotDecorator {
	ADot toBeDecorated;
	private List<ClassBuilder> classes;

	public DotImplementsDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeImplements(){
		StringBuilder temp = new StringBuilder("edge [ arrowhead = 'empty' style= 'dashed']");
	
		for(ClassBuilder c : classes){
			if(c.inter != null){
				for(String i : c.inter){
					temp.append(c.name + "->" + i + "\\l");
				}
			}
		}
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeImplements());
	}
}
