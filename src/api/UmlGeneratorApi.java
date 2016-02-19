package api;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import model.IModel;

public class UmlGeneratorApi extends Observable implements api{
	
	private List<String> RenderedRoots;
	private IModel model;
	private String dotPath;
	private String outputDir;
	private BufferedImage UML; // This might need to change, it will need to hold a png
	
	@Override
	public Map<String, List<String>> getPatternRoots() {
		
		return null;
	}

	@Override
	public void addPatternRoot(String patternRoot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPattern(String pattern) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePatternRoot(String patternRoot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePattern(String pattern) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPatternStyle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPatternStyles() {
		// TODO Auto-generated method stub
		
	}

}
