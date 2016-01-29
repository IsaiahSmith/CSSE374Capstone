package parser.detector;

import java.util.ArrayList;
import java.util.List;

import model.IFile;
import model.IPattern;

public class AdapterDetector extends Detector{
	public static String PATTERN = "Adapter";
	
	public AdapterDetector(List<IFile> files) {
		this.files = files;
	}
	
	@Override
	public List<IPattern> detect() {
		// TODO: implement this
		return new ArrayList<IPattern>();
	}
}
