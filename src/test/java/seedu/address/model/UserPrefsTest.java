package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    private static final UserPrefs USER_PREFS = new UserPrefs();

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> USER_PREFS.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> USER_PREFS.setEduMateFilePath(null));
    }

    @Test
    public void hashCode_validUserPrefs_success() {
        assertEquals(USER_PREFS.hashCode(), Objects.hash(USER_PREFS.getGuiSettings(),
                USER_PREFS.getEduMateFilePath()));
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(USER_PREFS, USER_PREFS);
    }

    @Test
    public void equals_notUserPrefs_false() {
        assertNotEquals(USER_PREFS, 3);
    }

}
