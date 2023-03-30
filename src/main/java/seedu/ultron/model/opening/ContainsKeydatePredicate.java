package seedu.ultron.model.opening;

import java.time.LocalDate;
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
        LocalDate today = LocalDate.now();
        for (Date date : opening.getDates()) {
            LocalDate curr = LocalDate.parse(date.fullDate);
            if (curr.compareTo(today) >= 0) {
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
