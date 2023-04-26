package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctors.ALICE;
import static seedu.address.testutil.TypicalDoctors.ELLA;
import static seedu.address.testutil.TypicalDoctors.FIONA;
import static seedu.address.testutil.TypicalDoctors.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath))
                .readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidDoctorAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "invalidDoctorAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidDoctorAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "invalidAndValidDoctorAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPatientAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "invalidPatientAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPatientAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "invalidAndValidPatientAddressBook.json"));
    }

    @Test
    public void readAddressBook_noUnassignedPatientsAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "noUnassignedPatientsAddressBook.json"));
    }

    @Test
    public void readAddressBook_noDoctorsAddressBook_throwDataConversionException() {
        assertThrows(
                DataConversionException.class, () -> readAddressBook(
                        "noDoctorsAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook original = getTypicalAddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addDoctor(ELLA);
        original.removeDoctor(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addDoctor(FIONA);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> saveAddressBook(
                        null, "SomeFile.json"));
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(
                new AddressBook(), null));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
}
