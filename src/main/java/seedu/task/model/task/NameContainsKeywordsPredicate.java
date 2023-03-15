package seedu.task.model.task;

import java.util.function.Predicate;

import seedu.task.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Task> {
    private final String keyphrase;

    public NameContainsKeywordsPredicate(String keyphrase) {
        this.keyphrase = keyphrase;
    }

    @Override
    public boolean test(Task task) {
        return StringUtil.containsIgnoreCase(task.getName().fullName, keyphrase);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keyphrase.equals(((NameContainsKeywordsPredicate) other).keyphrase)); // state check
    }

}
