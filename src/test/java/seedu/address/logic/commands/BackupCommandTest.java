package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.BackupData;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBackupData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.backup.Backup;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.storage.BackupDataStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.testutil.AddressBookBuilder;


public class BackupCommandTest {

    @Test
    public void constructor_noIndex_throwsCommandException() {
        assertThrows(NullPointerException.class, () -> new BackupCommand(null, null));
    }

    @Test
    public void constructor_invalidIndex_throwsIllegalArgumentException() throws Exception {
        Index invalidIndex = Index.fromOneBased(11);
        ModelStub modelStub = new ModelStubWithBackupFunctionalities();

        assertThrows(IllegalArgumentException.class, () -> new BackupCommand(invalidIndex, null));
    }

    @Test
    public void execute_backupSuccessful() throws CommandException {
        Index validIndex = Index.fromOneBased(10);
        String validDesc = "test";
        String filePath = "data/backup/addressbookBackup10.json";
        ModelStub modelStub = new ModelStubWithBackupFunctionalities();

        CommandResult commandResult = new BackupCommand(validIndex, validDesc).execute(modelStub);

        assertEquals(String.format(BackupCommand.MESSAGE_SUCCESS, validIndex.getOneBased()),
            commandResult.getFeedbackToUser());
        cleanUp(filePath);
    }

    @Test
    public void equals() {
        Index one = Index.fromOneBased(1);
        Index two = Index.fromOneBased(2);
        BackupCommand backupCommandOne = new BackupCommand(one, null);
        BackupCommand backupCommandOneCopy = new BackupCommand(one, null);
        BackupCommand backupCommandTwo = new BackupCommand(two, null);

        // same object -> returns true
        assertTrue(backupCommandOne.equals(backupCommandOne));

        // same values -> returns true
        assertTrue(backupCommandOne.equals(backupCommandOneCopy));

        // different types -> returns false
        assertFalse(backupCommandOne.equals(1));

        // null -> returns false
        assertFalse(backupCommandOne.equals(null));

        // different person -> returns false
        assertFalse(backupCommandOne.equals(backupCommandTwo));
    }

    private void cleanUp(String path) {
        File toCleanup = new File(path);
        toCleanup.delete();
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UserPrefsStorage getUserPrefsStorage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBackupData(BackupData backupData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBackupToBackupData(Backup backup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeBackupFromBackupData(String index) throws IndexOutOfBoundsException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public BackupData getBackupData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public BackupDataStorage getBackupDataStorage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person findPersonByNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Backup> getBackupList() throws IllegalValueException {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithBackupFunctionalities extends ModelStub {
        private final AddressBook addressBook = new AddressBookBuilder().build();
        private final Path userPrefsPath = Path.of("preferences.json");

        @Override
        public AddressBook getAddressBook() {
            return addressBook;
        }

        @Override
        public void addBackupToBackupData(Backup backup) {
        }

        @Override
        public BackupData getBackupData() {
            return null;
        }

        @Override
        public UserPrefsStorage getUserPrefsStorage() {
            return new JsonUserPrefsStorage(userPrefsPath);
        }

        @Override
        public BackupDataStorage getBackupDataStorage() {
            return new BackupDataStorageStub();
        }
    }

    private class BackupDataStorageStub implements BackupDataStorage {

        @Override
        public Path getBackupDataFilePath() {
            return null;
        }

        @Override
        public Optional<BackupData> readBackupData() throws DataConversionException, IOException {
            return Optional.empty();
        }

        @Override
        public void saveBackupData(ReadOnlyBackupData backupData) throws IOException {

        }
    }
}
