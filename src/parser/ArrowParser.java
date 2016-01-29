package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IArrow;
import model.IFile;
import parser.linker.AssociationLinker;
import parser.linker.ImplementsLinker;
import parser.linker.InheritanceLinker;
import parser.linker.Linker;
import parser.linker.UsesLinker;

public class ArrowParser implements Parser<IArrow> {

	private List<String> arrowTypes;
	private Map<String, Linker> linkers;

	public ArrowParser(List<IFile> files, List<String> arrowTypes, Boolean includeAll) {
		this.arrowTypes = arrowTypes;
		
		this.linkers = new HashMap<String, Linker>();
		linkers.put(ImplementsLinker.TYPE, new ImplementsLinker(files, includeAll));
		linkers.put(UsesLinker.TYPE, new UsesLinker(files, includeAll));
		linkers.put(InheritanceLinker.TYPE, new InheritanceLinker(files, includeAll));
		linkers.put(AssociationLinker.TYPE, new AssociationLinker(files, includeAll));
	}
	
	@Override
	public List<IArrow> parse() {
		List<IArrow> arrowList = new ArrayList<IArrow>();
		for(String type : arrowTypes) {
			arrowList.addAll(linkers.get(type).link());
		}
		return arrowList;
	}

}
