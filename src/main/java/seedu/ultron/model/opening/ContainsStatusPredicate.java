package seedu.ultron.model.opening;

import java.util.function.Predicate;

import seedu.ultron.commons.util.StringUtil;

/**
 * Tests that a {@code Opening}'s {@code Status} matches the keyword given.
 */
public class ContainsStatusPredicate implements Predicate<Opening> {
    private final Status status;

    public ContainsStatusPredicate(Status status) {
        this.status = status;
    }

    @Override
    public boolean test(Opening opening) {
        return StringUtil.containsWordIgnoreCase(opening.getStatus().fullStatus, status.fullStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsStatusPredicate // instanceof handles nulls
                && status.equals(((ContainsStatusPredicate) other).status)); // state check
    }

}
