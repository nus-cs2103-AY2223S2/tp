package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PetPal;
import seedu.address.model.ReadOnlyPetPal;

public class JsonPetPalStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPetPalStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPetPal_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPetPal(null));
    }

    private java.util.Optional<ReadOnlyPetPal> readPetPal(String filePath) throws Exception {
        Path fP = Paths.get(filePath);
        return new JsonPetPalStorage(fP, fP)
                .readPetPal(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPetPal("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPetPal("notJsonFormatPetPal.json"));
    }

    @Test
    public void readPetPal_invalidPersonPetPal_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPetPal("invalidPetPetPal.json"));
    }

    @Test
    public void readPetPal_invalidAndValidPersonPetPal_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPetPal("invalidAndValidPetPetPal.json"));
    }

    /*
    @Test
    public void readAndSavePetPal_allInOrder_success() throws Exception {
        Path filePath = TEST_DATA_FOLDER.resolve("TempPetPal.json");
        Path archiveFilePath = TEST_DATA_FOLDER.resolve("TempArchive.json");

        PetPal original = getTypicalPetPal();
        PetPal ogArchive = getTypicalArchive();

        JsonPetPalStorage jsonPetPalStorage = new JsonPetPalStorage(filePath, archiveFilePath);

        // Save in new file and read back
        jsonPetPalStorage.savePetPal(original, filePath);
        jsonPetPalStorage.savePetPalArchive(ogArchive, archiveFilePath);

        Path fP = Paths.get("src/test/data/JsonPetPalStorageTest/TempPetPal.json");
        ReadOnlyPetPal readBack = jsonPetPalStorage.readPetPal(filePath).get();
        ReadOnlyPetPal readArchive = jsonPetPalStorage.readPetPalArchive(filePath).get();

        assertEquals(original, new PetPal(readBack));
        assertEquals(ogArchive, new PetPal(readArchive));

        // Modify data, overwrite exiting file, and read back
        original.addPet(HOON);
        original.removePet(WHISKERS);
        jsonPetPalStorage.savePetPal(original, filePath);
        readBack = jsonPetPalStorage.readPetPal(filePath).get();
        assertEquals(original, new PetPal(readBack));

        // Save and read without specifying file path
        original.addPet(IDA);
        jsonPetPalStorage.savePetPal(original); // file path not specified
        readBack = jsonPetPalStorage.readPetPal().get(); // file path not specified
        assertEquals(original, new PetPal(readBack));

    }
     */

    @Test
    public void savePetPal_nullPetPal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePetPal(null, "SomeFile.json"));
    }

    /**
     * Saves {@code PetPal} at the specified {@code filePath}.
     */
    private void savePetPal(ReadOnlyPetPal petPal, String filePath) {
        try {
            new JsonPetPalStorage(Paths.get(filePath), Paths.get(filePath))
                    .savePetPal(petPal, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePetPal_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePetPal(new PetPal(), null));
    }

}

