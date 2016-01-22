package runner;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import builders.IDesignBuilder;
import builders.SequenceDesignBuilder;
import builders.UMLDesignBuilder;
import encoders.IEncoder;
import encoders.dot.DotEncoder;
import encoders.sdedit.SDEditEncoder;
import encoders.text.TextEncoder;
import model.IFile;
import model.IMethod;
import model.IModel;
import nodes.MethodNode;

public class DesignMaker {
	private IDesignBuilder design;
	private Map<String, IEncoder> encoders;
	
	
	public DesignMaker() {
		this.encoders = new HashMap<String, IEncoder>();
		encoders.put("text", new TextEncoder());
		encoders.put("dot", new DotEncoder());
		encoders.put("SDEdit", new SDEditEncoder());
		
	}
	
	public void make() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input File: ");
		String input = in.readLine();
		System.out.print("Encode Type (text/dot/SDEdit): ");
		String encodeType = in.readLine();
		boolean includeAll = false;
		System.out.print("Include all files not specified in args?(y/n): ");
		String include = in.readLine();
		if(include.equals("y"))
			includeAll = true;
		System.out.print("Output File Name: ");
		String outputName = in.readLine();
		
		//Testing area for SequenceDesignBuilder
		//this.design = new UMLDesignBuilder(getFiles(input));
		List<String> methodArgs = new ArrayList<String>();
		this.design = new SequenceDesignBuilder("nodes/MethodNode", "test", methodArgs, 5);
		
		IModel model = this.design.build();
		
		IEncoder enc = encoders.get(encodeType);
		FileOutputStream writer = new FileOutputStream("./output/"+outputName);

		writer.write(enc.encode(model, includeAll).toString().getBytes());
		writer.close();
		System.out.println("Done.");
	}
	
	/**
	 * Takes in an input file to populate files
	 * FORMAT: <File_Name>\n
	 * 
	 * @param input
	 * @return files
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	private List<String> getFiles(String input) throws IOException {
		FileReader reader = new FileReader("./input/" + input);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = null;
		List<String> files = new ArrayList<String>();
		while((line = bufferedReader.readLine()) != null) {
			files.add(line);
		}
		return files;
	}
}
