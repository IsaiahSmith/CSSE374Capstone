package encoders.dot;

public abstract class ADot {
	boolean includeAll;
	StringBuilder dotFile = new StringBuilder("");
	public StringBuilder getDot() {
		return dotFile;
	}

}
