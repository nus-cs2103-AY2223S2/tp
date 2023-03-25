package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class StatusUpdateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StatusUpdate(null, LocalDate.now()));
        assertThrows(NullPointerException.class, () -> new StatusUpdate(StatusValue.PENDING, null));
    }

    @Test
    public void nextStatusUpdate_validArguments_success() {
        StatusUpdate firstStatusUpdate = new StatusUpdate(StatusValue.PENDING, LocalDate.now());
        StatusUpdate paidStatusUpdate = firstStatusUpdate.nextStatusUpdate();
        StatusUpdate shippedStatusUpdate = paidStatusUpdate.nextStatusUpdate();
        StatusUpdate completedStatusUpdate = shippedStatusUpdate.nextStatusUpdate();

        assertEquals(firstStatusUpdate.statusValue, StatusValue.PENDING);
        assertEquals(paidStatusUpdate.statusValue, StatusValue.PAID);
        assertEquals(shippedStatusUpdate.statusValue, StatusValue.SHIPPED);
        assertEquals(completedStatusUpdate.statusValue, StatusValue.COMPLETED);
    }

    @Test
    public void nextStatusUpdate_invalidCall_throwsIllegalStateException() {
        StatusUpdate completedStatusUpdate = new StatusUpdate(StatusValue.COMPLETED, LocalDate.now());
        StatusUpdate cancelledStatusUpdate = new StatusUpdate(StatusValue.CANCELLED, LocalDate.now());

        assertThrows(IllegalStateException.class, completedStatusUpdate::nextStatusUpdate);
        assertThrows(IllegalStateException.class, cancelledStatusUpdate::nextStatusUpdate);
    }

    @Test
    public void equals_sameAttributes_returnsTrue() {
        StatusUpdate statusUpdate1 = new StatusUpdate(StatusValue.CANCELLED, LocalDate.of(2020, 1, 2));
        StatusUpdate statusUpdate2 = new StatusUpdate(StatusValue.CANCELLED, LocalDate.of(2020, 1, 2));

        assertEquals(statusUpdate1, statusUpdate2);
    }

    @Test
    public void equals_differentAttributes_returnsFalse() {
        StatusUpdate cancelledStatusUpdate = new StatusUpdate(StatusValue.CANCELLED, LocalDate.of(2020, 1, 2));
        StatusUpdate completedStatusUpdate = new StatusUpdate(StatusValue.COMPLETED, LocalDate.of(2020, 1, 2));
        assertNotEquals(cancelledStatusUpdate, completedStatusUpdate);

        StatusUpdate statusUpdate2020 = new StatusUpdate(StatusValue.PENDING, LocalDate.of(2020, 1, 2));
        StatusUpdate statusUpdate2022 = new StatusUpdate(StatusValue.PENDING, LocalDate.of(2022, 1, 2));
        assertNotEquals(statusUpdate2020, statusUpdate2022);
    }
}
