package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalSuppliers.ALICE;
import static trackr.testutil.TypicalSuppliers.HOON;
import static trackr.testutil.TypicalSuppliers.IDA;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.CLEAN_TOOLS_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
import trackr.model.TaskList;

public class JsonTrackrStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackrStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSupplierList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSupplierList(null));
        assertThrows(NullPointerException.class, () -> readTaskList(null));
    }

    private java.util.Optional<ReadOnlySupplierList> readSupplierList(String filePath) throws Exception {
        return new JsonTrackrStorage(Paths.get(filePath)).readSupplierList(addToTestDataPathIfNotNull(filePath));
    }

    private java.util.Optional<ReadOnlyTaskList> readTaskList(String filePath) throws Exception {
        return new JsonTrackrStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSupplierList("NonExistentFile.json").isPresent());
        assertFalse(readTaskList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSupplierList("notJsonFormatTrackr.json"));
        assertThrows(DataConversionException.class, () -> readTaskList("notJsonFormatTrackr.json"));
    }

    @Test
    public void readTrackr_invalidSupplierTrackr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSupplierList("invalidSupplierTrackr.json"));
    }

    @Test
    public void readTrackr_invalidAndValidSupplierTrackr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSupplierList("invalidAndValidSupplierTrackr.json"));
    }

    @Test
    public void readTrackr_invalidTaskTrackr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskList("invalidTaskTrackr.json"));
    }

    @Test
    public void readTrackr_invalidAndValidTaskTrackr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskList("invalidAndValidTaskTrackr.json"));
    }

    @Test
    public void readAndSaveTrackr_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrackr.json");
        SupplierList originalAddressBook = getTypicalSupplierList();
        TaskList originalTaskList = getTypicalTaskList();
        JsonTrackrStorage jsonTrackrStorage = new JsonTrackrStorage(filePath);

        // Save in new file and read back
        jsonTrackrStorage.saveTrackr(originalAddressBook, originalTaskList, filePath);
        ReadOnlySupplierList readBackAddressBook = jsonTrackrStorage.readSupplierList(filePath).get();
        ReadOnlyTaskList readBackTaskList = jsonTrackrStorage.readTaskList(filePath).get();
        assertEquals(originalAddressBook, new SupplierList(readBackAddressBook));
        assertEquals(originalTaskList, new TaskList(readBackTaskList));

        // Modify data, overwrite exiting file, and read back
        originalAddressBook.addSupplier(HOON);
        originalAddressBook.removeSupplier(ALICE);
        jsonTrackrStorage.saveTrackr(originalAddressBook, originalTaskList, filePath);
        readBackAddressBook = jsonTrackrStorage.readSupplierList(filePath).get();
        assertEquals(originalAddressBook, new SupplierList(readBackAddressBook));

        originalTaskList.addTask(BUY_FLOUR_N);
        originalTaskList.removeTask(SORT_INVENTORY_N);
        jsonTrackrStorage.saveTrackr(originalAddressBook, originalTaskList, filePath);
        readBackTaskList = jsonTrackrStorage.readTaskList(filePath).get();
        assertEquals(originalTaskList, new TaskList(readBackTaskList));

        // Save and read without specifying file path
        originalAddressBook.addSupplier(IDA);
        jsonTrackrStorage.saveTrackr(originalAddressBook, originalTaskList); // file path not specified
        readBackAddressBook = jsonTrackrStorage.readSupplierList().get(); // file path not specified
        assertEquals(originalAddressBook, new SupplierList(readBackAddressBook));

        originalTaskList.addTask(CLEAN_TOOLS_N);
        jsonTrackrStorage.saveTrackr(originalAddressBook, originalTaskList); // file path not specified
        readBackTaskList = jsonTrackrStorage.readTaskList().get(); // file path not specified
        assertEquals(originalTaskList, new TaskList(readBackTaskList));

    }

    @Test
    public void saveTrackr_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackr(null, null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} and {@code taskList} at the specified {@code filePath}.
     */
    private void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList, String filePath) {
        try {
            new JsonTrackrStorage(Paths.get(filePath))
                    .saveTrackr(addressBook, taskList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackr_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackr(new SupplierList(), new TaskList(), null));
    }
}
