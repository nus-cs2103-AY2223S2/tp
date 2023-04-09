package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskBookModel;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub, new TaskBookModelStub());

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () ->
            addCommand.execute(modelStub, new TaskBookModelStub()));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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

        @Override
        public boolean hasPersonIndex(Index personIndex) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    private class TaskBookModelStub implements TaskBookModel {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setUserPrefs'");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getUserPrefs'");
        }

        @Override
        public GuiSettings getGuiSettings() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getGuiSettings'");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setGuiSettings'");
        }

        @Override
        public Path getTaskBookFilePath() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTaskBookFilePath'");
        }

        @Override
        public void setTaskBook(ReadOnlyTaskBook taskBook) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setTaskBook'");
        }

        @Override
        public ReadOnlyTaskBook getTaskBook() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTaskBook'");
        }

        @Override
        public boolean hasTask(Task task) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasTask'");
        }

        @Override
        public boolean hasTaskIndex(Index taskIndex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasTaskIndex'");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setTask'");
        }

        @Override
        public void deleteTask(Task target) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
        }

        @Override
        public void addTask(Task target) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addTask'");
        }

        @Override
        public void markTask(Task taskToMark, Task markedTask, Index taskIndex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'markTask'");
        }

        @Override
        public void unmarkTask(Task taskToUnmark, Task unmarkedTask, Index taskIndex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'unmarkTask'");
        }

        @Override
        public void commentOnTask(Task taskToComment, Task commentedTask, Index taskIndex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'commentOnTask'");
        }

        @Override
        public void assignTask(Task taskToAssign, Task assignedTask, Index taskIndex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'assignTask'");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFilteredTaskList'");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredTaskList'");
        }

        @Override
        public void deletePersonFromTask(Index personIndex) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deletePersonFromTask'");
        }

    }

}
