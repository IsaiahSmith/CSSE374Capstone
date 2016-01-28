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
import builders.ClassDesignBuilder;
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
	
	private DesignFactory designFactory;
	public DesignMaker() {
		this.encoders = new HashMap<String, IEncoder>();
		this.encoders.put("text", new TextEncoder());
		this.encoders.put("dot", new DotEncoder());
		this.encoders.put("sdedit", new SDEditEncoder());
	}
	
	public void make() throws IOException {
		String type;
		List<String> files;
		List<String> signatures;
		int depth;
		Boolean includeAll = false;
		String output;
		IEncoder encoder;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// get type
		System.out.print("Diagram Type (text/uml/sequence): ");
		type = in.readLine();
		
		// get files input
		System.out.print("Files Input: ");
		files = getFiles(in.readLine());
		
		// check type
		if(type.equals("uml")) {
			// if dot ask for includeAll | set designFactory
			System.out.print("Include all files not specified in args?(y/n): ");
			if(in.readLine().equals("y")) {
				includeAll = true;
			}
			designFactory = new UMLDesignBuilderCreator(files, includeAll);
			encoder = encoders.get("dot");
		} else if(type.equals("sequence")) {
			// if sequence ask for method signature file, depth | set designFactory
			System.out.print("File Containing Method Signatures: ");
			signatures = getFiles(in.readLine());
			System.out.print("Sequence Call Depth: ");
			depth = Integer.parseInt(in.readLine());
			
			designFactory = new SequenceDesignBuilderCreator(files, signatures, depth);
			encoder = encoders.get("sdedit");
		} else {
			// if text ask ask for nothing | set designFactory
			designFactory = new UMLDesignBuilderCreator(files, includeAll);
			encoder = encoders.get("text");
		}
		// ask for output file 
		System.out.print("Output File Name: ");
		output = in.readLine();
		
		DesignBuilder design = designFactory.createDesignBuilder();
		IModel model = design.build();
		
		FileOutputStream writer = new FileOutputStream("./output/"+output);

		
		writer.write(encoder.encode(model).toString().getBytes());
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
