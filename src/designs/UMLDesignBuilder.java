package designs;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import model.IArrow;
import model.IFile;
import model.IModel;
import model.IPattern;
import nodes.Model;
import parser.ArrowParser;
import parser.FileParser;
import parser.Parser;
import parser.PatternParser;

public class UMLDesignBuilder extends Observable implements DesignBuilder, Observer {

	private Set<String> fileNames;
	private boolean includeAll;
	private Set<String> arrowTypes;
	private Set<String> patternTypes;
	
	private IModel model;
	public UMLDesignBuilder(Set<String> fileNames, Set<String> arrowTypes, Set<String> patternTypes) {
		// this was a nice feature at first but I see no point in it for where the project is going
		this.includeAll = false;
		
		this.fileNames = fileNames;
		this.arrowTypes = arrowTypes;
		this.patternTypes = patternTypes;
		
		this.model = new Model();
		
		// Currently this is set Statically but in the
		// future we can set this to be dynamically set at
		// runtime
//		this.arrowTypes = new ArrayList<String>();
//		this.arrowTypes.add("Association");
//		this.arrowTypes.add("Implements");
//		this.arrowTypes.add("Inheritance");
//		this.arrowTypes.add("Uses");
//		
//		this.patternTypes = new ArrayList<String>();
//		this.patternTypes.add("Singleton");
//		this.patternTypes.add("Decorator");
//		this.patternTypes.add("Adapter");
//		this.patternTypes.add("Composite");
	}
	
	@Override
	public IModel build() {
		FileParser fileParser = new FileParser(this.fileNames);
		fileParser.addObserver(this);
		Set<IFile> files = fileParser.parse();
		
		ArrowParser arrowParser = new ArrowParser(files, this.arrowTypes, false);
		arrowParser.addObserver(this);
		Set<IArrow> arrows = arrowParser.parse();
		
		PatternParser patternParser = new PatternParser(files, this.patternTypes);
		patternParser.addObserver(this);
		Set<IPattern> patterns = patternParser.parse();
		
		this.model.addFiles(files);
		this.model.addArrows(arrows);
		this.model.addPatterns(patterns);
		
		return this.model;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		this.notifyObservers((String) arg);
	}

}
