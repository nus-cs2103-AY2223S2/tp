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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

class AddTaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        RepositoryModelManagerStub repoModelManagerStub = new RepositoryModelManagerStub();
        OfficeConnectModel testModel = new OfficeConnectModel(repoModelManagerStub,
                new RepositoryModelManager<AssignTask>(new Repository<AssignTask>()));
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(new ModelStub(), testModel);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), repoModelManagerStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        RepositoryModelManagerStubWithTask repositoryModelManagerStubWithTask =
                new RepositoryModelManagerStubWithTask(validTask);
        OfficeConnectModel testModel = new OfficeConnectModel(repositoryModelManagerStubWithTask,
                new RepositoryModelManager<AssignTask>(new Repository<AssignTask>()));

        assertThrows(CommandException.class, AddTaskCommand.MESSAGE_DUPLICATE_TASK, () ->
                addTaskCommand.execute(new ModelStub(), testModel));
    }
    @Test
    public void equals() {
        Task taskEat = new TaskBuilder().withTitle("Recreation Day").build();
        Task taskDrink = new TaskBuilder().withTitle("Project RUN").build();
        AddTaskCommand addTaskEatCommand = new AddTaskCommand(taskEat);
        AddTaskCommand addTaskDrinkCommand = new AddTaskCommand(taskDrink);

        // same object -> returns true
        assertTrue(addTaskEatCommand.equals(addTaskEatCommand));

        // same values -> returns true
        AddTaskCommand addTaskEatCommandCopy = new AddTaskCommand(taskEat);
        assertTrue(addTaskEatCommand.equals(addTaskEatCommandCopy));

        // different types -> returns false
        assertFalse(addTaskEatCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskEatCommand.equals(null));

        // different person -> returns false
        assertFalse(addTaskEatCommand.equals(addTaskDrinkCommand));
    }

    private class RepositoryModelManagerStub extends RepositoryModelManager<Task> {
        final ArrayList<Task> tasksAdded = new ArrayList<>();
        RepositoryModelManagerStub() {
            super(new Repository<Task>());
        }
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
    }

    private class RepositoryModelManagerStubWithTask extends RepositoryModelManager<Task> {
        private final Task task;
        RepositoryModelManagerStubWithTask(Task task) {
            super(new Repository<Task>());
            requireNonNull(task);
            this.task = task;
        }
        @Override
        public boolean hasItem(Task task) {
            requireNonNull(task);
            return this.task.isSame(task);
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
