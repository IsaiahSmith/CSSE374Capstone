package project;

public class StandardDot implements IDot{

	@Override
	public StringBuilder getDot() {
		StringBuilder stand = new StringBuilder();
		stand.append("digraph G { fontname = 'Bitstream Vera Sans' fontsize = 8 node [fontname = 'Bitstream Vera Sans' fontsize = 8 shape = 'record'] edge [fontname = 'Bitstream Vera Sans' fontsize = 8]");
		
		return stand;
	}

}
