package project;

public abstract class DotDecorator implements IDot {
	protected IDot toBeDecorated;
	
	public DotDecorator(IDot toBeDecorated) {
		this.toBeDecorated = toBeDecorated;
	}

	public StringBuilder getDot() {
		return this.toBeDecorated.getDot();
	}
}
