package seedu.address.model.person.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YoeTest {
    @Test
    public void constructor_validYoe_success() {
        Yoe yoe = new Yoe("00005");
        assertEquals("5", yoe.getValue());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Yoe(null));
    }

    @Test
    public void constructor_invalidYoe_throwsIllegalArgumentException() {
        String invalidYoe = "";
        assertThrows(IllegalArgumentException.class, () -> new Yoe(invalidYoe));
    }

    @Test
    public void isValidYoe() {
        // null yoe number
        assertThrows(NullPointerException.class, () -> Yoe.isValidYoe(null));

        // invalid yoe numbers
        assertFalse(Yoe.isValidYoe("")); // empty string
        assertFalse(Yoe.isValidYoe(" ")); // spaces only
        assertFalse(Yoe.isValidYoe("912")); // more than 2 digits
        assertFalse(Yoe.isValidYoe("yoe")); // non-numeric
        assertFalse(Yoe.isValidYoe("p3")); // alphabets within digits
        assertFalse(Yoe.isValidYoe("0x1")); // hexadecimal digits
        assertFalse(Yoe.isValidYoe("5a")); // hexadecimal digits
        assertFalse(Yoe.isValidYoe("1 2")); // spaces within digits

        // valid yoe numbers
        assertTrue(Yoe.isValidYoe("91")); // exactly 2 numbers
        assertTrue(Yoe.isValidYoe("1")); // exactly 1 numbers
        assertTrue(Yoe.isValidYoe("000012")); // leading 0s with 2 digits
    }

    @Test
    public void getValue_validYoe_returnsYoe() {
        String validYoe = "00005";
        String expectedYoe = "5";
        Yoe yoe = new Yoe(validYoe);
        assertEquals(yoe.getValue(), expectedYoe);
    }
}
