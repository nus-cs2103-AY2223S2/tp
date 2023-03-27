package expresslibrary.model.book;

import java.util.List;
import java.util.function.Predicate;

import expresslibrary.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Title} matches any of the keywords given.
 */
public class BookNameContainsKeywordsPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public BookNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(book.getTitle().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BookNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
