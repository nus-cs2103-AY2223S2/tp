package seedu.address.model.application;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipDate}
 * is after the specified date given.
 */
public class AfterDatePredicate extends DatePredicate {
    private final InterviewDate afterDate;

    /**
     * Creates a AfterDatePredicate with boundary date specified (inclusive).
     * @param afterDate The date to compare to
     */
    public AfterDatePredicate(InterviewDate afterDate) {
        assert afterDate != null : "date should not be null";
        this.afterDate = afterDate;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        assert internshipApplication != null : "internshipApplication should not be null";
        return internshipApplication.getInterviewDate() != null
                && internshipApplication.getInterviewDate().isAfterInclusive(afterDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AfterDatePredicate // instanceof handles nulls
                && afterDate.equals(((AfterDatePredicate) other).afterDate)); // state check
    }
}
