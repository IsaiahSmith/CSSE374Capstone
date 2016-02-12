package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IFile;
import model.IPattern;
import parser.detector.AdapterDetector;
import parser.detector.CompositeDetector;
import parser.detector.DecoratorDetector;
import parser.detector.Detector;
import parser.detector.SingletonDetector;

public class PatternParser implements Parser<IPattern> {
	private Map<String, Detector> detectors;
	private List<String> patternTypes;
	
	public PatternParser(List<IFile> files, List<String> patternTypes) {
		this.patternTypes = patternTypes;
		this.detectors = new HashMap<String, Detector>();
		this.detectors.put(SingletonDetector.PATTERN, new SingletonDetector(files));
		this.detectors.put(DecoratorDetector.PATTERN, new DecoratorDetector(files));
		this.detectors.put(AdapterDetector.PATTERN, new AdapterDetector(files));
		this.detectors.put(CompositeDetector.PATTERN, new CompositeDetector(files));
	}

	@Override
	public List<IPattern> parse() {
		List<IPattern> patterns = new ArrayList<IPattern>();
		for(String type : this.patternTypes){
			patterns.addAll(this.detectors.get(type).detect());
		}
		return patterns;
	}

}
