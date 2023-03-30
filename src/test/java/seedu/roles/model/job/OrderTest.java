package seedu.roles.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.roles.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.roles.logic.parser.OrderParser;

public class OrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderParser(null));
    }

    @Test
    public void constructor_invalidOrder_throwsIllegalArgumentException() {
        String invalidOrder = "asecc";
        assertThrows(IllegalArgumentException.class, () -> new OrderParser(invalidOrder));
    }

    @Test
    public void isValidOrder() {
        // null order
        assertThrows(NullPointerException.class, () -> OrderParser.isValidOrder(null));

        // invalid order
        assertFalse(OrderParser.isValidOrder("")); // empty string
        assertFalse(OrderParser.isValidOrder(" ")); // spaces only
        assertFalse(OrderParser.isValidOrder("ASEC")); // only lower caps
        assertFalse(OrderParser.isValidOrder("dessc")); // spelt wrongly

        // valid order
        assertTrue(OrderParser.isValidOrder("asc")); // asc
        assertTrue(OrderParser.isValidOrder("desc")); // desc
    }
}
