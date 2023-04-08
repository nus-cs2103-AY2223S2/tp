package taa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import taa.commons.exceptions.DataConversionException;
import taa.model.ClassList;
import taa.model.ReadOnlyStudentList;
import taa.testutil.Assert;
import taa.testutil.TypicalPersons;

public class JsonClassListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonClassListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaaData_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readTaaData(null));
    }

    private Optional<ReadOnlyStudentList> readTaaData(String filePath) throws Exception {
        return new JsonTaaStorage(Paths.get(filePath)).readTaaData(addToTestDataPathIfNotNull(filePath))
                .map(data -> data.studentList);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaaData("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readTaaData("notJsonFormatTaaData.json"));
    }

    @Test
    public void readTaaData_invalidPersonTaaData_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readTaaData("invalidPersonTaaData.json"));
    }

    @Test
    public void readTaaData_invalidAndValidPersonTaaData_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, ()
                -> readTaaData("invalidAndValidPersonTaaData.json"));
    }

    @Test
    public void readAndSaveTaaData_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaaData.json");
        ClassList original = TypicalPersons.getTypicalTaaData();
        JsonTaaStorage jsonTaaDataStorage = new JsonTaaStorage(filePath);

        // Save in new file and read back
        jsonTaaDataStorage.saveTaaData(new TaaData(original), filePath);
        ReadOnlyStudentList readBack = jsonTaaDataStorage.readTaaData(filePath).get().studentList;
        assertEquals(original, new ClassList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(TypicalPersons.HOON);
        original.removeStudent(TypicalPersons.ALICE);
        jsonTaaDataStorage.saveTaaData(new TaaData(original), filePath);
        readBack = jsonTaaDataStorage.readTaaData(filePath).get().studentList;
        assertEquals(original, new ClassList(readBack));

        // Save and read without specifying file path
        original.addStudent(TypicalPersons.IDA);
        jsonTaaDataStorage.saveTaaData(new TaaData(original)); // file path not specified
        readBack = jsonTaaDataStorage.readTaaData().get().studentList; // file path not specified
        assertEquals(original, new ClassList(readBack));

    }

    @Test
    public void saveTaaData_nullTaaData_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, ()
                -> saveTaaData(null, "SomeFile.json"));
    }

    /**
     * Saves TAA data at the specified {@code filePath}.
     */
    private void saveTaaData(ReadOnlyStudentList studentList, String filePath) {
        try {
            new JsonTaaStorage(Paths.get(filePath))
                    .saveTaaData(new TaaData(studentList), addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaaData_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveTaaData(new ClassList(), null));
    }
}
