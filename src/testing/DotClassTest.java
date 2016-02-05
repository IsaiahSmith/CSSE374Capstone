package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import encoders.dot.ADot;
import encoders.dot.Dot;
import encoders.dot.DotFileDecorator;
import encoders.dot.StandardDot;
import model.IFile;
import model.IInnerNode;
import model.IModel;
import nodes.FieldNode;
import nodes.FileNode;
import nodes.Model;

public class DotClassTest {

	//IModel model = new Model();
	
	@Test
	public void TestEmptyModel() {
		//Dot dot = new DotFileDecorator(new StandardDot(), this.model);
//		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
//				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
//				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t", 
//				dot.getDot().toString());
	}
	
	@Test
	public void TestSingleClassModel() {
		IFile classA = new FileNode();
		classA.setName("ClassA");
		//this.model.addFile(classA);
		//ADot dot = new DotFileDecorator(new StandardDot(false), this.model);
//		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
//				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
//				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t"
//				+ "ClassA [ \n"
//				+ "\t\tlabel = \"{ClassA||\\l}\" \n"
//				+ "\t]\n"
//				+ "\t", 
//				dot.getDot().toString());
	}
	@Test
	public void TestSingleClassWithSingleFieldModel() {
//		IFile classA = new FileNode();
//		classA.setName("ClassA");
//		IInnerNode field = new FieldNode();
//		field.setName("fieldA");
//		classA.addField(field);
//		this.model.addFile(classA);
//		
//		ADot dot = new DotClassDecorator(new StandardDot(false), this.model);
//		assertEquals("digraph G {\n\tfontname = \"Bitstream Vera Sans\" \n\tfontsize = 8 "
//				+ "\n\tnode [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8 \n\t\tshape = \"record\"\n\t] "
//				+ "\n\tedge [\n\t\tfontname = \"Bitstream Vera Sans\" \n\t\tfontsize = 8\n\t]\n\t"
//				+ "ClassA [ \n"
//				+ "\t\tlabel = \"{ClassA||\\l}\" \n"
//				+ "\t]\n"
//				+ "\t", 
//				dot.getDot().toString());
	}
	

}
