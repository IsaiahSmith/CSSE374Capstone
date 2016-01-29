package parser;

import java.util.List;

public interface Parser<T> {
	public List<T> parse();
}
