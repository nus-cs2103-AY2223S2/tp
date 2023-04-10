package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class JsonExportStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonExportStorageTest");

    @Test
    public void exportPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exportPerson(null, "SomeFile.json"));
    }

    /**
     * Saves {@code personToExport} at the specified {@code filePath}.
     */
    private void exportPerson(Person personToExport, String filePath) {
        try {
            new JsonExportStorage(Paths.get(filePath))
                    .exportPerson(personToExport, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exportPerson(
                new PersonBuilder(ALICE).build(), null));
    }

}
