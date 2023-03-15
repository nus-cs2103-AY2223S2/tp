package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackr.commons.core.GuiSettings;
import trackr.model.OrderList;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTrackrStorage trackrStorage = new JsonTrackrStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(trackrStorage, userPrefsStorage);
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
    public void trackrReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        SupplierList originalAddressBook = getTypicalSupplierList();
        TaskList originalTaskList = getTypicalTaskList();
        OrderList originalOrderList = getTypicalOrderList();
        storageManager.saveTrackr(originalAddressBook, originalTaskList, originalOrderList);
        ReadOnlySupplierList retrievedAddressBook = storageManager.readSupplierList().get();
        assertEquals(originalAddressBook, new SupplierList(retrievedAddressBook));
        ReadOnlyTaskList retrievedTaskList = storageManager.readTaskList().get();
        assertEquals(originalTaskList, new TaskList(retrievedTaskList));
        ReadOnlyOrderList retrievedOrderList = storageManager.readOrderList().get();
        assertEquals(originalOrderList, new OrderList(retrievedOrderList));
    }

    @Test
    public void getTrackrFilePath() {
        assertNotNull(storageManager.getTrackrFilePath());
    }

    @Test
    public void getUserPrefsFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

}
