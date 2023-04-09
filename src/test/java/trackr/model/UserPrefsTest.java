package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static trackr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
        assertEquals(userPrefs, userPrefs);

        // null -> returns false
        assertNotEquals(null, userPrefs);

        // different type -> returns false
        assertNotEquals(5, userPrefs);

        // same values -> returns true
        UserPrefs userPrefsCopy = new UserPrefs(userPrefs);
        assertEquals(userPrefs, userPrefsCopy);

        // different file path -> returns false
        Path diffPath = Paths.get("test", "different", "path");
        UserPrefs userPrefsDiffPath = new UserPrefs();
        userPrefsDiffPath.setTrackrFilePath(diffPath);
        assertNotEquals(userPrefs, userPrefsDiffPath);

        // different gui settings -> returns false
        GuiSettings guiSettings = new GuiSettings(0, 0, 0, 0);
        UserPrefs userPrefsDiffGui = new UserPrefs();
        userPrefsDiffGui.setGuiSettings(guiSettings);
        assertNotEquals(userPrefs, userPrefsDiffGui);
    }

    @Test
    public void hashCode_success() {
        UserPrefs userPrefs = new UserPrefs();
        int hashCode = Objects.hash(userPrefs.getGuiSettings(), userPrefs.getTrackrFilePath());
        assertEquals(userPrefs.hashCode(), hashCode);
    }

}
