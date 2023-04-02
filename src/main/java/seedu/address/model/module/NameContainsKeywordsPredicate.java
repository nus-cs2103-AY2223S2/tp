package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code Name} {@code Tag} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword -> containsSubstringIgnoreCase(module.getName().fullName, keyword)
                        || module.getTags().stream()
                        .anyMatch(tag -> containsSubstringIgnoreCase(tag.tagName, keyword)));
    }

    private boolean containsSubstringIgnoreCase(String text, String substring) {
        return text.toLowerCase().contains(substring.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
