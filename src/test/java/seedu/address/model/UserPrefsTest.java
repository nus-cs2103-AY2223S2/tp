package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setPetPalFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setPetPalFilePath(null));
    }

    @Test
    public void setPetPalFileArchivePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setPetPalArchiveFilePath(null));
    }

    @Test
    public void hashCodeTests() {
        UserPrefs userPrefs = new UserPrefs();
        int expected = Objects.hash(userPrefs.getGuiSettings(),
                userPrefs.getPetPalFilePath(), userPrefs.getPetPalArchiveFilePath());
        assertEquals(userPrefs.hashCode(), expected);
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();

        assertEquals(userPrefs, userPrefs);

        // same values -> returns true
        UserPrefs userPrefsCopy = new UserPrefs();
        assertEquals(userPrefs, userPrefsCopy);

        // different types -> returns false
        assertNotEquals(1, userPrefs);

        // null -> returns false
        assertNotEquals(null, userPrefs);
    }
}
