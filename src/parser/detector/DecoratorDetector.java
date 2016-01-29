package parser.detector;

import java.util.ArrayList;
import java.util.List;

import model.IFile;
import model.IPattern;

public class DecoratorDetector extends Detector{
	public static String PATTERN = "Decorator";
	
	public DecoratorDetector(List<IFile> files) {
		this.files = files;
	}
	
	@Override
	public List<IPattern> detect() {
		// TODO: implement this
		return new ArrayList<IPattern>();
	}
}
