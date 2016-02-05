package parser.detector;

import java.util.ArrayList;
import java.util.List;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.IPattern;
import nodes.Pattern;

public class SingletonDetector extends Detector{

	public static String PATTERN = "Singleton";
	
	public SingletonDetector(List<IFile> files) {
		this.files = files;
	}
	
	@Override
	public List<IPattern> detect() {
		List<IPattern> patterns = new ArrayList<IPattern>();
		for(IFile file:this.files) {
			if(this.isSingleton(file)) {
				IPattern pattern = new Pattern(PATTERN);
				pattern.setName(PATTERN);
				pattern.setNode(file.getName());
				patterns.add(pattern);
			}
		}
		
		return patterns;
	}
	
	private boolean isSingleton(IFile file) {
		boolean hasAPrivateStaticFieldOfTypeSelf = false;
		boolean allConstructorsArePrivate = true;
		boolean hasAtLeastOnePublicStaticMethodOfTypeSelf = false;
		boolean isClass = false;
		if(file.getType().equals("class")) {
			isClass=true;
		}
		for(IInnerNode field:file.getFields()) {
			if(sanitize(field.getType()).equals(sanitize(file.getName())) && field.getVisibility().equals("private") && field.getModifiers().get(0).equals("static")) {
				
				hasAPrivateStaticFieldOfTypeSelf = true;
			}
		}
		for(IMethod method:file.getMethods()) {
			//System.out.println(method);
			if(method.getName().equals("<init>")){
				if(!method.getVisibility().equals("private")) {
					allConstructorsArePrivate = false;
				}
			}
			if(method.getVisibility().equals("public") && method.getModifiers().contains("static") && sanitize(method.getType()).equals(sanitize(file.getName()))) {
				hasAtLeastOnePublicStaticMethodOfTypeSelf = true;
			}
		}
		return hasAPrivateStaticFieldOfTypeSelf && allConstructorsArePrivate && hasAtLeastOnePublicStaticMethodOfTypeSelf && isClass;
	}

}
