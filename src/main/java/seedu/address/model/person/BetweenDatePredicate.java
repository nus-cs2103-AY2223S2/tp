package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipDate}
 * is between the specified dates given.
 */
public class BetweenDatePredicate extends DatePredicate {
    private final InterviewDate startDate;
    private final InterviewDate endDate;

    public BetweenDatePredicate(InterviewDate startDate, InterviewDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        return internshipApplication.getInterviewDate() != null
                && internshipApplication.getInterviewDate().isBetweenInclusive(startDate, endDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && startDate.equals(((BetweenDatePredicate) other).startDate)
                && endDate.equals(((BetweenDatePredicate) other).endDate)); // state check
    }
}
