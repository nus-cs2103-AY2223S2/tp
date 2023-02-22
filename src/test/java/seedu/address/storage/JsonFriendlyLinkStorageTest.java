package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalFriendlyLink;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyFriendlyLink;

public class JsonFriendlyLinkStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFriendlyLinkStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFriendlyLink_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFriendlyLink(null));
    }

    private java.util.Optional<ReadOnlyFriendlyLink> readFriendlyLink(String filePath) throws Exception {
        return new JsonFriendlyLinkStorage(Paths.get(filePath)).readFriendlyLink(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFriendlyLink("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFriendlyLink("notJsonFormatFriendlyLink.json"));
    }

    @Test
    public void readFriendlyLink_invalidPersonFriendlyLink_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFriendlyLink("invalidPersonFriendlyLink.json"));
    }

    @Test
    public void readFriendlyLink_invalidAndValidPersonFriendlyLink_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFriendlyLink("invalidAndValidPersonFriendlyLink.json"));
    }

    @Test
    public void readAndSaveFriendlyLink_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFriendlyLink.json");
        FriendlyLink original = getTypicalFriendlyLink();
        JsonFriendlyLinkStorage jsonFriendlyLinkStorage = new JsonFriendlyLinkStorage(filePath);

        // Save in new file and read back
        jsonFriendlyLinkStorage.saveFriendlyLink(original, filePath);
        ReadOnlyFriendlyLink readBack = jsonFriendlyLinkStorage.readFriendlyLink(filePath).get();
        assertEquals(original, new FriendlyLink(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonFriendlyLinkStorage.saveFriendlyLink(original, filePath);
        readBack = jsonFriendlyLinkStorage.readFriendlyLink(filePath).get();
        assertEquals(original, new FriendlyLink(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonFriendlyLinkStorage.saveFriendlyLink(original); // file path not specified
        readBack = jsonFriendlyLinkStorage.readFriendlyLink().get(); // file path not specified
        assertEquals(original, new FriendlyLink(readBack));

    }

    @Test
    public void saveFriendlyLink_nullFriendlyLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriendlyLink(null, "SomeFile.json"));
    }

    /**
     * Saves {@code friendlyLink} at the specified {@code filePath}.
     */
    private void saveFriendlyLink(ReadOnlyFriendlyLink friendlyLink, String filePath) {
        try {
            new JsonFriendlyLinkStorage(Paths.get(filePath))
                    .saveFriendlyLink(friendlyLink, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFriendlyLink_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriendlyLink(new FriendlyLink(), null));
    }
}
