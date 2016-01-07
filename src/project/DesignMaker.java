package project;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignMaker {
	private DesignBuilder design;
	private Map<String, IEncoder> encoders;
	
	public DesignMaker() {
		this.encoders = new HashMap<String, IEncoder>();
		encoders.put("text", new TextEncoder());
		encoders.put("dot", new DotEncoder());
		this.design = new DesignBuilder();
	}
	
	public void make(String[] files) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Encode Type (text/dot): ");
		String encodeType = in.readLine();
		System.out.print("Output File Name: ");
		String outputName = in.readLine();
		List<ClassBuilder> Classes = design.parse(files);
		
		IEncoder enc = encoders.get(encodeType);
		FileOutputStream writer = new FileOutputStream("./output/"+outputName);
		writer.write(enc.encode(Classes).toString().getBytes());
		writer.close();
		System.out.println("Done.");
	}
}
