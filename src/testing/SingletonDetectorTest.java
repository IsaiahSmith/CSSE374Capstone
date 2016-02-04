package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.IPattern;
import nodes.FieldNode;
import nodes.FileNode;
import nodes.MethodNode;
import parser.FileParser;
import parser.Parser;
import parser.PatternParser;
import parser.detector.Detector;
import parser.detector.SingletonDetector;

public class SingletonDetectorTest {

	private IInnerNode makeStaticFieldOfTypeSelf(IFile file) {
		IInnerNode field = new FieldNode();
		field.setName("singletonInstance");
		field.setType(file.getName());
		field.addModifier("static");
		field.setVisibility("private");
		return field;
		
	}
	
	private IMethod makePrivateConstructor() {
		IMethod method = new MethodNode();
		method.setVisibility("private");
		method.setType("void");
		method.setName("<init>");
		return method;
	}
	
	private IMethod makeGetInstance(IFile file) {
		IMethod method = new MethodNode();
		method.setName("getInstance");
		method.addModifier("static");
		method.setType(file.getName());
		method.setVisibility("public");
		return method;
	}
	
	@Test
	public void TestDetectEmptyClass() {
		IFile file = new FileNode();
		List<IFile> files = new ArrayList<IFile>();
		files.add(file);
		Detector singleton = new SingletonDetector(files);
		List<IPattern> patterns = singleton.detect();
		assertTrue( patterns.isEmpty());
	}
	
	@Test
	public void TestNonSingletonClass() {
		IFile file = new FileNode();
		file.setName("ClassA");
		IInnerNode field = new FieldNode();
		field.setType("ClassB");
		field.setVisibility("public");
		file.addField(field);
		IMethod method = new MethodNode();
		method.setType("classB");
		method.setVisibility("private");
		method.setName("<init>");
		file.addMethod(method);
		List<IFile> files = new ArrayList<IFile>();
		files.add(file);
		Detector singleton = new SingletonDetector(files);
		List<IPattern> patterns = singleton.detect();
		assertTrue( patterns.isEmpty());
	}
	
	@Test
	public void TestSingleSingletonClass() {
		IFile file = new FileNode();
		file.setName("Singleton");
		file.setType("class");
		IInnerNode field = makeStaticFieldOfTypeSelf(file);
		IMethod cotr = this.makePrivateConstructor();
		IMethod getInstance = this.makeGetInstance(file);
		file.addField(field);
		file.addMethod(cotr);
		file.addMethod(getInstance);
		List<IFile> files = new ArrayList<IFile>();
		files.add(file);
		
		Detector singletonD = new SingletonDetector(files);
		List<IPattern> patterns = singletonD.detect();
		assertTrue(!patterns.isEmpty());
		
	}
	
	@Test
	public void TestRuntime() {
		List<String> fileNames = new ArrayList<String>();
		List<String> singletonPattern = new ArrayList<String>();
		singletonPattern.add("Singleton");
		fileNames.add("java/lang/Runtime");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, singletonPattern).parse();
		
		assertTrue(!patterns.isEmpty());
	}
	
	@Test
	public void TestDesktop() {
		List<String> fileNames = new ArrayList<String>();
		List<String> singletonPattern = new ArrayList<String>();
		singletonPattern.add("Singleton");
		fileNames.add("java/awt/Desktop");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, singletonPattern).parse();
		
		assertTrue(!patterns.isEmpty());
	}
	@Test
	public void TestCalendar() {
		List<String> fileNames = new ArrayList<String>();
		List<String> singletonPattern = new ArrayList<String>();
		singletonPattern.add("Singleton");
		fileNames.add("java/util/Calendar");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, singletonPattern).parse();
		
		assertTrue(patterns.isEmpty());
	}
	@Test
	public void FilterInputStream() {
		List<String> fileNames = new ArrayList<String>();
		List<String> singletonPattern = new ArrayList<String>();
		singletonPattern.add("Singleton");
		fileNames.add("java/io/FilterInputStream");
		fileNames.add("java/io/InputStream");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, singletonPattern).parse();
		
		assertTrue(patterns.isEmpty());
	}
	
	@Test
	public void TestParsedSingleton() {
		String[] fileNames = {"test_input/Singleton.class"};
		List<String> singletonPattern = new ArrayList<String>();
		singletonPattern.add("Singleton");
		
		List<IFile> files = new FileParser(fileNames).parse();
		List<IPattern> patterns = new PatternParser(files, singletonPattern).parse();
		
		assertTrue(!patterns.isEmpty());
	}

}
