package seedu.address.model.application;

/**
 * Tests that an {@code InternshipApplication}'s {@code InternshipDate}
 * is before the specified date given.
 */
public class BeforeDatePredicate extends DatePredicate {
    private final InterviewDate beforeDate;

    /**
     * Creates a BeforeDatePredicate with boundary date specified (inclusive).
     *
     * @param beforeDate The date to compare to
     */
    public BeforeDatePredicate(InterviewDate beforeDate) {
        assert beforeDate != null : "date should not be null";
        this.beforeDate = beforeDate;
    }

    @Override
    public boolean test(InternshipApplication internshipApplication) {
        assert internshipApplication != null : "internshipApplication should not be null";
        return internshipApplication.getInterviewDate() != null
                && internshipApplication.getInterviewDate().isBeforeInclusive(beforeDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BeforeDatePredicate // instanceof handles nulls
                && beforeDate.equals(((BeforeDatePredicate) other).beforeDate)); // state check
    }
}
