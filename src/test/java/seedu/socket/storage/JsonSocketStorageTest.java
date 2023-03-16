package seedu.socket.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.HOON;
import static seedu.socket.testutil.TypicalPersons.IDA;
import static seedu.socket.testutil.TypicalPersons.getTypicalSocket;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.Socket;

public class JsonSocketStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSocketStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSocket_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSocket(null));
    }

    private java.util.Optional<ReadOnlySocket> readSocket(String filePath) throws Exception {
        return new JsonSocketStorage(Paths.get(filePath)).readSocket(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSocket("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSocket("notJsonFormaSocket.json"));
    }

    @Test
    public void readSocket_invalidPersonSocket_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSocket("invalidPersonSocket.json"));
    }

    @Test
    public void readSocket_invalidAndValidPersonSocket_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSocket("invalidAndValidPersonSocket.json"));
    }

    @Test
    public void readAndSaveSocket_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Socket original = getTypicalSocket();
        JsonSocketStorage jsonAddressBookStorage = new JsonSocketStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveSocket(original, filePath);
        ReadOnlySocket readBack = jsonAddressBookStorage.readSocket(filePath).get();
        assertEquals(original, new Socket(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveSocket(original, filePath);
        readBack = jsonAddressBookStorage.readSocket(filePath).get();
        assertEquals(original, new Socket(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveSocket(original); // file path not specified
        readBack = jsonAddressBookStorage.readSocket().get(); // file path not specified
        assertEquals(original, new Socket(readBack));
    }

    @Test
    public void saveSocket_nullSocket_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSocket(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Socket} at the specified {@code filePath}.
     */
    private void saveSocket(ReadOnlySocket socket, String filePath) {
        try {
            new JsonSocketStorage(Paths.get(filePath))
                    .saveSocket(socket, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSocket_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSocket(new Socket(), null));
    }
}
