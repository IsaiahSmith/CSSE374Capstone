package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import encoders.dot.ADot;
import encoders.dot.StandardDot;

public class dotStandardTest {

	@Test
	public void TestIncludeAllFalse() {
		ADot dot = new StandardDot(false);
		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t",
				dot.getDot().toString());
	}
	
	@Test
	public void TestIncludeAllTrue() {
		ADot dot = new StandardDot(true);
		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t",
				dot.getDot().toString());
	}
	
	

}
