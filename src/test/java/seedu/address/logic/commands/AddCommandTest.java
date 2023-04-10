package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyNote;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;
import seedu.address.testutil.InternshipBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternshipAdded modelStub = new ModelStubAcceptingInternshipAdded();
        InternshipApplication validInternship = new InternshipBuilder().build();

        CommandResult commandResult = new AddCommand(validInternship).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validInternship), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternship), modelStub.internshipsAdded);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        InternshipApplication validInternship = new InternshipBuilder().build();
        AddCommand addCommand = new AddCommand(validInternship);
        ModelStub modelStub = new ModelStubWithInternship(validInternship);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_INTERNSHIP, ()
            -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        InternshipApplication bankOfAmerica = new InternshipBuilder().withCompanyName("Bank of America").build();
        InternshipApplication deutscheBank = new InternshipBuilder().withCompanyName("Deutsche Bank").build();
        AddCommand addBankOfAmericaCommand = new AddCommand(bankOfAmerica);
        AddCommand addDeutscheBankCommand = new AddCommand(deutscheBank);

        // same object -> returns true
        assertTrue(addBankOfAmericaCommand.equals(addBankOfAmericaCommand));

        // same values -> returns true
        AddCommand addBankOfAmericaCommandCopy = new AddCommand(bankOfAmerica);
        assertTrue(addBankOfAmericaCommand.equals(addBankOfAmericaCommandCopy));

        // different types -> returns false
        assertFalse(addBankOfAmericaCommand.equals(1));

        // null -> returns false
        assertFalse(addBankOfAmericaCommand.equals(null));

        // different person -> returns false
        assertFalse(addBankOfAmericaCommand.equals(addDeutscheBankCommand));
    }

    /**
     * A default model stub that have all the methods failing.
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
        public Path getNoteListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTodoListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(InternshipApplication person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(InternshipTodo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addNote(Note note) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplications(List<InternshipApplication> applications) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public InternshipApplication getReminder() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateReminder() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternEase(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTodoList getTodoList() {
            return null;
        }

        @Override
        public ReadOnlyNote getNoteList() {
            return null;
        }

        @Override
        public boolean hasApplication(InternshipApplication application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTodo(InternshipTodo todo) {
            return false;
        }

        @Override
        public boolean hasNote(Note note) {
            return false;
        }

        @Override
        public void deleteInternship(InternshipApplication target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(InternshipTodo target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNote(Note target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearTodo(ReadOnlyTodoList internEase) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearNote(ReadOnlyNote internEase) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(InternshipApplication target,
                                            InternshipApplication editedApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTodo(InternshipTodo target, InternshipTodo editedTodo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNote(Note target, Note editedNote) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternshipApplication> getSortedFilteredInternshipList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternshipTodo> getFilteredTodoList() {
            return null;
        }

        @Override
        public ObservableList<Note> getFilteredNoteList() {
            return null;
        }

        @Override
        public List<InternshipApplication> getCachedInternshipList() {
            return null;
        }

        @Override
        public InternshipApplication getAndRemoveCachedApplication() {
            return null;
        }

        @Override
        public void addInternshipToCache(InternshipApplication application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAllInternshipToCache(List<InternshipApplication> application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEmptyInternshipCacheList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipList(Predicate<InternshipApplication> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTodoList(Predicate<InternshipTodo> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredNoteList(Predicate<Note> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedFilteredInternshipList(Comparator<InternshipApplication> comparator) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single internship.
     */
    private class ModelStubWithInternship extends ModelStub {
        private final InternshipApplication internship;

        ModelStubWithInternship(InternshipApplication internship) {
            requireNonNull(internship);
            this.internship = internship;
        }

        @Override
        public boolean hasApplication(InternshipApplication internship) {
            requireNonNull(internship);
            return this.internship.isSameApplication(internship);
        }
    }

    /**
     * A Model stub that always accept the internship application being added.
     */
    private class ModelStubAcceptingInternshipAdded extends ModelStub {
        final ArrayList<InternshipApplication> internshipsAdded = new ArrayList<>();

        @Override
        public boolean hasApplication(InternshipApplication internship) {
            requireNonNull(internship);
            return internshipsAdded.stream().anyMatch(internship::isSameApplication);
        }

        @Override
        public void addApplication(InternshipApplication person) {
            requireNonNull(person);
            internshipsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
