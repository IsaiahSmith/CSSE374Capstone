package project;

public abstract class DotDecorator implements IDot {
	protected IDot toBeDecorated;
	private StringBuilder stringToBuild;
	
	public DotDecorator(IDot toBeDecorated) {
		this.toBeDecorated = toBeDecorated;
		this.stringToBuild = new StringBuilder();
	}
	
}
