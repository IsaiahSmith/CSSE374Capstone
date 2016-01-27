package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import builders.ClassDesignBuilder;
import encoders.dot.ADot;
import encoders.dot.DotClassDecorator;
import encoders.dot.StandardDot;

public class DotClassTest {

	@Test
	public void test() throws IOException {
		String[] c = {"project/main"};
		List<ClassBuilder> classes = DesignBuilder.parse(c);
		ADot dot = new StandardDot();
		dot = new DotClassDecorator(dot, classes);
		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t"
				+ "main [ \n\t\t"
				+ "label = \"{main| | main(arg0: String[]) : void\\l\\l}\" "
				+ "\n\t]\n\t",
				dot.getDot().toString());
	}

}
