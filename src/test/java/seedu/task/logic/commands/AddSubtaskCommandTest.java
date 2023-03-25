package seedu.task.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalSubtasks.ALICE_HOMEWORK;
import static seedu.task.testutil.TypicalSubtasks.AMY_LAB;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.task.commons.core.GuiSettings;
import seedu.task.commons.core.Messages;
import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.ReadOnlyUserPrefs;
import seedu.task.model.task.Subtask;
import seedu.task.model.task.Task;
import seedu.task.model.task.UniqueTaskList;
import seedu.task.testutil.SimpleTaskBuilder;
import seedu.task.testutil.SubtaskBuilder;

public class AddSubtaskCommandTest {

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSubtaskCommand((Index) null, (Subtask) null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        Task validTask = new SimpleTaskBuilder().withSubtasks(ALICE_HOMEWORK).build();
        ModelStub modelStub = new ModelStubWithTask(validTask);
        CommandResult commandResult = new AddSubtaskCommand(Index.fromOneBased(1),
            AMY_LAB).execute(modelStub);

        assertEquals(String.format(AddSubtaskCommand.MESSAGE_SUCCESS, AMY_LAB), commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task validTask = new SimpleTaskBuilder().withSubtasks(ALICE_HOMEWORK).build();
        AddSubtaskCommand addSubtaskCommand = new AddSubtaskCommand(Index.fromOneBased(1), ALICE_HOMEWORK);
        ModelStub modelStub = new ModelStubWithTask(validTask);
        assertThrows(CommandException.class, AddSubtaskCommand.MESSAGE_DUPLICATE_TASK, () -> addSubtaskCommand
            .execute(modelStub));
    }

    @Test
    public void execute_taskIndexOutOfBounds_throwsCommandException() {
        Task validTask = new SimpleTaskBuilder().withSubtasks(ALICE_HOMEWORK).build();
        AddSubtaskCommand addSubtaskCommand = new AddSubtaskCommand(Index.fromOneBased(2), AMY_LAB);
        AddSubtaskCommandTest.ModelStub modelStub = new AddSubtaskCommandTest.ModelStubWithTask(validTask);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, () -> addSubtaskCommand
            .execute(modelStub));
    }



    @Test
    public void equals() {
        Subtask alice = new SubtaskBuilder().withName("Alice").build();
        Subtask bob = new SubtaskBuilder().withName("Bob").build();
        AddSubtaskCommand addAliceCommand = new AddSubtaskCommand(Index.fromOneBased(1), alice);
        AddSubtaskCommand addBobCommand = new AddSubtaskCommand(Index.fromOneBased(1), bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddSubtaskCommand addAliceCommandCopy = new AddSubtaskCommand(Index.fromOneBased(1), alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different task -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getTaskBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskBookFilePath(Path taskBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskBook(ReadOnlyTaskBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskBook getTaskBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void plan(int value) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getAlertTaskList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAlertTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        private final UniqueTaskList taskList = new UniqueTaskList();

        private final FilteredList<Task> filteredList = new FilteredList<>(taskList.asUnmodifiableObservableList());

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
            this.taskList.add(task);
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return this.filteredList;
        }

        @Override
        public void setTask(Task taskToEdit, Task newTask) {
            this.taskList.setTask(taskToEdit, newTask);
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            requireNonNull(predicate);
            filteredList.setPredicate(predicate);
        }



    }
}
