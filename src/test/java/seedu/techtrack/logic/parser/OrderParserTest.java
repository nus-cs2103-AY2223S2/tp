package seedu.techtrack.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OrderParserTest {

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
