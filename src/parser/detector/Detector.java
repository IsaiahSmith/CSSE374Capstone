package parser.detector;

import java.util.List;

import model.IFile;
import model.IPattern;

public abstract class Detector {
	
	List<IFile> files;
	
	public abstract List<IPattern> detect();
}
