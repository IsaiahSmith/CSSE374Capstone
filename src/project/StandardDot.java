package project;

public class StandardDot implements IDot{

	private StringBuilder stand;

	public StandardDot() {
		this.stand = new StringBuilder();
	}
	
	@Override
	public StringBuilder getDot() {
		stand.append("digraph G { fontname = \"Bitstream Vera Sans\" fontsize = 8 "
				+ "node [fontname = \"Bitstream Vera Sans\" fontsize = 8 shape = \"record\"] "
				+ "edge [fontname = \"Bitstream Vera Sans\" fontsize = 8]");
		
		return stand;
	}

}
