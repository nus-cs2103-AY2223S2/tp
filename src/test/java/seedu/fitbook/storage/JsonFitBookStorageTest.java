package seedu.fitbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.client.TypicalClients.ALICE;
import static seedu.fitbook.testutil.client.TypicalClients.HOON;
import static seedu.fitbook.testutil.client.TypicalClients.IDA;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.FitBook;
import seedu.fitbook.model.ReadOnlyFitBook;

public class JsonFitBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFitBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFitBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFitBook(null));
    }

    private java.util.Optional<ReadOnlyFitBook> readFitBook(String filePath) throws Exception {
        return new JsonFitBookStorage(Paths.get(filePath)).readFitBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFitBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFitBook("notJsonFormatFitBook.json"));
    }

    @Test
    public void readFitBook_invalidClientFitBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFitBook("invalidRoutineFitBook.json"));
    }

    @Test
    public void readFitBook_invalidAndValidClientFitBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFitBook("invalidAndValidClientFitBook.json"));
    }

    @Test
    public void readAndSaveFitBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFitBook.json");
        FitBook original = getTypicalFitBook();
        JsonFitBookStorage jsonFitBookStorage = new JsonFitBookStorage(filePath);

        // Save in new file and read back
        jsonFitBookStorage.saveFitBook(original, filePath);
        ReadOnlyFitBook readBack = jsonFitBookStorage.readFitBook(filePath).get();
        assertEquals(original, new FitBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonFitBookStorage.saveFitBook(original, filePath);
        readBack = jsonFitBookStorage.readFitBook(filePath).get();
        assertEquals(original, new FitBook(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonFitBookStorage.saveFitBook(original); // file path not specified
        readBack = jsonFitBookStorage.readFitBook().get(); // file path not specified
        assertEquals(original, new FitBook(readBack));

    }

    @Test
    public void saveFitBook_nullFitBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code fitBook} at the specified {@code filePath}.
     */
    private void saveFitBook(ReadOnlyFitBook fitBook, String filePath) {
        try {
            new JsonFitBookStorage(Paths.get(filePath))
                    .saveFitBook(fitBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFitBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitBook(new FitBook(), null));
    }
}
