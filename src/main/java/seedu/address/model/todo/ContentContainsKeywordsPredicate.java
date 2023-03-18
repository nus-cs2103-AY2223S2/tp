package seedu.address.model.todo;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.InternshipApplication;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code InternshipApplication}'s {@code CompanyName}
 * and {@code JobTitle} matches any of the keywords given.
 */
public class ContentContainsKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    public ContentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Note note) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(note.getNote().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
