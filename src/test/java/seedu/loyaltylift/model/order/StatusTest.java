package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.fromString(null));
    }

    @Test
    public void fromString_invalidString_throwsIllegalArgumentException() {
        String invalidString = "";
        assertThrows(IllegalArgumentException.class, () -> Status.fromString(invalidString));
    }

    @Test
    public void fromString_validString() {
        assertEquals(Status.fromString("PENDING"), Status.PENDING);
        assertEquals(Status.fromString("Pending"), Status.PENDING);
        assertEquals(Status.fromString("pending"), Status.PENDING);

        assertEquals(Status.fromString("PAID"), Status.PAID);
        assertEquals(Status.fromString("Paid"), Status.PAID);
        assertEquals(Status.fromString("paid"), Status.PAID);

        assertEquals(Status.fromString("SHIPPED"), Status.SHIPPED);
        assertEquals(Status.fromString("Shipped"), Status.SHIPPED);
        assertEquals(Status.fromString("shipped"), Status.SHIPPED);

        assertEquals(Status.fromString("COMPLETED"), Status.COMPLETED);
        assertEquals(Status.fromString("Completed"), Status.COMPLETED);
        assertEquals(Status.fromString("completed"), Status.COMPLETED);

        assertEquals(Status.fromString("CANCELLED"), Status.CANCELLED);
        assertEquals(Status.fromString("Cancelled"), Status.CANCELLED);
        assertEquals(Status.fromString("cancelled"), Status.CANCELLED);
    }
}
