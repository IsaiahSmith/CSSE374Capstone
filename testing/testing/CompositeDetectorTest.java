package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.IFile;
import model.IPattern;
import parser.FileParser;
import parser.PatternParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class CompositeDetectorTest {

	@Test
	public void testMedCase() {
		List<String> fileNames = new ArrayList<String>();
		List<String> compositePattern = new ArrayList<String>();
		compositePattern.add("Composite");
		
		Charset charset = Charset.forName("US-ASCII");
		File file = new File("./input/testMedComp.txt");
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)){
			String line = null;
			while((line = reader.readLine()) != null){
				fileNames.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, compositePattern).parse();
		
		assertEquals(5, patterns.size());
	}
	
	@Test
	public void testLabSevenDashTwo() {
		List<String> fileNames = new ArrayList<String>();
		List<String> compositePattern = new ArrayList<String>();
		compositePattern.add("Composite");

		Charset charset = Charset.forName("US-ASCII");
		File file = new File("./input/lab7-2.txt");
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)){
			String line = null;
			while((line = reader.readLine()) != null){
				fileNames.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, compositePattern).parse();
		
		assertEquals(4, patterns.size());
	}
	
	@Test
	public void testProject() {
		List<String> fileNames = new ArrayList<String>();
		List<String> compositePattern = new ArrayList<String>();
		compositePattern.add("Composite");
		
		Charset charset = Charset.forName("US-ASCII");
		File file = new File("./input/project.txt");
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)){
			String line = null;
			while((line = reader.readLine()) != null){
				fileNames.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, compositePattern).parse();
		
		assertEquals(8, patterns.size());
	}
	
	@Test
	public void testJavaSwing() {
		List<String> fileNames = new ArrayList<String>();
		List<String> compositePattern = new ArrayList<String>();
		compositePattern.add("Composite");
		
		Charset charset = Charset.forName("US-ASCII");
		File file = new File("./input/swingComp.txt");
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)){
			String line = null;
			while((line = reader.readLine()) != null){
				fileNames.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, compositePattern).parse();
		
		assertEquals(2, patterns.size());
	}
}
