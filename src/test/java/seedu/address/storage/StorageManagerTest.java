package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalFriendlyLink;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.UserPrefs;
import seedu.address.storage.elderly.JsonElderlyStorage;
import seedu.address.storage.volunteer.JsonVolunteerStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFriendlyLinkStorage friendlyLinkStorage = new JsonFriendlyLinkStorage(getTempFilePath("friendlylink"));
        JsonElderlyStorage elderlyStorage = new JsonElderlyStorage(getTempFilePath("elderly"));
        JsonVolunteerStorage volunteerStorage = new JsonVolunteerStorage(getTempFilePath("volunteer"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(friendlyLinkStorage, elderlyStorage, volunteerStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void friendlyLinkReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFriendlyLinkStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFriendlyLinkStorageTest} class.
         */
        FriendlyLink original = getTypicalFriendlyLink();
        storageManager.saveFriendlyLink(original);
        ReadOnlyFriendlyLink retrieved = storageManager.readFriendlyLink().get();
        assertEquals(original, new FriendlyLink(retrieved));
    }

    @Test
    public void getFriendlyLinkFilePath() {
        assertNotNull(storageManager.getFriendlyLinkFilePath());
    }

}
