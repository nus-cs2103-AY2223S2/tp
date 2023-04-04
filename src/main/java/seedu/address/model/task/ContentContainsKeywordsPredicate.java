package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Note}'s {@code NoteContent} matches any of the keywords given.
 */
public class ContentContainsKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    /**
     * Creates a {@code ContentContainsKeywordsPredicate} instance using the provided {@code keyword}.
     */
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
