package api;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import designs.UMLDesignBuilder;
import encoders.EncoderBuilder;
import encoders.IEncoder;
import encoders.IEncoderBuilder;
import model.IFile;
import model.IModel;
import model.IPattern;
import nodes.Model;

public class UmlGeneratorApi extends Observable implements api{
	
	private Set<String> classList;
	private Set<String> patternList;
	private Set<String> arrowList;
	
	
	private Set<String> renderedRoots;
	private IModel model;
	private String dotPath;
	private String outputDir;
	private String tempDotFile;
	private String tempPngFile;
	private Map<String, List<String>> patternRoots;
	private IEncoder encoder;
	private UMLDesignBuilder designBuilder;
	private Image image;
	
	// this map will simply be used inorder to speed up image generation
	private Map<String, IFile> files;
	
	public UmlGeneratorApi() {
		this.classList = new HashSet<String>();
		this.patternList = new HashSet<String>();
		this.arrowList = new HashSet<String>();
		
		this.renderedRoots = new HashSet<String>();
		this.model = new Model();
		this.dotPath = new String();
		this.outputDir = new String();
		this.tempDotFile = new String();
		this.tempPngFile = new String();
		this.patternRoots = new HashMap<String, List<String>>();
		this.encoder = null;
		this.designBuilder = null;
		this.image =  null;
	}
	
	@Override
	public boolean loadConfig(File config) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject base = (JSONObject) parser.parse(new FileReader(config));
			// Set Path variables
			this.outputDir = (String) base.get("Output-Directory");
			this.dotPath = (String) base.get("Dot-Path");
			this.tempDotFile = (String) base.get("Temp-Dot-Path");
			this.tempPngFile = (String) base.get("Temp-png-Path");
			
			// Set Model variables
			// TODO: change so this also creates the styles for each variable
			JSONArray classes = (JSONArray) base.get("Input-Classes");
			for(Object cls:classes){
				this.classList.add((String) cls);
			}
			
			JSONArray patterns = (JSONArray) base.get("Input-Patterns");
			for(Object pattern:patterns){
				this.patternList.add((String) pattern);
			}
			
			JSONArray arrows = (JSONArray) base.get("Input-Arrows");
			for(Object arrow:arrows){
				this.arrowList.add((String) arrow);
			}
			
			//Right now I will be statically setting the design builder to be UML only.
			this.designBuilder = new UMLDesignBuilder(this.classList, this.arrowList, this.patternList);
			
			IEncoderBuilder encodeSelect = new EncoderBuilder();
			// this.encoder.setStyles() TODO this
			this.encoder = encodeSelect.Build((String)base.get("Encoder"));
			
		} catch (IOException | ParseException e) {
			return false;
		}
		
		return true;
	}
	
	public Map<String, List<String>> getPatternRoots() {
		if(this.patternRoots == null) {
			this.setPatternRoots();
		}
		return this.patternRoots;
	}
	
	private void setPatternRoots() {
		List<IPattern> roots = new ArrayList<IPattern>();
		Map<String, List<String>> patternRoots = new HashMap<String, List<String>>();
		for(IPattern pattern: this.model.getPatterns()){
			if(pattern.isRoot()) {
				roots.add(pattern);
			}
		}
		
		for(IPattern root:roots) {
			List<String> patterns = new ArrayList<String>();
			for(IPattern pattern:this.model.getPatterns()){
				if(root.getType().equals(pattern.getType()) && root.getInstance() == pattern.getInstance()) {
					patterns.add(pattern.getNode());
				}
			}
			patternRoots.put(root.getNode(), patterns);
		}
		
		this.patternRoots = patternRoots;
	}

	@Override
	public void addPatternRoot(String patternRoot) {
		this.renderedRoots.add(patternRoot);
		this.updateUML();
	}

	@Override
	public void addPattern(String pattern) {
		for(IPattern p:this.model.getPatterns()) {
			if(p.getType().equals(pattern) && p.isRoot()) {
				this.renderedRoots.add(pattern);
			}
		}
		this.updateUML();
	}

	@Override
	public void removePatternRoot(String patternRoot) {
		this.renderedRoots.remove(patternRoot);
		this.updateUML();
	}

	@Override
	public void removePattern(String pattern) {
		for(IPattern p:this.model.getPatterns()) {
			if(p.getType().equals(pattern) && p.isRoot() && this.renderedRoots.contains(pattern)) {
				this.renderedRoots.remove(pattern);
			}
		}
		this.updateUML();
	}

	@Override
	public void getPatternStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPatternStyles() {
		// TODO Auto-generated method stub
		
	}
	
	private void updateUML() {
		IModel temp = new Model();
		temp.addArrows(model.getArrows());
		temp.addPatterns(model.getPatterns());
		Set<IFile> tempFiles = new HashSet<IFile>();
		for(String root:renderedRoots) {
			for(String file:patternRoots.get(root)) {
				tempFiles.add(files.get(file));
			}
		}
		//create graphvizfile
		makeFile(this.encoder.encode(temp).toString(), this.tempDotFile);
		createGraphViz(this.dotPath, this.tempDotFile, this.tempPngFile);
		updateImage(this.tempPngFile);
	}
	
	private void makeFile(String digraph, String resultPath) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(resultPath, "UTF-8");
			writer.print(digraph);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void createGraphViz(String DotFile, String tempDotFile, String tempPngPath) {
		String command = DotFile + " -Tpng " + tempDotFile + " -o " + tempPngPath;
		Process createGraphImage = null;
		try {
			createGraphImage = Runtime.getRuntime().exec(command);
			createGraphImage.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void updateImage(String tempPngPath) {
		//TODO need to update the image field in here too
		ImageIcon image = new ImageIcon(tempPngPath);
		setChanged();
		this.notifyObservers(image);
	}
	
	public void buildModel() {
		this.designBuilder.build();
	}
	
	public void subscribeToBuild(Observer observer) {
		this.designBuilder.addObserver(observer);
	}
	
	public String getOutputDir() {
		return this.outputDir;
	}
	
	public void saveImage(String path) {
		BufferedImage img = (BufferedImage) this.image;
		File outputfile = new File(path);
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getInputFileName() {
		// TODO get the name of the config file
		return null;
	}

	public boolean hasConfigFile() {
		// TODO check to see if there's a config file ready to go
		return true;
	}

}
