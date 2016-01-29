package designs;

import java.util.ArrayList;
import java.util.List;

import model.IModel;
import nodes.Model;

public class UMLDesignBuilder implements DesignBuilder {

	private List<String> fileNames;
	private boolean includeAll;
	private List<String> arrowTypes;
	private List<String> patternTypes;
	public UMLDesignBuilder(List<String> fileNames, boolean includeAll) {
		this.includeAll = includeAll;
		this.fileNames = fileNames;
		
		// Currently this is set Statically but in the
		// future we can set this to be dynamically set at
		// runtime
		this.arrowTypes = new ArrayList<String>();
		this.arrowTypes.add("Association");
		this.arrowTypes.add("Implements");
		this.arrowTypes.add("Inheritance");
		this.arrowTypes.add("Uses");
		
		this.patternTypes = new ArrayList<String>();
		this.patternTypes.add("Singleton");
		this.patternTypes.add("Decorator");
		this.patternTypes.add("Adapter");
	}
	
	@Override
	public IModel build() {
		
		return new Model
				.Builder(fileNames)
				.arrows(arrowTypes, includeAll)
				.patterns(patternTypes)
				.build();
	}

}
