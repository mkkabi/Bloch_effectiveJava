package effectivejava;

import java.util.List;
import java.util.Objects;

public class DependencyInjection {

}

// Dependency injection provides flexibility and testability
class SpellChecker {

	private final Lexicon dictionary;

	public SpellChecker(Lexicon dictionary) {
		this.dictionary = Objects.requireNonNull(dictionary);
	}

	public boolean isValid(String word) {
		return true;
	}

	public List<String> suggestions(String typo) {
		return null; // not implemented yet}
	}
}

class Lexicon {

}
