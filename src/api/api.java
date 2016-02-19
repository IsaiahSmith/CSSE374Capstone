package api;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface api {
	public void loadConfig(File file);
	public Map<String, List<String>> getPatternRoots();
	public void addPatternRoot(String patternRoot);
	public void addPattern(String pattern);
	public void removePatternRoot(String patternRoot);
	public void removePattern(String pattern);
	public void getPatternStyle(); // TODO: figure out what this type needs to be
	public void getPatternStyles(); // TODO: figure out what this type needs to be
}
