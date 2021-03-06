package parser.detector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.IFile;
import model.IInnerNode;
import model.IMethod;
import model.IPattern;
import nodes.Pattern;

public class SingletonDetector extends Detector{

	public static String PATTERN = "Singleton";
	private int instance;
	
	public SingletonDetector(Set<IFile> files) {
		this.files = files;
		this.instance = -1;
	}
	
	@Override
	public Set<IPattern> detect() {
		Set<IPattern> patterns = new HashSet<IPattern>();
		for(IFile file:this.files) {
			this.instance++;
			if(this.isSingleton(file)) {
				IPattern pattern = new Pattern(PATTERN);
				pattern.setName(PATTERN);
				pattern.setNode(file.getName());
				pattern.setRoot();
				pattern.setInstance(this.instance);
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
