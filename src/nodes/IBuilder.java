package nodes;

import java.util.List;

import model.IArrow;
import model.IFile;
import model.IModel;
import model.IPattern;

public interface IBuilder {
	public IBuilder arrows(List<String> arrowTypes, boolean includeAll);
	public IBuilder sequences(List<String> signatures, int depth);
	public IBuilder patterns(List<String> patternTypes);
	
	public List<IFile> getFiles();
	public List<IArrow> getArrows();
	public List<IPattern> getPatterns();
	public IModel build();
}
