package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.DA1;
import static seedu.internship.testutil.TypicalInternships.ML1;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;

public class JsonInternshipCatalogueStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonInternshipCatalogueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternshipCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternshipCatalogue(null));
    }

    private java.util.Optional<ReadOnlyInternshipCatalogue> readInternshipCatalogue(String filePath) throws Exception {
        return new JsonInternshipCatalogueStorage(Paths.get(filePath)).readInternshipCatalogue(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternshipCatalogue("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInternshipCatalogue(
                "notJsonFormatInternshipCatalogue.json"));
    }

    @Test
    public void readInternshipCatalogue_invalidInternshipInternshipCatalogue_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternshipCatalogue(
                "invalidInternshipInternshipCatalogue.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidInternshipInternshipCatalogue_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInternshipCatalogue(
                "invalidAndValidInternshipInternshipCatalogue.json"));
    }

    @Test
    public void readAndSaveInternshipCatalogue_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternshipCatalogue.json");
        InternshipCatalogue original = getTypicalInternshipCatalogue();
        JsonInternshipCatalogueStorage jsonInternshipCatalogueStorage = new JsonInternshipCatalogueStorage(filePath);

        // Save in new file and read back
        jsonInternshipCatalogueStorage.saveInternshipCatalogue(original, filePath);
        ReadOnlyInternshipCatalogue readBack = jsonInternshipCatalogueStorage.readInternshipCatalogue(filePath).get();
        assertEquals(original, new InternshipCatalogue(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(ML1);
        original.removeInternship(ML1);
        jsonInternshipCatalogueStorage.saveInternshipCatalogue(original, filePath);
        readBack = jsonInternshipCatalogueStorage.readInternshipCatalogue(filePath).get();
        assertEquals(original, new InternshipCatalogue(readBack));

        // Save and read without specifying file path
        original.addInternship(DA1);
        jsonInternshipCatalogueStorage.saveInternshipCatalogue(original); // file path not specified
        readBack = jsonInternshipCatalogueStorage.readInternshipCatalogue().get(); // file path not specified
        assertEquals(original, new InternshipCatalogue(readBack));

    }

    @Test
    public void saveInternshipCatalogue_nullInternshipCatalogue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipCatalogue(
                null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveInternshipCatalogue(ReadOnlyInternshipCatalogue internshipCatalogue, String filePath) {
        try {
            new JsonInternshipCatalogueStorage(Paths.get(filePath))
                    .saveInternshipCatalogue(internshipCatalogue, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternshipCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipCatalogue(
                new InternshipCatalogue(), null));
    }
}

