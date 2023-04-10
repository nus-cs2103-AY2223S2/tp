package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class HasPaidTest {
    private HasPaid hasPaidFalse = new HasPaid();
    private HasPaid hasPaidTrue = new HasPaid(true);
    private HasPaid hasPaidFalse1 = new HasPaid(false);

    @Test
    public void equals() {
        assertEquals(hasPaidFalse, hasPaidFalse1);
        assertFalse(hasPaidFalse.equals(hasPaidTrue));
    }

    @Test
    public void markPaidTest() {
        hasPaidFalse.markPaid();
        assertEquals(hasPaidTrue, hasPaidFalse);
        hasPaidTrue.markPaid();
        assertEquals(hasPaidTrue, hasPaidFalse);
    }

    @Test
    public void markUnpaidTest() {
        hasPaidTrue.markUnpaid();
        assertEquals(hasPaidFalse, hasPaidTrue);
    }

    @Test
    public void toStringTest() {
        assertEquals(hasPaidTrue.toString(), "[has paid]");
        assertEquals(hasPaidFalse.toString(), "[not yet paid]");
    }

    @Test
    public void getBool() {
        assertEquals(hasPaidFalse.getBool(), false);
        assertEquals(hasPaidTrue.getBool(), true);
    }
}
