package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipDate}
 * is after the specified date given.
 */
public class AfterDatePredicate extends DatePredicate {
    private final InterviewDate afterDate;

    public AfterDatePredicate(InterviewDate afterDate) {
        this.afterDate = afterDate;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        return internshipApplication.getInterviewDate() != null
                && internshipApplication.getInterviewDate().isAfterInclusive(afterDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && afterDate.equals(((AfterDatePredicate) other).afterDate)); // state check
    }
}
