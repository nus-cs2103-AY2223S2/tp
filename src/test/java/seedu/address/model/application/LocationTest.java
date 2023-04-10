package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void equals() {
        Location location1 = new Location("Singapore");
        Location location2 = new Location("Singapore");
        Location location3 = new Location("USA");

        // reflexivity
        assertTrue(location1.equals(location1));

        // symmetry
        assertTrue(location1.equals(location2));
        assertTrue(location2.equals(location1));

        // transitivity
        assertTrue(location1.equals(location2));
        assertFalse(location2.equals(location3));
        assertFalse(location1.equals(location3));

        // non-nullity
        assertFalse(location1.equals(null));

        // different types
        assertFalse(location1.equals("Singapore"));
    }

    @Test
    void testHashCode() {
        Location location1 = new Location("Singapore");
        Location location2 = new Location("Singapore");
        Location location3 = new Location("USA");

        assertEquals(location1.hashCode(), location2.hashCode());
        assertNotEquals(location1.hashCode(), location3.hashCode());
    }
}

