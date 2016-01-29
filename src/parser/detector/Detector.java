package parser.detector;

import java.util.List;

import model.IFile;
import model.IPattern;

public abstract class Detector {
	
	List<IFile> files;
	
	public abstract List<IPattern> detect();
	public static String sanitize(String input) {
		return input.replaceAll("\\.", "_").replaceAll("/", "_");
	}
}
