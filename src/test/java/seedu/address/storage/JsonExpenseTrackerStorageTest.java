package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ExpenseTracker;
import seedu.address.model.ReadOnlyExpenseTracker;

public class JsonExpenseTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExpenseTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExpenseTracker(null));
    }

    private java.util.Optional<ReadOnlyExpenseTracker> readExpenseTracker(String filePath) throws Exception {
        return new JsonExpenseTrackerStorage(Paths.get(filePath))
                .readExpenseTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExpenseTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExpenseTracker("notJsonFormatExpenseTracker.json"));
    }
    /**
     * Valid Category data
     * Invalid Category data
     * Valid Description data
     * Invalid Description data
     * Valid Amount data
     * Invalid Amount data
     * Valid Date data
     * Invalid Date data
     * Valid Expense data
     * Invalid Expense data 
     * Combinations
     */
    
    @Test
    public void saveExpenseTracker_nullExpenseTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker, String filePath) {
        try {
            new JsonExpenseTrackerStorage(Paths.get(filePath))
                    .saveExpenseTracker(expenseTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExpenseTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseTracker(new ExpenseTracker(), null));
    }
}
