package expresslibrary.storage;

import static expresslibrary.testutil.Assert.assertThrows;
//import static expresslibrary.testutil.TypicalPersons.ALICE;
//import static expresslibrary.testutil.TypicalPersons.HOON;
//import static expresslibrary.testutil.TypicalPersons.IDA;
//import static expresslibrary.testutil.TypicalPersons.getTypicalExpressLibrary;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import expresslibrary.commons.exceptions.DataConversionException;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.ReadOnlyExpressLibrary;

public class JsonExpressLibraryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonExpressLibraryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExpressLibrary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExpressLibrary(null));
    }

    private java.util.Optional<ReadOnlyExpressLibrary> readExpressLibrary(String filePath) throws Exception {
        return new JsonExpressLibraryStorage(Paths.get(filePath))
                .readExpressLibrary(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExpressLibrary("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExpressLibrary("notJsonFormatExpressLibrary.json"));
    }

    @Test
    public void readExpressLibrary_invalidPersonExpressLibrary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpressLibrary("invalidPersonExpressLibrary.json"));
    }

    @Test
    public void readExpressLibrary_invalidAndValidPersonExpressLibrary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readExpressLibrary("invalidAndValidPersonExpressLibrary.json"));
    }

    @Test
    public void readAndSaveExpressLibrary_allInOrder_success() throws Exception {
    //        Path filePath = testFolder.resolve("TempExpressLibrary.json");
    //        ExpressLibrary original = getTypicalExpressLibrary();
    //        JsonExpressLibraryStorage jsonExpressLibraryStorage = new JsonExpressLibraryStorage(filePath);
    //
    //        // Save in new file and read back
    //        jsonExpressLibraryStorage.saveExpressLibrary(original, filePath);
    //        ReadOnlyExpressLibrary readBack = jsonExpressLibraryStorage.readExpressLibrary(filePath).get();
    //        assertEquals(original, new ExpressLibrary(readBack));
    //
    //        // Modify data, overwrite exiting file, and read back
    //        original.addPerson(HOON);
    //        original.removePerson(ALICE);
    //        jsonExpressLibraryStorage.saveExpressLibrary(original, filePath);
    //        readBack = jsonExpressLibraryStorage.readExpressLibrary(filePath).get();
    //        assertEquals(original, new ExpressLibrary(readBack));
    //
    //        // Save and read without specifying file path
    //        original.addPerson(IDA);
    //        jsonExpressLibraryStorage.saveExpressLibrary(original); // file path not specified
    //        readBack = jsonExpressLibraryStorage.readExpressLibrary().get(); // file path not specified
    //        assertEquals(original, new ExpressLibrary(readBack));

    }

    @Test
    public void saveExpressLibrary_nullExpressLibrary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpressLibrary(null, "SomeFile.json"));
    }

    /**
     * Saves {@code expressLibrary} at the specified {@code filePath}.
     */
    private void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary, String filePath) {
        try {
            new JsonExpressLibraryStorage(Paths.get(filePath))
                    .saveExpressLibrary(expressLibrary, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExpressLibrary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpressLibrary(new ExpressLibrary(), null));
    }
}
