package seedu.internship.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.ReadOnlyEventCatalogue;
import seedu.internship.model.event.Event;
import seedu.internship.testutil.EventBuilder;
import seedu.internship.testutil.TypicalEvents;

public class JsonEventCatalogueStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonEventCatalogueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEventCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEventCatalogue(null));
    }

    private Optional<ReadOnlyEventCatalogue> readEventCatalogue(String filePath) throws Exception {
        return new JsonEventCatalogueStorage(Paths.get(filePath)).readEventCatalogue(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEventCatalogue("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEventCatalogue(
                "notJsonFormatEventCatalogue.json"));
    }

    @Test
    public void readEventCatalogue_invalidEventEventCatalogue_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEventCatalogue(
                "invalidEventEventCatalogue.json"));
    }

    @Test
    public void readEventCatalogue_invalidAndValidEventEventCatalogue_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEventCatalogue(
                "invalidAndValidEventEventCatalogue.json"));
    }

    @Test
    public void readAndSaveEventCatalogue_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEventCatalogue.json");
        EventCatalogue original = TypicalEvents.getTypicalEventCatalogue();
        JsonEventCatalogueStorage jsonEventCatalogueStorage = new JsonEventCatalogueStorage(filePath);

        // Save in new file and read back
        jsonEventCatalogueStorage.saveEventCatalogue(original, filePath);
        ReadOnlyEventCatalogue readBack = jsonEventCatalogueStorage.readEventCatalogue(filePath).get();
        assertTrue(original.equals(new EventCatalogue(readBack)));

        // Modify data, overwrite exiting file, and read back
        Event event = new EventBuilder().withName("Interview").withStart("15/04/2023 2000").withEnd("15/04/2023 2200")
                        .build();
        original.addEvent(event);
        original.removeEvent(event);
        jsonEventCatalogueStorage.saveEventCatalogue(original, filePath);
        readBack = jsonEventCatalogueStorage.readEventCatalogue(filePath).get();
        assertTrue(original.equals(new EventCatalogue(readBack)));

        // Save and read without specifying file path
        original.addEvent(event);
        jsonEventCatalogueStorage.saveEventCatalogue(original); // file path not specified
        readBack = jsonEventCatalogueStorage.readEventCatalogue().get(); // file path not specified
        assertTrue(original.equals(new EventCatalogue(readBack)));
    }

    @Test
    public void saveEventCatalogue_nullEventCatalogue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventCatalogue(
                null, "SomeFile.json"));
    }

    /**
     * Saves {@code eventCatalogue} at the specified {@code filePath}.
     */
    private void saveEventCatalogue(ReadOnlyEventCatalogue eventCatalogue, String filePath) {
        try {
            new JsonEventCatalogueStorage(Paths.get(filePath))
                    .saveEventCatalogue(eventCatalogue, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEventCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventCatalogue(
                new EventCatalogue(), null));
    }
}

