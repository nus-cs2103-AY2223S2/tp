package seedu.fitbook.model.routines;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.util.StringUtil;

/**
 * Tests that a {@code Routine}'s {@code RoutineName} matches any of the keywords given.
 */
public class RoutineNameContainsKeywordsPredicate implements Predicate<Routine> {
    private final List<String> keywords;

    public RoutineNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Routine routine) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(routine.getRoutineName().routineName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoutineNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
