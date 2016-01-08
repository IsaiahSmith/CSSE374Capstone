package project;

public class DotAssociationDecorator extends DotDecorator {

	public DotAssociationDecorator(IDot toBeDecorated) {
		super(toBeDecorated);
	}

	@Override
	public StringBuilder getDot() {
		return null;
	}

}
