package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class IsPresentTest {
    private IsPresent isPresentFalse = new IsPresent();
    private IsPresent isPresentTrue = new IsPresent(true);
    private IsPresent isPresentFalse1 = new IsPresent(false);

    @Test
    public void equals() {
        assertEquals(isPresentFalse, isPresentFalse1);
        assertFalse(isPresentFalse.equals(isPresentTrue));
    }

    @Test
    public void markPresentTest() {
        isPresentFalse.markPresent();
        assertEquals(isPresentTrue, isPresentFalse);
        isPresentTrue.markPresent();
        assertEquals(isPresentTrue, isPresentFalse);
    }

    @Test
    public void markAbsentTest() {
        isPresentTrue.markAbsent();
        assertEquals(isPresentFalse, isPresentTrue);
    }

    @Test
    public void toStringTest() {
        assertEquals(isPresentTrue.toString(), "[present]");
        assertEquals(isPresentFalse.toString(), "[absent]");
    }

    @Test
    public void getBool() {
        assertEquals(isPresentFalse.getBool(), false);
        assertEquals(isPresentTrue.getBool(), true);
    }
}
