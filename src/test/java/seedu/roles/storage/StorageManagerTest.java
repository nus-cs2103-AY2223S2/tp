package seedu.roles.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.roles.commons.core.GuiSettings;
import seedu.roles.model.ReadOnlyRoleBook;
import seedu.roles.model.RoleBook;
import seedu.roles.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonRoleBookStorage roleBookStorage = new JsonRoleBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(roleBookStorage, userPrefsStorage);
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
    public void roleBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonRoleBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonRoleBookStorageTest} class.
         */
        RoleBook original = getTypicalRoleBook();
        storageManager.saveRoleBook(original);
        ReadOnlyRoleBook retrieved = storageManager.readRoleBook().get();
        assertEquals(original, new RoleBook(retrieved));
    }

    @Test
    public void getRoleBookFilePath() {
        assertNotNull(storageManager.getRoleBookFilePath());
    }

}
