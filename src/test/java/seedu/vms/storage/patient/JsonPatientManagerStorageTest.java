package seedu.vms.storage.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalPatients.HOON;
import static seedu.vms.testutil.TypicalPatients.IDA;
import static seedu.vms.testutil.TypicalPatients.getTypicalPatientManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.ReadOnlyPatientManager;

public class JsonPatientManagerStorageTest {
    private static final String TEST_DATA_FOLDER = "src/test/data/JsonPatientManagerStorageTest/";

    @TempDir
    public Path testFolder;

    @Test
    public void readPatientManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPatientManager(null));
    }

    private ReadOnlyPatientManager readPatientManager(String filePath) throws Exception {
        return new JsonPatientManagerStorage(Paths.get(filePath))
                .readPatientManager();
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertThrows(IOException.class,
                () -> readPatientManager(TEST_DATA_FOLDER + "NonExistentFile.json"));
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(IOException.class,
                () -> readPatientManager(TEST_DATA_FOLDER + "notJsonFormatPatientManager.json"));
    }

    @Test
    public void readPatientManager_invalidPatientPatientManager_throwDataConversionException() {
        // TODO: Check for proper exception
        assertThrows(IOException.class,
                () -> readPatientManager(TEST_DATA_FOLDER + "invalidPatientPatientManager.json"));
    }

    @Test
    public void readPatientManager_invalidAndValidPatientPatientManager_throwDataConversionException() {
        // TODO: Check for proper exception
        assertThrows(IOException.class,
                () -> readPatientManager(TEST_DATA_FOLDER + "invalidAndValidPatientPatientManager.json"));
    }

    @Test
    public void readAndSavePatientManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPatientManager.json");
        PatientManager original = getTypicalPatientManager();
        JsonPatientManagerStorage jsonPatientManagerStorage = new JsonPatientManagerStorage(filePath);

        // Save in new file and read back
        jsonPatientManagerStorage.savePatientManager(original);
        ReadOnlyPatientManager readBack = jsonPatientManagerStorage.readPatientManager();
        assertEquals(original, new PatientManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.add(HOON);
        original.remove(0);
        jsonPatientManagerStorage.savePatientManager(original);
        readBack = jsonPatientManagerStorage.readPatientManager();
        assertEquals(original, new PatientManager(readBack));

        // Save and read without specifying file path
        original.add(IDA);
        jsonPatientManagerStorage.savePatientManager(original); // file path not specified
        readBack = jsonPatientManagerStorage.readPatientManager(); // file path not specified
        assertEquals(original, new PatientManager(readBack));

    }

    @Test
    public void savePatientManager_nullPatientManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code patientManager} at the specified {@code filePath}.
     */
    private void savePatientManager(ReadOnlyPatientManager patientManager, String filePath) {
        try {
            new JsonPatientManagerStorage(Paths.get(filePath))
                    .savePatientManager(patientManager);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePatientManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientManager(new PatientManager(), null));
    }
}
