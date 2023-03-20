package seedu.careflow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalPatients.ALICE;
import static seedu.careflow.testutil.TypicalPatients.HOON;
import static seedu.careflow.testutil.TypicalPatients.IDA;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

class JsonPatientRecordStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonPatientRecordStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPatientRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPatientRecord(null));
    }

    private java.util.Optional<ReadOnlyPatientRecord> readPatientRecord(String filePath) throws Exception {
        return new JsonPatientRecordStorage(Paths.get(filePath))
                .readPatientRecord(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPatientRecord("NonExistentFile.json").isPresent());
    }
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, ()
                -> readPatientRecord("notJsonFormatPatientRecord.json"));
    }
    @Test
    public void readPatientRecord_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readPatientRecord("invalidPatientPatientRecord.json"));
    }
    @Test
    public void readPatientRecord_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readPatientRecord("invalidAndValidPatientPatientRecord"
                + ".json"));
    }

    @Test
    public void readAndSavePatientRecord_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPatientRecord.json");
        PatientRecord original = getTypicalPatientRecord();
        JsonPatientRecordStorage jsonPatientRecordStorage = new JsonPatientRecordStorage(filePath);
        // Save in new file and read back
        jsonPatientRecordStorage.savePatientRecord(original, filePath);
        ReadOnlyPatientRecord readBack = jsonPatientRecordStorage.readPatientRecord(filePath).get();
        assertEquals(original, new PatientRecord(readBack));
        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonPatientRecordStorage.savePatientRecord(original, filePath);
        readBack = jsonPatientRecordStorage.readPatientRecord(filePath).get();
        assertEquals(original, new PatientRecord(readBack));
        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonPatientRecordStorage.savePatientRecord(original); // file path not specified
        readBack = jsonPatientRecordStorage.readPatientRecord().get(); // file path not specified
        assertEquals(original, new PatientRecord(readBack));
    }

    @Test
    public void savePatientRecord_nullPatientRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> savePatientRecord(null, "SomeFile.json"));
    }
    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void savePatientRecord(ReadOnlyPatientRecord patientRecord, String filePath) {
        try {
            new JsonPatientRecordStorage(Paths.get(filePath))
                    .savePatientRecord(patientRecord, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
    @Test
    public void savePatientRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientRecord(new PatientRecord(), null));
    }
}
