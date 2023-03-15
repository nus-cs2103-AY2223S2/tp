package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;


public class BackupCommandTest {

    @Test
    public void constructor_noIndex_throwsCommandException() {
        assertThrows(NullPointerException.class, () -> new BackupCommand(null));
    }

    @Test
    public void constructor_invalidIndex_throwsIllegalArgumentException() throws Exception {
        Index invalidIndex = Index.fromOneBased(11);
        ModelStub modelStub = new ModelStubWithGetAddressBook();

        assertThrows(IllegalArgumentException.class, () -> new BackupCommand(invalidIndex));
    }

    @Test
    public void equals() {
        Index one = Index.fromOneBased(1);
        Index two = Index.fromOneBased(2);
        BackupCommand backupCommandOne = new BackupCommand(one);
        BackupCommand backupCommandOneCopy = new BackupCommand(one);
        BackupCommand backupCommandTwo = new BackupCommand(two);

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
    }

    private class ModelStubWithGetAddressBook extends ModelStub {
        private final AddressBook addressBook = new AddressBookBuilder().build();

        @Override
        public AddressBook getAddressBook() {
            return addressBook;
        }
    }
}
