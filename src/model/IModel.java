package model;

import java.util.List;

public interface IModel {
	public void addFile(IFile node);
	public List<IFile> getFiles();
}
