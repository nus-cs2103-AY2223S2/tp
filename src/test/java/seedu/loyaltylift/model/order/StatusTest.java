package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_emptyUpdates_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, Status.MESSAGE_CONSTRAINTS, () -> new Status(List.of()));
    }

    @Test
    public void constructor_badlySortedUpdates_updatesSorted() {
        Status status = new Status(List.of(
                new StatusUpdate(StatusValue.PAID, LocalDate.of(2020, 1, 2)),
                new StatusUpdate(StatusValue.PENDING, LocalDate.of(2019, 3, 15))
        ));

        assertEquals(status.getLatestStatus().statusValue, StatusValue.PAID);
    }
}
