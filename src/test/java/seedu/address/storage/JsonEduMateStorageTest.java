package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;

public class JsonEduMateStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEduMateStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEduMate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEduMate(null));
    }

    private java.util.Optional<ReadOnlyEduMate> readEduMate(String filePath) throws Exception {
        return new JsonEduMateStorage(Paths.get(filePath)).readEduMate(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEduMate("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEduMate("notJsonFormatEduMate.json"));
    }

    @Test
    public void readEduMate_invalidPersonEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate("invalidPersonEduMate.json"));
    }

    @Test
    public void readEduMate_invalidAndValidPersonEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate("invalidAndValidPersonEduMate.json"));
    }

    @Test
    public void readAndSaveEduMate_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEduMate.json");
        EduMate original = getTypicalEduMate();
        JsonEduMateStorage jsonEduMateStorage = new JsonEduMateStorage(filePath);

        // Save in new file and read back
        jsonEduMateStorage.saveEduMate(original, filePath);
        ReadOnlyEduMate readBack = jsonEduMateStorage.readEduMate(filePath).get();
        assertEquals(original, new EduMate(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonEduMateStorage.saveEduMate(original, filePath);
        readBack = jsonEduMateStorage.readEduMate(filePath).get();
        assertEquals(original, new EduMate(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonEduMateStorage.saveEduMate(original); // file path not specified
        readBack = jsonEduMateStorage.readEduMate().get(); // file path not specified
        assertEquals(original, new EduMate(readBack));

    }

    @Test
    public void saveEduMate_nullEduMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEduMate(null, "SomeFile.json"));
    }

    /**
     * Saves {@code eduMate} at the specified {@code filePath}.
     */
    private void saveEduMate(ReadOnlyEduMate eduMate, String filePath) {
        try {
            new JsonEduMateStorage(Paths.get(filePath))
                    .saveEduMate(eduMate, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEduMate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEduMate(new EduMate(), null));
    }
}
