package codoc.storage;

import static codoc.testutil.Assert.assertThrows;
import static codoc.testutil.TypicalPersons.ALICE;
import static codoc.testutil.TypicalPersons.HOON;
import static codoc.testutil.TypicalPersons.IDA;
import static codoc.testutil.TypicalPersons.getTypicalCodoc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import codoc.commons.exceptions.DataConversionException;
import codoc.model.Codoc;
import codoc.model.ReadOnlyCodoc;

public class JsonCodocStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCodocStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCodoc_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCodoc(null));
    }

    private java.util.Optional<ReadOnlyCodoc> readCodoc(String filePath) throws Exception {
        return new JsonCodocStorage(Paths.get(filePath)).readCodoc(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCodoc("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCodoc("notJsonFormatCodoc.json"));
    }

    @Test
    public void readCodoc_invalidPersonCodoc_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCodoc("invalidPersonCodoc.json"));
    }

    @Test
    public void readCodoc_invalidAndValidPersonCodoc_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCodoc("invalidAndValidPersonCodoc.json"));
    }

    @Test
    public void readAndSaveCodoc_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCodoc.json");
        Codoc original = getTypicalCodoc();
        JsonCodocStorage jsonCodocStorage = new JsonCodocStorage(filePath);

        // Save in new file and read back
        jsonCodocStorage.saveCodoc(original, filePath);
        ReadOnlyCodoc readBack = jsonCodocStorage.readCodoc(filePath).get();
        assertEquals(original, new Codoc(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonCodocStorage.saveCodoc(original, filePath);
        readBack = jsonCodocStorage.readCodoc(filePath).get();
        assertEquals(original, new Codoc(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonCodocStorage.saveCodoc(original); // file path not specified
        readBack = jsonCodocStorage.readCodoc().get(); // file path not specified
        assertEquals(original, new Codoc(readBack));

    }

    @Test
    public void saveCodoc_nullCodoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCodoc(null, "SomeFile.json"));
    }

    /**
     * Saves {@code codoc} at the specified {@code filePath}.
     */
    private void saveCodoc(ReadOnlyCodoc codoc, String filePath) {
        try {
            new JsonCodocStorage(Paths.get(filePath))
                    .saveCodoc(codoc, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCodoc_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCodoc(new Codoc(), null));
    }
}
