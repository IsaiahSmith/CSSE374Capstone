package parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import model.IFile;
import model.IPattern;
import parser.detector.AdapterDetector;
import parser.detector.CompositeDetector;
import parser.detector.DecoratorDetector;
import parser.detector.Detector;
import parser.detector.SingletonDetector;

public class PatternParser extends Observable implements Parser<IPattern> {
	private Map<String, Detector> detectors;
	private Set<String> patternTypes;
	
	public PatternParser(Set<IFile> files, Set<String> patternTypes) {
		this.patternTypes = patternTypes;
		this.detectors = new HashMap<String, Detector>();
		this.detectors.put(SingletonDetector.PATTERN, new SingletonDetector(files));
		this.detectors.put(DecoratorDetector.PATTERN, new DecoratorDetector(files));
		this.detectors.put(AdapterDetector.PATTERN, new AdapterDetector(files));
		this.detectors.put(CompositeDetector.PATTERN, new CompositeDetector(files));
	}

	@Override
	public Set<IPattern> parse() {
		Set<IPattern> patterns = new HashSet<IPattern>();
		for(String type : this.patternTypes){
			setChanged();
			this.notifyObservers("Detecting: "+type);
			patterns.addAll(this.detectors.get(type).detect());
		}
		return patterns;
	}

}
