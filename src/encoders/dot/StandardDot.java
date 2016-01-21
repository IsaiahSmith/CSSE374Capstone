package encoders.dot;

public class StandardDot extends ADot{

	public StandardDot(boolean includeAll) {
		this.includeAll = includeAll;
		dotFile = new StringBuilder("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t");
	}

}