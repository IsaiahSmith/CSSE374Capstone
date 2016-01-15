package project;

import java.util.List;

public interface IEncoder {
	public StringBuilder encode(List<ClassBuilder> Classes, boolean includeAll);
}
