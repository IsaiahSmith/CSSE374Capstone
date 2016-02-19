package model;

import java.util.Set;

public interface IModel {
//	public void addFile(IFile node);
	public Set<IFile> getFiles();
	public Set<IArrow> getArrows();
	public Set<IPattern> getPatterns();
	
//after gutting builder from model
	public void addFiles(Set<IFile> files);
	public void addArrows(Set<IArrow> arrows);
	public void addPatterns(Set<IPattern> patterns);
}
