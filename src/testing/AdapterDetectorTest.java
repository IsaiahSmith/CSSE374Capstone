package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.IFile;
import model.IPattern;
import parser.FileParser;
import parser.PatternParser;


public class AdapterDetectorTest {

	
	@Test
	public void TestWeekTwoProblem() {
		List<String> fileNames = new ArrayList<String>();
		List<String> adapterPattern = new ArrayList<String>();
		adapterPattern.add("Adapter");
		fileNames.add("problem.DecryptionInputStream");
		fileNames.add("problem.EncryptionOutputStream");
		fileNames.add("problem.IDecryption");
		fileNames.add("problem.IEncryption");
		fileNames.add("problem.SubstitutionCipher");
		fileNames.add("problem.TextEditorApp");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, adapterPattern).parse();
		
		assertTrue(patterns.size() == 0);
	}
	
	@Test
	public void TestWeekTwoOneStarBuzz() {
		List<String> fileNames = new ArrayList<String>();
		List<String> adapterPattern = new ArrayList<String>();
		adapterPattern.add("Adapter");
		fileNames.add("headfirst.decorator.starbuzz.Beverage");
		fileNames.add("headfirst.decorator.starbuzz.CondimentDecorator");
		fileNames.add("headfirst.decorator.starbuzz.DarkRoast");
		fileNames.add("headfirst.decorator.starbuzz.Decaf");
		fileNames.add("headfirst.decorator.starbuzz.Espresso");
		fileNames.add("headfirst.decorator.starbuzz.HouseBlend");
		fileNames.add("headfirst.decorator.starbuzz.Milk");
		fileNames.add("headfirst.decorator.starbuzz.Mocha");
		fileNames.add("headfirst.decorator.starbuzz.Soy");
		fileNames.add("headfirst.decorator.starbuzz.StarbuzzCoffee");
		fileNames.add("headfirst.decorator.starbuzz.Whip");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, adapterPattern).parse();
		
		assertTrue(patterns.size() == 0);
	}
	
	@Test
	public void TestWeekFiveOne() {
		List<String> fileNames = new ArrayList<String>();
		List<String> adapterPattern = new ArrayList<String>();
		adapterPattern.add("Adapter");
		fileNames.add("java/util/Enumeration");
		fileNames.add("java/util/Iterator");
		fileNames.add("problem.client.IteratorToEnumerationAdapter");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, adapterPattern).parse();
		
		assertTrue(patterns.size() == 3);
	}
	
	@Test
	public void TestInputStreamReader() {
		List<String> fileNames = new ArrayList<String>();
		List<String> adapterPattern = new ArrayList<String>();
		adapterPattern.add("Adapter");
		fileNames.add("java/io/InputStreamReader");
		fileNames.add("java/io/Closeable");
		fileNames.add("java/lang/AutoCloseable");
		fileNames.add("java/lang/Readable");
		fileNames.add("java/io/Reader");
		fileNames.add("java/io/FileReader");
		fileNames.add("java.io.InputStream");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, adapterPattern).parse();
		
		assertEquals(0, patterns.size());
	}

	@Test
	public void TestOutputStreamWriter() {
		List<String> fileNames = new ArrayList<String>();
		List<String> adapterPattern = new ArrayList<String>();
		adapterPattern.add("Adapter");
		fileNames.add("java/io/OutputStreamWriter");
		fileNames.add("java/io/Closeable");
		fileNames.add("java/lang/AutoCloseable");
		fileNames.add("java/io/Flushable");
		fileNames.add("java/lang/Appendable");
		fileNames.add("java/io/FileWriter");
		fileNames.add("java/io/Writer");
		fileNames.add("java.io.BufferedWriter");
		fileNames.add("java.io.OutputStream");
		fileNames.add("java.io.PrintWriter");
		fileNames.add("java.lang.Object");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, adapterPattern).parse();
		
		assertTrue(patterns.size() == 0);
	}
	
	@Test
	public void TestMouserAdapter() {
		List<String> fileNames = new ArrayList<String>();
		List<String> adapterPattern = new ArrayList<String>();
		adapterPattern.add("Adapter");
		fileNames.add("java.awt.event.MouseAdapter");
		fileNames.add("java.awt.event.MouseListener");
		fileNames.add("java.awt.event.MouseMotionListener");
		fileNames.add("java.awt.event.MouseWheelListener");
		fileNames.add("java.util.EventListener");
		fileNames.add("java.awt.event.MouseEvent");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, adapterPattern).parse();
		
		assertTrue(patterns.size() == 0);
	}
}
