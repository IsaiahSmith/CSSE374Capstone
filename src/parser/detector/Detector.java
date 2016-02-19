package parser.detector;

import java.util.Set;

import model.IFile;
import model.IPattern;

public abstract class Detector {
	
	Set<IFile> files;
	
	public abstract Set<IPattern> detect();
	public static String sanitize(String input) {
		return input.replaceAll("\\.", "_").replaceAll("/", "_");
	}
}
