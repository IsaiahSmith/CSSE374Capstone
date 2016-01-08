package project;

public class DotImplementsDecorator extends DotDecorator {
	
	public DotImplementsDecorator(IDot toBeDecorated){
		super(toBeDecorated);
	}
	
	public StringBuilder makeImplements(){
		String edgeSyn = "edge [ arrowhead = 'empty' style= 'dashed']";
		return null;
	}

	@Override
	public StringBuilder getDot() {
		// TODO Auto-generated method stub
		return null;
	}
}
