package project;

import java.util.List;

public class DotImplementsDecorator extends DotDecorator {
	
	private List<ClassBuilder> classes;
	private StringBuilder stringBuild;

	public DotImplementsDecorator(IDot toBeDecorated, List<ClassBuilder> classes){
		super(toBeDecorated);
		this.classes = classes;
		this.stringBuild = new StringBuilder();
		makeImplements();
	}
	
	public void makeImplements(){
		stringBuild.append("edge [ arrowhead = 'empty' style= 'dashed']");
		for(ClassBuilder c : classes){
			if(c.inter != null){
				for(String i : c.inter){
					stringBuild.append(c.name + "->" + i + "\\l");
				}
			}
		}
	}

	@Override
	public StringBuilder getDot() {
		return stringBuild;
	}
}
