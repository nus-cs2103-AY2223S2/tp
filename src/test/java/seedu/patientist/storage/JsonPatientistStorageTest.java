package seedu.patientist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalPatients.ADAM;
//import static seedu.patientist.testutil.TypicalPatients.AMY;
//import static seedu.patientist.testutil.TypicalPatients.CHARLIE;
import static seedu.patientist.testutil.TypicalPatients.getTypicalPatientist;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.patientist.commons.exceptions.DataConversionException;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.ReadOnlyPatientist;

public class JsonPatientistStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPatientistStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyPatientist> readAddressBook(String filePath) throws Exception {
        return new JsonPatientistStorage(Paths.get(filePath)).readPatientist(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatPatientist.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonPatientist.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonPatientist.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Patientist original = getTypicalPatientist();
        JsonPatientistStorage jsonAddressBookStorage = new JsonPatientistStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.savePatientist(original, filePath);
        ReadOnlyPatientist readBack = jsonAddressBookStorage.readPatientist(filePath).get();
        Patientist p = new Patientist(readBack);
        assertEquals(original, p);

        // Modify data, overwrite exiting file, and read back
        //original.addPerson(BOB);
        original.removePerson(ADAM);
        jsonAddressBookStorage.savePatientist(original, filePath);
        readBack = jsonAddressBookStorage.readPatientist(filePath).get();
        assertEquals(original, new Patientist(readBack));

        // Save and read without specifying file path
        //original.addPerson(AMY);
        jsonAddressBookStorage.savePatientist(original); // file path not specified
        readBack = jsonAddressBookStorage.readPatientist().get(); // file path not specified
        assertEquals(original, new Patientist(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyPatientist addressBook, String filePath) {
        try {
            new JsonPatientistStorage(Paths.get(filePath))
                    .savePatientist(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Patientist(), null));
    }
}
