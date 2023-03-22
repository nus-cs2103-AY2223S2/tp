package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipDate}
 * is before the specified date given.
 */
public class BeforeDatePredicate extends DatePredicate {
    private final InterviewDate beforeDate;

    public BeforeDatePredicate(InterviewDate beforeDate) {
        this.beforeDate = beforeDate;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        return internshipApplication.getInterviewDate() != null
                && internshipApplication.getInterviewDate().isBeforeInclusive(beforeDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && beforeDate.equals(((BeforeDatePredicate) other).beforeDate)); // state check
    }
}
