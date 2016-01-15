package project;

import java.util.List;

public class DotInheritanceDecorator extends DotDecorator{
	
	ADot toBeDecorated;

	public DotInheritanceDecorator(ADot toBeDecorated, List<ClassBuilder> classes){
		this.toBeDecorated = toBeDecorated;
		this.classes = classes;
	}
	
	public StringBuilder makeInheritance(){
		StringBuilder temp = new StringBuilder("edge [ \n\t\tarrowhead = \"empty\"\n\tstyle = \"filled\"]\n\t");
		List<String> fileNames = getFileNames();
		for(ClassBuilder c : classes){
			if(c.superName != null){
				String left = c.name;
				String right = c.superName;
				System.out.println("SuperName: "+ c.superName);
				System.out.println("fileNames: "+fileNames);
				if(fileNames.contains(c.superName)&& this.includeAll==false) {
					String[] leftSplit = left.split("/");
					String[] rightSplit = right.split("/");
					left = leftSplit[leftSplit.length-1];
					right = rightSplit[rightSplit.length-1];
					temp.append(" " + left + " -> " + right+"\n\t");
				}
			}
		}
		return temp;
	}

	@Override
	public StringBuilder getDot() {
		return toBeDecorated.getDot().append(makeInheritance());
	}
}
