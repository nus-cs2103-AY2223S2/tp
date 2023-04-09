package seedu.medinfo.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.medinfo.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.ReadOnlyMedInfo;

public class JsonMedInfoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMedInfoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMedInfo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMedInfo(null));
    }

    private java.util.Optional<ReadOnlyMedInfo> readMedInfo(String filePath) throws Exception {
        return new JsonMedInfoStorage(Paths.get(filePath)).readMedInfo(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMedInfo("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMedInfo("notJsonFormatMedInfo.json"));
    }

    @Test
    public void readMedInfo_invalidPersonMedInfo_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMedInfo("invalidPatientMedInfo.json"));
    }

    @Test
    public void readMedInfo_invalidAndValidPersonMedInfo_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMedInfo("invalidAndValidPatientMedInfo.json"));
    }

    //    @Test
    //    public void readAndSaveMedInfo_allInOrder_success() throws Exception {
    //        Path filePath = testFolder.resolve("TempMedInfo.json");
    //        MedInfo original = getTypicalMedInfo();
    //        JsonMedInfoStorage jsonMedInfoStorage = new JsonMedInfoStorage(filePath);
    //
    //        // Save in new file and read back
    //        jsonMedInfoStorage.saveMedInfo(original, filePath);
    //        ReadOnlyMedInfo readBack = jsonMedInfoStorage.readMedInfo(filePath).get();
    //        assertEquals(original, new MedInfo(readBack));
    //
    //        // Modify data, overwrite exiting file, and read back
    //        original.addPatient(HOON);
    //        original.removePatient(ALEX);
    //        jsonMedInfoStorage.saveMedInfo(original, filePath);
    //        readBack = jsonMedInfoStorage.readMedInfo(filePath).get();
    //        assertEquals(original, new MedInfo(readBack));
    //
    //        // Save and read without specifying file path
    //        original.addPatient(IDA);
    //        jsonMedInfoStorage.saveMedInfo(original); // file path not specified
    //        readBack = jsonMedInfoStorage.readMedInfo().get(); // file path not specified
    //        assertEquals(original, new MedInfo(readBack));
    //    }

    @Test
    public void saveMedInfo_nullMedInfo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedInfo(null, "SomeFile.json"));
    }

    /**
     * Saves {@code medInfo} at the specified {@code filePath}.
     */
    private void saveMedInfo(ReadOnlyMedInfo medInfo, String filePath) {
        try {
            new JsonMedInfoStorage(Paths.get(filePath))
                    .saveMedInfo(medInfo, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMedInfo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedInfo(new MedInfo(), null));
    }
}
