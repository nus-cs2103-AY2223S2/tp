package seedu.ultron.model.opening;

import java.util.function.Predicate;


/**
 * Tests that a {@code Opening}'s {@code Company} or {@code Position} matches any of the keywords given.
 */
public class ContainsKeydatePredicate implements Predicate<Opening> {

    @Override
    public boolean test(Opening opening) {
        if (opening.getDates() == null || opening.getDates().isEmpty()) {
            return false;
        }
        for (Date date : opening.getDates()) {
            if (date.isPastDate()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }

}
