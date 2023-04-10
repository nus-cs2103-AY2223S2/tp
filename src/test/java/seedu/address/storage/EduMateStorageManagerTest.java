package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.CLARK;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.EduMate;
import seedu.address.model.ReadOnlyEduMate;

public class EduMateStorageManagerTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "EduMateStorageManagerTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEduMate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEduMate(null));
    }

    private java.util.Optional<ReadOnlyEduMate> readEduMate(String filePath) throws Exception {
        return new EduMateStorageManager(Paths.get(filePath), null).readEduMate(addToTestDataPathIfNotNull(filePath));
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
    public void readEduMate_shortPhoneFieldEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate("shortPhoneFieldEduMate.json"));
    }

    @Test
    public void readEduMate_nonNumericFieldEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate("nonNumericPhoneFieldEduMate.json"));
    }

    @Test
    public void readEduMate_invalidNameEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate("invalidNameEduMate.json"));
    }

    @Test
    public void readEduMate_longModuleTagEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate("longModuleTagEduMate.json"));
    }

    @Test
    public void readEduMate_shortPhoneFieldValidInvalidEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate(
                "shortPhoneFieldValidInvalidEduMate.json"));
    }

    @Test
    public void readEduMate_nonNumericFieldValidInvalidEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate(
                "nonNumericPhoneFieldValidInvalidEduMate.json"));
    }

    @Test
    public void readEduMate_invalidNameValidInvalidEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate(
                "invalidNameValidInvalidEduMate.json"));
    }

    @Test
    public void readEduMate_longModuleTagValidInvalidEduMate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEduMate(
                "longModuleTagValidInvalidEduMate.json"));
    }

    @Test
    public void readAndSaveEduMate_allInOrder_success() throws Exception {
        Path storageFilePath = testFolder.resolve("TempEduMate.json");
        Path historyFilePath = testFolder.resolve(".temp_edumate_history");
        EduMate original = getTypicalEduMate();
        EduMateStorageManager eduMateStorageManager = new EduMateStorageManager(storageFilePath, historyFilePath);

        // Save in new file and read back
        eduMateStorageManager.saveEduMate(original, storageFilePath);
        ReadOnlyEduMate readBack = eduMateStorageManager.readEduMate(storageFilePath).get();
        assertEquals(original.getPersonList(), new EduMate(readBack).getPersonList());

        // Modify data, overwrite exiting file, and read back
        original.removePerson(ALBERT);
        original.removePerson(CLARK);
        eduMateStorageManager.saveEduMate(original, storageFilePath);
        readBack = eduMateStorageManager.readEduMate(storageFilePath).get();
        assertEquals(original.getPersonList(), new EduMate(readBack).getPersonList());

        // Save and read without specifying file path
        original.addPerson(CLARK);
        eduMateStorageManager.saveEduMate(original); // file path not specified
        readBack = eduMateStorageManager.readEduMate().get(); // file path not specified
        assertEquals(original.getPersonList(), new EduMate(readBack).getPersonList());

    }

    @Test
    public void saveEduMate_nullEduMate_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> saveEduMate(
                        null,
                        "SomeFile.json",
                        "some_file"));
    }

    /**
     * Saves {@code eduMate} at the specified {@code filePath}.
     */
    private void saveEduMate(ReadOnlyEduMate eduMate, String storageFilePath, String historyFilePath) {
        try {
            new EduMateStorageManager(Paths.get(storageFilePath), Paths.get(historyFilePath))
                    .saveEduMate(eduMate, addToTestDataPathIfNotNull(storageFilePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEduMate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEduMate(new EduMate(), null, null));
    }
}
