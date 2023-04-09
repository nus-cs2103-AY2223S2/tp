package seedu.address.model.application.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class JobTitleComparatorTest {
    private JobTitleComparator cmp = new JobTitleComparator();

    @Test
    public void compare_bothStartWithUpperCase_returnsNegativeInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withJobTitle("Software Engineer").build(),
                new InternshipBuilder().withJobTitle("Test Engineer").build())
                < 0);
    }

    @Test
    public void compare_mixedCases_returnsNegativeInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withJobTitle("Software Engineer").build(),
                new InternshipBuilder().withJobTitle("test Engineer").build())
                < 0);

        assertTrue(cmp.compare(
                new InternshipBuilder().withJobTitle("software Engineer").build(),
                new InternshipBuilder().withJobTitle("Test Engineer").build())
                < 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsPositiveInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withJobTitle("Tesla Software Engineer").build(),
                new InternshipBuilder().withJobTitle("Software Engineer").build())
                > 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsZero() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withJobTitle("Tesla Software Engineer").build(),
                new InternshipBuilder().withJobTitle("Tesla Software Engineer").build())
                == 0);
    }
}
