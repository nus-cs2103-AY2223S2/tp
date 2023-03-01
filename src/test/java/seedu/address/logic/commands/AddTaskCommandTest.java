package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.*;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TaskBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_TaskAcceptedByModel_addSuccessful() throws Exception {
        AddTaskCommandTest.OfficeConnectModelStubAcceptingTaskAdded OfficeConnectStub =
                new AddTaskCommandTest.OfficeConnectModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(new ModelStub(), OfficeConnectStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), OfficeConnectStub.getTaskModelManager().returnList());
    }

    private class OfficeConnectModelStub extends OfficeConnectModel {

        public RepositoryModelManager<Task> getTaskModelManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public RepositoryModelManager<PersonTask> getPersonTaskModelManager() {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class OfficeConnectModelStubAcceptingTaskAdded extends OfficeConnectModel {
        public OfficeConnectModelStubAcceptingTaskAdded() {
             super(new RepositoryModelManager<Task>(new Repository<Task>()) {
                 final ArrayList<Task> tasksAdded = new ArrayList<>();

                 @Override
                 public boolean hasItem(Task task) {
                     requireNonNull(task);
                     return tasksAdded.stream().anyMatch(task::isSame);
                 }

                 @Override
                 public void addItem(Task task) {
                     requireNonNull(task);
                     tasksAdded.add(task);
                 }
                 @Override
                 public ArrayList<Task> returnList() {
                     return tasksAdded;
                 }
             },
            new RepositoryModelManager<PersonTask>(new Repository<PersonTask>()));
        }
    }
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
}