package seedu.address.testutil;

import seedu.address.model.application.InterviewDate;

/**
 * A utility class to help with building InterviewDate objects.
 */
public class InterviewDateBuilder {
    public static final String DEFAULT_DATE = "2099-12-01 11:00 AM";

    private InterviewDate interviewDate;

    /**
     * Creates a {@code InterviewDateBuilder} with the default details.
     */
    public InterviewDateBuilder() {
        interviewDate = new InterviewDate(DEFAULT_DATE);
    }

    public InterviewDate build() {
        return new InterviewDate(DEFAULT_DATE);
    }
}
