package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyElderly;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.ReadOnlyVolunteer;
import seedu.address.model.UserPrefs;
import seedu.address.storage.elderly.JsonElderlyStorage;
import seedu.address.storage.pair.JsonPairStorage;
import seedu.address.storage.volunteer.JsonVolunteerStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPairStorage pairStorage = new JsonPairStorage(getTempFilePath("pair"));
        JsonElderlyStorage elderlyStorage = new JsonElderlyStorage(getTempFilePath("elderly"));
        JsonVolunteerStorage volunteerStorage = new JsonVolunteerStorage(getTempFilePath("volunteer"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(pairStorage, elderlyStorage, volunteerStorage, userPrefsStorage);
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

    /*
     * Note: Next 3 test cases are integration tests that verifies the StorageManager is properly wired to the
     * storage classes.
     * More extensive testing of UserPref saving/reading is done in the storage classes test files.
     */

    // TODO: pairReadSave(), follow implementations below

    @Test
    public void elderlyReadSave() throws Exception {
        FriendlyLink original = getTypicalFriendlyLink();
        storageManager.saveElderly(original);
        ReadOnlyElderly retrieved = storageManager.readElderly(new FriendlyLink()).get();
        assertEquals(original.getElderlyList(),
                new FriendlyLink((ReadOnlyFriendlyLink) retrieved).getElderlyList());
    }

    @Test
    public void volunteerReadSave() throws Exception {
        FriendlyLink original = getTypicalFriendlyLink();
        storageManager.saveVolunteer(original);
        ReadOnlyVolunteer retrieved = storageManager.readVolunteer(new FriendlyLink()).get();
        assertEquals(original.getVolunteerList(),
                new FriendlyLink((ReadOnlyFriendlyLink) retrieved).getVolunteerList());
    }

    @Test
    public void readSave() throws Exception {
        FriendlyLink original = getTypicalFriendlyLink();
        storageManager.saveElderly(original);
        storageManager.saveVolunteer(original);
        storageManager.savePair(original);
        FriendlyLink retrieved = storageManager.read();
        assertEquals(original.getPairList(),
                new FriendlyLink(retrieved).getPairList());
    }

    @Test
    public void getElderlyFilePath() {
        assertNotNull(storageManager.getElderlyFilePath());
    }

    @Test
    public void getVolunteerFilePath() {
        assertNotNull(storageManager.getVolunteerFilePath());
    }

    @Test
    public void getPairFilePath() {
        assertNotNull(storageManager.getPairFilePath());
    }

}
