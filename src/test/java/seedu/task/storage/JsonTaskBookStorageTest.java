package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.HOON;
import static seedu.task.testutil.TypicalTasks.IDA;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.TaskBook;

//@@author
public class JsonTaskBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskBook(null));
    }

    private java.util.Optional<ReadOnlyTaskBook> readTaskBook(String filePath) throws Exception {
        return new JsonTaskBookStorage(Paths.get(filePath)).readTaskBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskBook("notJsonFormatTaskBook.json"));
    }

    @Test
    public void readTaskBook_invalidTaskTaskBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskBook("invalidTaskTaskBook.json"));
    }

    @Test
    public void readTaskBook_invalidAndValidTaskTaskBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskBook("invalidAndValidTaskTaskBook.json"));
    }

    @Test
    public void readAndSaveTaskBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskBook.json");
        TaskBook original = getTypicalTaskBook();
        JsonTaskBookStorage jsonTaskBookStorage = new JsonTaskBookStorage(filePath);

        // Save in new file and read back
        jsonTaskBookStorage.saveTaskBook(original, filePath);
        ReadOnlyTaskBook readBack = jsonTaskBookStorage.readTaskBook(filePath).get();
        assertEquals(original, new TaskBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(HOON);
        original.removeTask(ALICE);
        jsonTaskBookStorage.saveTaskBook(original, filePath);
        readBack = jsonTaskBookStorage.readTaskBook(filePath).get();
        assertEquals(original, new TaskBook(readBack));

        // Save and read without specifying file path
        original.addTask(IDA);
        jsonTaskBookStorage.saveTaskBook(original); // file path not specified
        readBack = jsonTaskBookStorage.readTaskBook().get(); // file path not specified
        assertEquals(original, new TaskBook(readBack));

    }

    @Test
    public void saveTaskBook_nullTaskBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskBook} at the specified {@code filePath}.
     */
    private void saveTaskBook(ReadOnlyTaskBook taskBook, String filePath) {
        try {
            new JsonTaskBookStorage(Paths.get(filePath))
                    .saveTaskBook(taskBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskBook(new TaskBook(), null));
    }
}
