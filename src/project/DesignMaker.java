package project;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
		//TODO Parse data structure for class
		
		IEncoder enc = encoders.get(encodeType);
		FileOutputStream writer = new FileOutputStream("./output/"+outputName);
		//TODO Write the ClassObject here
		//writer.write(enc.encode(ClassObject).toString().getBytes());
		writer.close();
	}
}