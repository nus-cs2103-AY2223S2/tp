package seedu.address.model.application.comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.application.InternshipStatus.ACCEPTED;
import static seedu.address.model.application.InternshipStatus.DECLINED;
import static seedu.address.model.application.InternshipStatus.PENDING;
import static seedu.address.model.application.InternshipStatus.RECEIVED;
import static seedu.address.model.application.InternshipStatus.REJECTED;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class StatusComparatorTest {
    private StatusComparator cmp = new StatusComparator();

    @Test
    public void compare_bothStartWithUpperCase_returnsNegativeInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withStatus(RECEIVED).build(),
                new InternshipBuilder().withStatus(REJECTED).build())
                < 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsPositiveInt() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withStatus(DECLINED).build(),
                new InternshipBuilder().withStatus(ACCEPTED).build())
                > 0);
    }

    @Test
    public void compare_twoInternshipApplications_returnsZero() {
        assertTrue(cmp.compare(
                new InternshipBuilder().withStatus(PENDING).build(),
                new InternshipBuilder().withStatus(PENDING).build())
                == 0);
    }
}
