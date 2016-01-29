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
		List<IPattern> patterns = new ArrayList<IPattern>();
		for(IFile file:this.files) {
			if(this.isSingleton(file)) {
				IPattern pattern = new Pattern(PATTERN);
				pattern.addNode(file);
				patterns.add(pattern);
			}
		}
		
		return patterns;
	}
	
	private boolean isSingleton(IFile file) {
		boolean allPrivateConstructors = true;
		boolean hasPrivateInstance = false;
		for(IInnerNode field:file.getFields())
			if(field.getType().equals(file.getName()) && field.getAccessLevel().equals("private"))
				hasPrivateInstance = true;
		for(IMethod method:file.getMethods()) {
			if(method.getName().equals("<init>")){
				if(!method.getType().equals("private"))
					allPrivateConstructors = false;
			}
		}
		return allPrivateConstructors && hasPrivateInstance;
	}
}
