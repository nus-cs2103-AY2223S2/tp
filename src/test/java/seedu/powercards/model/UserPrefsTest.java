package seedu.powercards.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setMasterDeckFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setMasterDeckFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs sameUserPrefs = new UserPrefs();

        // Test with same object reference
        assertTrue(userPrefs.equals(userPrefs));

        // Test with different types
        assertFalse(userPrefs.equals(new Object()));

        // Test with null
        assertFalse(userPrefs.equals(null));

        // Test with equal objects
        assertTrue(userPrefs.equals(sameUserPrefs));

    }

}
