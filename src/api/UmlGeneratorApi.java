package api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import model.IModel;
import model.IPattern;
import nodes.Model;

public class UmlGeneratorApi extends Observable implements api{
	
	private Set<String> renderedRoots;
	private IModel model;
	private String dotPath;
	private String outputDir;
	private BufferedImage UML; // This might need to change, it will need to hold a png
	private Map<String, List<String>> patternRoots;
	
	@Override
	public void loadConfig(File file) {
		// TODO Auto-generated method stub
		
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
		// TODO create this
	}

	public String getInputFileName() {
		// TODO get the name of the config file
		return null;
	}

	public boolean hasConfigFile() {
		// TODO check to see if there's a config file ready to go
		return true;
	}

	public void readConfigFile() {
		// TODO Auto-generated method stub
		
	}

}
