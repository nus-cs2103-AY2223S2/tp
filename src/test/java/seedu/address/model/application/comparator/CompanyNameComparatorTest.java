package seedu.address.model.application.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class CompanyNameComparatorTest {
    private CompanyNameComparator cmp = new CompanyNameComparator();

    @Test
    public void compare_twoInternshipApplications_returnsNegativeInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withCompanyName("Google").build(),
                new InternshipBuilder().withCompanyName("Tesla").build())
                < 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsPositiveInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withCompanyName("Google").build(),
                new InternshipBuilder().withCompanyName("Alice").build())
                > 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsZero() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withCompanyName("Google").build(),
                new InternshipBuilder().withCompanyName("Google").build())
                == 0);
    }
}
