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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setEduMateFilePath(null));
    }

    @Test
    public void hashCode_validUserPrefs_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs.hashCode(), Objects.hash(userPrefs.getGuiSettings(),
                userPrefs.getEduMateFilePath()));
    }

    @Test
    public void equals_sameObject_true() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs, userPrefs);
    }

    @Test
    public void equals_notUserPrefs_false() {
        UserPrefs userPrefs = new UserPrefs();
        assertNotEquals(userPrefs, 3);
    }

}
