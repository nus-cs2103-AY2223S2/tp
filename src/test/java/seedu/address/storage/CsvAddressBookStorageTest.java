package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.*;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class CsvAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "CsvAddressBookStorageTest");
    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new CsvAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.csv").isPresent());
    }

    @Test
    public void readAddressBook_TooManyHeaders_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("TooManyHeaders.csv"));
    }

    @Test
    public void readAddressBook_WrongHeaders_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("WrongHeaders.csv"));
    }

    @Test
    public void readAddressBook_missingFields_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("MissingFields.csv"));
    }

    @Test
    public void readAddressBook_invalidPerson_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("InvalidPerson.csv"));
    }

    @Test
    public void readAddressBook_validAddressBook_success() throws Exception {
        ReadOnlyAddressBook expectedAddressBook = getTypicalAddressBook();
        ReadOnlyAddressBook actualAddressBook = readAddressBook("ValidAddressBook.csv").get();

        assertEquals(expectedAddressBook, actualAddressBook);
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.csv");
        AddressBook original = getTypicalAddressBook();
        CsvAddressBookStorage csvAddressBookStorage = new CsvAddressBookStorage(filePath);

        // Save in new file and read back
        csvAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = csvAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        csvAddressBookStorage.saveAddressBook(original, filePath);
        readBack = csvAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        csvAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = csvAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.csv"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new CsvAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException e) {
            throw new AssertionError("IOException when writing to csv file.", e);
        }
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }
}
