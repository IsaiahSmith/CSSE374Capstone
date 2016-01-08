package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import project.ADot;
import project.StandardDot;

public class dotStandardTest {

	@Test
	public void test() {
		ADot dot = new StandardDot();
		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t",
				dot.getDot().toString());
	}

}
