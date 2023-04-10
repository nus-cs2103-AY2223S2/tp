package seedu.powercards.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonMasterDeckStorage masterDeckStorage = new JsonMasterDeckStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(masterDeckStorage, userPrefsStorage);
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
    public void masterDeckReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonMasterDeckStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonMasterDeckStorageTest} class.
         */
        MasterDeck original = getTypicalMasterDeck();
        storageManager.saveMasterDeck(original);
        ReadOnlyMasterDeck retrieved = storageManager.readMasterDeck().get();
        assertEquals(original, new MasterDeck(retrieved));
    }

    @Test
    public void getMasterDeckFilePath() {
        assertNotNull(storageManager.getMasterDeckFilePath());
    }

}
