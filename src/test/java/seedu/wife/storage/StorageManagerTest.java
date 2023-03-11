package seedu.wife.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static seedu.wife.testutil.TypicalFood.getTypicalWife;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.wife.commons.core.GuiSettings;
//import seedu.wife.model.ReadOnlyWife;
//import seedu.wife.model.Wife;
import seedu.wife.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonWifeStorage wifeStorage = new JsonWifeStorage(getTempFilePath("wife"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(wifeStorage, userPrefsStorage);
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


    //    @Test
    //    public void wifeReadSave() throws Exception {
    //        /*
    //         * Note: This is an integration test that verifies the StorageManager is properly wired to the
    //         * {@link JsonAddressBookStorage} class.
    //         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
    //         */
    //        Wife original = getTypicalWife();
    //        storageManager.saveWife(original);
    //        ReadOnlyWife retrieved = storageManager.readWife().get();
    //        assertEquals(original, new Wife(retrieved));
    //    }

    @Test
    public void getWifeFilePath() {
        assertNotNull(storageManager.getWifeFilePath());
    }

}
