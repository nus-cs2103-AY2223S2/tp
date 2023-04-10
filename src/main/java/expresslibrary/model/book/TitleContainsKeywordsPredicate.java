package expresslibrary.model.book;

import java.util.List;
import java.util.function.Predicate;

import expresslibrary.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(book.getTitle().title, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
