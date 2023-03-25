package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusValueTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StatusValue.fromString(null));
    }

    @Test
    public void fromString_invalidString_throwsIllegalArgumentException() {
        String invalidString = "";
        assertThrows(IllegalArgumentException.class, () -> StatusValue.fromString(invalidString));
    }

    @Test
    public void fromString_validString() {
        assertEquals(StatusValue.fromString("PENDING"), StatusValue.PENDING);
        assertEquals(StatusValue.fromString("Pending"), StatusValue.PENDING);
        assertEquals(StatusValue.fromString("pending"), StatusValue.PENDING);

        assertEquals(StatusValue.fromString("PAID"), StatusValue.PAID);
        assertEquals(StatusValue.fromString("Paid"), StatusValue.PAID);
        assertEquals(StatusValue.fromString("paid"), StatusValue.PAID);

        assertEquals(StatusValue.fromString("SHIPPED"), StatusValue.SHIPPED);
        assertEquals(StatusValue.fromString("Shipped"), StatusValue.SHIPPED);
        assertEquals(StatusValue.fromString("shipped"), StatusValue.SHIPPED);

        assertEquals(StatusValue.fromString("COMPLETED"), StatusValue.COMPLETED);
        assertEquals(StatusValue.fromString("Completed"), StatusValue.COMPLETED);
        assertEquals(StatusValue.fromString("completed"), StatusValue.COMPLETED);

        assertEquals(StatusValue.fromString("CANCELLED"), StatusValue.CANCELLED);
        assertEquals(StatusValue.fromString("Cancelled"), StatusValue.CANCELLED);
        assertEquals(StatusValue.fromString("cancelled"), StatusValue.CANCELLED);
    }
}
