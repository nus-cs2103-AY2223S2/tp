package trackr.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import trackr.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setTrackrFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setTrackrFilePath(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different type -> returns false
        assertFalse(userPrefs.equals(5));

        // same values -> returns true
        UserPrefs userPrefsCopy = new UserPrefs(userPrefs);
        assertTrue(userPrefs.equals(userPrefsCopy));

        // different file path -> returns false
        Path diffPath = Paths.get("test" , "different", "path");
        UserPrefs userPrefsDiffPath = new UserPrefs();
        userPrefsDiffPath.setTrackrFilePath(diffPath);
        assertFalse(userPrefs.equals(userPrefsDiffPath));

        // different gui settings -> returns false
        GuiSettings guiSettings = new GuiSettings(0, 0, 0, 0);
        UserPrefs userPrefsDiffGui = new UserPrefs();
        userPrefsDiffGui.setGuiSettings(guiSettings);
        assertFalse(userPrefs.equals(userPrefsDiffGui));
    }

}
