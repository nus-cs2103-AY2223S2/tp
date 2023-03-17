package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.EventBook;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonEventBookStorage eventBookStorage = new JsonEventBookStorage(getTempFilePath("ab"));
        JsonContactListStorage contactListStorage = new JsonContactListStorage(getTempFilePath("cl"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(eventBookStorage, contactListStorage, userPrefsStorage);
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
    public void eventBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonEventBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonEventBookStorageTest} class.
         */
        EventBook original = getTypicalEventBook();
        storageManager.saveEventBook(original);
        ReadOnlyEventBook retrieved = storageManager.readEventBook().get();
        assertEquals(original, new EventBook(retrieved));
    }

    @Test
    public void contactListReadSave() throws Exception {
        ContactList original = getTypicalContactList();
        storageManager.saveContactList(original);
        ReadOnlyContactList retrieved = storageManager.readContactList().get();
        assertEquals(original, new ContactList(retrieved));
    }

    @Test
    public void getEventBookFilePath() {
        assertNotNull(storageManager.getEventBookFilePath());
    }

    @Test
    public void getContactListFilePath() {
        assertNotNull(storageManager.getContactListFilePath());
    }

}
