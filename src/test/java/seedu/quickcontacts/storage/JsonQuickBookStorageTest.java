package seedu.quickcontacts.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.quickcontacts.testutil.Assert.assertThrows;
import static seedu.quickcontacts.testutil.TypicalPersons.ALICE;
import static seedu.quickcontacts.testutil.TypicalPersons.HOON;
import static seedu.quickcontacts.testutil.TypicalPersons.IDA;
import static seedu.quickcontacts.testutil.TypicalQuickBooks.getTypicalQuickBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.quickcontacts.commons.exceptions.DataConversionException;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.ReadOnlyQuickBook;

public class JsonQuickBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonQuickBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readQuickBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readQuickBook(null));
    }

    private java.util.Optional<ReadOnlyQuickBook> readQuickBook(String filePath) throws Exception {
        return new JsonQuickBookStorage(Paths.get(filePath)).readQuickBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readQuickBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readQuickBook("notJsonFormatQuickBook.json"));
    }

    @Test
    public void readQuickBook_invalidPersonQuickBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readQuickBook("invalidPersonQuickBook.json"));
    }

    @Test
    public void readQuickBook_invalidMeetingQuickBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readQuickBook("invalidMeetingQuickBook.json"));
    }

    @Test
    public void readQuickBook_invalidAndValidPersonQuickBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readQuickBook("invalidAndValidPersonQuickBook.json"));
    }

    @Test
    public void readQuickBook_invalidAndValidMeetingQuickBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readQuickBook("invalidAndValidMeetingQuickBook.json"));
    }

    @Test
    public void readAndSaveQuickBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempQuickContactsA.json");
        QuickBook original = getTypicalQuickBook();
        JsonQuickBookStorage jsonQuickBookStorage = new JsonQuickBookStorage(filePath);

        // Save in new file and read back
        jsonQuickBookStorage.saveQuickBook(original, filePath);
        ReadOnlyQuickBook readBack = jsonQuickBookStorage.readQuickBook(filePath).get();
        assertEquals(original, new QuickBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonQuickBookStorage.saveQuickBook(original, filePath);
        readBack = jsonQuickBookStorage.readQuickBook(filePath).get();
        assertEquals(original, new QuickBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonQuickBookStorage.saveQuickBook(original); // file path not specified
        readBack = jsonQuickBookStorage.readQuickBook().get(); // file path not specified
        assertEquals(original, new QuickBook(readBack));

    }

    @Test
    public void saveQuickBook_nullQuickBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveQuickBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code quickBook} at the specified {@code filePath}.
     */
    private void saveQuickBook(ReadOnlyQuickBook quickBook, String filePath) {
        try {
            new JsonQuickBookStorage(Paths.get(filePath))
                    .saveQuickBook(quickBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveQuickBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveQuickBook(new QuickBook(), null));
    }
}
