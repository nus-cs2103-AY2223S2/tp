package codoc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import codoc.commons.core.GuiSettings;
import codoc.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCodocStorage codocStorage = new JsonCodocStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(codocStorage, userPrefsStorage);
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

    //    @Test // Broken
    //    public void codocReadSave() throws Exception {
    //        /*
    //         * Note: This is an integration test that verifies the StorageManager is properly wired to the
    //         * {@link JsonCodocStorage} class.
    //         * More extensive testing of UserPref saving/reading is done in {@link JsonCodocStorageTest} class.
    //         */
    //        Codoc original = getTypicalCodoc();
    //        storageManager.saveCodoc(original);
    //        ReadOnlyCodoc retrieved = storageManager.readCodoc().get();
    //        assertEquals(original, new Codoc(retrieved));
    //    }

    @Test
    public void getCodocFilePath() {
        assertNotNull(storageManager.getCodocFilePath());
    }

}
