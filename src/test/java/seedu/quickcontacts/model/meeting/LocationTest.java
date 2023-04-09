package seedu.quickcontacts.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.testutil.Assert;

public class LocationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null location
        Assert.assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid locations
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation(" ")); // spaces only
        assertFalse(Location.isValidLocation(" City Square Mall")); // starts with a space

        // valid locations
        assertTrue(Location.isValidLocation("https://zoom.us/")); // url
        assertTrue(Location.isValidLocation("A")); // one character
        assertTrue(Location.isValidLocation("123 Jurong West Street 52 Blk 123A #14-244")); // long location
    }

    @Test
    public void isValidVirtualLocation() {
        // null virtual location
        Assert.assertThrows(NullPointerException.class, () -> Location.isValidVirtualLocation(null));

        // invalid virtual locations
        assertFalse(Location.isValidVirtualLocation("")); // empty string
        assertFalse(Location.isValidVirtualLocation(" ")); // spaces only
        assertFalse(Location.isValidVirtualLocation(" City Square Mall")); // starts with a space
        assertFalse(Location.isValidVirtualLocation("A")); // one character
        assertFalse(Location.isValidVirtualLocation("123 Jurong West Street 52 Blk 123A #14-244")); // address
        assertFalse(Location.isValidVirtualLocation("zoom.us")); // address

        // valid virtual locations
        assertTrue(Location.isValidVirtualLocation("https://zoom.us/")); // https url
        assertTrue(Location.isValidVirtualLocation("http://zoom.us/")); // http url
        assertTrue(Location.isValidVirtualLocation(
                "https://us02web.zoom.us/j/99999999999?pwd=ABCdEfGHiJkYkRuYW5WTLmNopQrSt12")); // actual zoom url
        assertTrue(Location.isValidVirtualLocation(
                "http://us02web.zoom.us/j/99999999999?pwd=ABCdEfGHiJkYkRuYW5WTLmNopQrSt12")); // http zoom url
    }
}
