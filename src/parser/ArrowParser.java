package parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import model.IArrow;
import model.IFile;
import parser.linker.AssociationLinker;
import parser.linker.ImplementsLinker;
import parser.linker.InheritanceLinker;
import parser.linker.Linker;
import parser.linker.UsesLinker;

public class ArrowParser extends Observable implements Parser<IArrow> {

	private Set<String> arrowTypes;
	private Map<String, Linker> linkers;

	public ArrowParser(Set<IFile> files, Set<String> arrowTypes, Boolean includeAll) {
		this.arrowTypes = arrowTypes;
		
		this.linkers = new HashMap<String, Linker>();
		linkers.put(ImplementsLinker.TYPE, new ImplementsLinker(files, includeAll));
		linkers.put(UsesLinker.TYPE, new UsesLinker(files, includeAll));
		linkers.put(InheritanceLinker.TYPE, new InheritanceLinker(files, includeAll));
		linkers.put(AssociationLinker.TYPE, new AssociationLinker(files, includeAll));
	}
	
	@Override
	public Set<IArrow> parse() {
		Set<IArrow> arrowList = new HashSet<IArrow>();
		for(String type : arrowTypes) {
			setChanged();
			this.notifyObservers("Linking: "+type);
			arrowList.addAll(linkers.get(type).link());
		}
		return arrowList;
	}

}
