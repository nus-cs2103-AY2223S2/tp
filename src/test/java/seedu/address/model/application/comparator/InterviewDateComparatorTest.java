package seedu.address.model.application.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.application.InterviewDate;
import seedu.address.testutil.InternshipBuilder;

public class InterviewDateComparatorTest {
    private InterviewDateComparator cmp = new InterviewDateComparator();

    @Test
    public void compare_twoInternshipApplications_returnsNegativeInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withInterviewDate(new InterviewDate("2023-04-01 01:00 PM")).build(),
                new InternshipBuilder().withInterviewDate(new InterviewDate("2023-04-02 01:00 PM")).build())
                < 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsPositiveInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withInterviewDate(new InterviewDate("2023-04-02 02:00 PM")).build(),
                new InternshipBuilder().withInterviewDate(new InterviewDate("2023-04-02 01:00 PM")).build())
                > 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsZero() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withInterviewDate(new InterviewDate("2023-04-01 01:00 PM")).build(),
                new InternshipBuilder().withInterviewDate(new InterviewDate("2023-04-01 01:00 PM")).build())
                == 0);
    }
}
