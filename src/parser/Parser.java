package parser;

import java.util.Set;

public interface Parser<T> {
	public Set<T> parse();
}
