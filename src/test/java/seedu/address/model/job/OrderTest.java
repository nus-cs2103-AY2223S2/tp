package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Order(null));
    }

    @Test
    public void constructor_invalidOrder_throwsIllegalArgumentException() {
        String invalidOrder = "asecc";
        assertThrows(IllegalArgumentException.class, () -> new Order(invalidOrder));
    }

    @Test
    public void isValidOrder() {
        // null order
        assertThrows(NullPointerException.class, () -> Order.isValidOrder(null));

        // invalid order
        assertFalse(Order.isValidOrder("")); // empty string
        assertFalse(Order.isValidOrder(" ")); // spaces only
        assertFalse(Order.isValidOrder("ASEC")); // only lower caps
        assertFalse(Order.isValidOrder("dessc")); // spelt wrongly

        // valid order
        assertTrue(Order.isValidOrder("asc")); // asc
        assertTrue(Order.isValidOrder("desc")); // desc
    }
}
