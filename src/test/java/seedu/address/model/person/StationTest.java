package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Station(null));
    }

    @Test
    public void constructor_invalidStation_throwsIllegalArgumentException() {
        String invalidStation = "";
        assertThrows(IllegalArgumentException.class, () -> new Station(invalidStation));
    }

    @Test
    public void isValidStation() {
        // invalid stations
        assertFalse(Station.isValidStation("")); // empty string
        assertFalse(Station.isValidStation(" ")); // spaces only

        // valid stations
        assertTrue(Station.isValidStation("Punggol"));
        assertTrue(Station.isValidStation("Pasir Ris"));
        assertTrue(Station.isValidStation("Sixth Avenue"));

        // wrong case but still works
        assertTrue(Station.isValidStation("punGGol"));
        assertTrue(Station.isValidStation("pasir ris"));
        assertTrue(Station.isValidStation("SIXTH avenue"));

        // untrimmed still works
        assertTrue(Station.isValidStation("   Punggol"));
        assertTrue(Station.isValidStation("Pasir Ris   "));

        // wrong spacing
        assertFalse(Station.isValidStation("Pasir  Ris"));
        assertFalse(Station.isValidStation("   Sixth     Avenue  "));

        // looks valid, but is wrong
        assertFalse(Station.isValidStation("Pungol"));
        assertFalse(Station.isValidStation("Paris Ris"));
        assertFalse(Station.isValidStation("Fifth Avenue"));
    }
}
