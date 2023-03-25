package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Title} matches exactly the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Task> {
    private final String keyword;

    public TitleContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Task task) {
        return task.getStringTitleLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((TitleContainsKeywordsPredicate) other).keyword)); // state check
    }
}
