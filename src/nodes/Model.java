package nodes;

import java.util.ArrayList;
import java.util.List;

import model.IArrow;
import model.IFile;
import model.IModel;
import model.INode;
import model.IPattern;
import parser.ArrowParser;
import parser.FileParser;
import parser.Parser;
import parser.PatternParser;
import parser.SequenceParser;

public class Model implements IModel {

	private final List<IFile> files;
	
	private final List<IArrow> arrows;
	private final List<IPattern> patterns;
	
	public static class Builder implements IBuilder {
		private List<IFile> files;
		
		private List<IArrow> arrows = new ArrayList<IArrow>();
		private List<IPattern> patterns = new ArrayList<IPattern>();
		
		public Builder(List<String> fileNames) {
			Parser<IFile> fileList = new FileParser(fileNames);
			this.files = fileList.parse();
		}
		
		@Override
		public IBuilder arrows(List<String> arrowTypes, boolean includeAll) {
			Parser<IArrow> arrowList = new ArrowParser(this.files, arrowTypes, includeAll);
			this.arrows = arrowList.parse();
			return this;
		}

		@Override
		public IBuilder sequences(List<String> signatures, int depth) {
			Parser<IFile> sequenceList = new SequenceParser(this.files, signatures, depth);
			this.files = sequenceList.parse();
			return this;
		}

		@Override
		public IBuilder patterns(List<String> patternTypes) {
			Parser<IPattern> patternList = new PatternParser(this.files, patternTypes);
			this.patterns = patternList.parse();
			return this;
		}

		@Override
		public List<IFile> getFiles() {
			return this.files;
		}

		@Override
		public List<IArrow> getArrows() {
			return this.arrows;
		}

		@Override
		public List<IPattern> getPatterns() {
			return this.patterns;
		}
		
	}
	
	private Model(IBuilder builder) {
		this.files = builder.getFiles();
		
		this.arrows = builder.getArrows();
		this.patterns = builder.getPatterns();
	}
	
//	public Model() {
//		this.files = new ArrayList<IFile>();
//	}
	
//	@Override
//	public void addFile(IFile file) {
//		this.files.add(file);
//	}

	@Override
	public List<IFile> getFiles() {
		return this.files;
	}
	
	@Override
	public String toString() {
		String str = new String();
		for(INode node:files)
			str += node.toString() + "\n\n";
		return str;
	}

	@Override
	public List<IArrow> getArrows() {
		return this.arrows;
	}

	@Override
	public List<IPattern> getPatterns() {
		return this.patterns;
	}

}
