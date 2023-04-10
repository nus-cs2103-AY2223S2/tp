package trackr.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static trackr.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import trackr.commons.exceptions.DataConversionException;
import trackr.model.Menu;
import trackr.model.OrderList;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
import trackr.model.TaskList;

//@@author changgittyhub-reused
public class JsonTrackrStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackrStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSupplierList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSupplierList(null));
        assertThrows(NullPointerException.class, () -> readTaskList(null));
        assertThrows(NullPointerException.class, () -> readOrderList(null));
    }

    private java.util.Optional<ReadOnlySupplierList> readSupplierList(String filePath) throws Exception {
        return new JsonTrackrStorage(Paths.get(filePath)).readSupplierList(addToTestDataPathIfNotNull(filePath));
    }

    private java.util.Optional<ReadOnlyTaskList> readTaskList(String filePath) throws Exception {
        return new JsonTrackrStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private java.util.Optional<ReadOnlyOrderList> readOrderList(String filePath) throws Exception {
        return new JsonTrackrStorage(Paths.get(filePath)).readOrderList(addToTestDataPathIfNotNull(filePath));
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
        assertFalse(readOrderList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSupplierList("notJsonFormatTrackr.json"));
        assertThrows(DataConversionException.class, () -> readTaskList("notJsonFormatTrackr.json"));
        assertThrows(DataConversionException.class, () -> readOrderList("notJsonFormatTrackr.json"));
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
    public void readTrackr_invalidOrderTrackr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readOrderList("invalidOrderTrackr.json"));
    }

    @Test
    public void readTrackr_invalidAndValidOrderTrackr_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readOrderList("invalidAndValidOrderTrackr.json"));
    }


    @Test
    public void saveTrackr_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackr(null,
                null, null, null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} and {@code taskList} at the specified {@code filePath}.
     */
    private void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList,
            ReadOnlyMenu menu, ReadOnlyOrderList orderList, String filePath) {
        try {
            new JsonTrackrStorage(Paths.get(filePath))
                    .saveTrackr(addressBook, taskList, menu, orderList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackr_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackr(new SupplierList(),
                new TaskList(), new Menu(), new OrderList(), null));
    }
}
