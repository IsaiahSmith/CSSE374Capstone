package parser.detector;

import java.util.ArrayList;
import java.util.List;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.INode;
import model.IPattern;
import nodes.Pattern;

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
