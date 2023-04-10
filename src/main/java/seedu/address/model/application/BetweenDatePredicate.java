package seedu.address.model.application;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipDate}
 * is between the specified dates given.
 */
public class BetweenDatePredicate extends DatePredicate {
    private final InterviewDate startDate;
    private final InterviewDate endDate;

    /**
     * Creates a BetweenDatePredicate with boundary dates specified (both inclusive).
     *
     * @param startDate The start date of the interval
     * @param endDate The end date of the interval
     */
    public BetweenDatePredicate(InterviewDate startDate, InterviewDate endDate) {
        assert startDate != null : "date should not be null";
        assert endDate != null : "date should not be null";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        assert internshipApplication != null : "internshipApplication should not be null";
        return internshipApplication.getInterviewDate() != null
                && internshipApplication.getInterviewDate().isBetweenInclusive(startDate, endDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BetweenDatePredicate // instanceof handles nulls
                && startDate.equals(((BetweenDatePredicate) other).startDate)
                && endDate.equals(((BetweenDatePredicate) other).endDate)); // state check
    }
}
