package seedu.ultron.model.opening;

import java.util.function.Predicate;

import seedu.ultron.commons.util.StringUtil;

/**
 * Tests that a {@code Opening}'s {@code Status} matches the keyword given.
 */
public class StatusContainsKeywordPredicate implements Predicate<Opening> {
    private final String keyword;

    public StatusContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Opening opening) {
        return StringUtil.containsWordIgnoreCase(opening.getStatus().fullStatus, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((StatusContainsKeywordPredicate) other).keyword)); // state check
    }

}
