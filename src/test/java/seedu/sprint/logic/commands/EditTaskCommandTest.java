package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION_INTERVIEW;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_THIRD_APPLICATION;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.task.Task;
import seedu.sprint.testutil.ApplicationBuilder;
import seedu.sprint.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandTest {
    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void execute_taskDoesNotExist_throwsCommandException() {
        Index indexApplicationToEditTask = INDEX_THIRD_APPLICATION;
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexApplicationToEditTask, editTaskDescriptor);

        assertCommandFailure(editTaskCommand, model, commandHistory, EditTaskCommand.MESSAGE_TASK_DOES_NOT_EXIST);
    }


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexApplicationToEditTask = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application applicationToEditTask = model.getFilteredApplicationList()
                .get(indexApplicationToEditTask.getZeroBased());
        Task taskToEdit = applicationToEditTask.getTask();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(taskToEdit)
                .withDeadline(VALID_DEADLINE).withDescription(VALID_DESCRIPTION).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexApplicationToEditTask, descriptor);

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToEditTask);
        Application editedApplication =
                applicationInList.withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_SUCCESS, editedApplication.getTask());
        Model expectedModel = new ModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs()
        );
        expectedModel.setApplication(applicationToEditTask, editedApplication);
        expectedModel.commitInternshipBookChange();
        assertCommandSuccess(editTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }


    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        Index indexApplicationToEditTask = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application applicationToEditTask = model.getFilteredApplicationList()
                .get(indexApplicationToEditTask.getZeroBased());
        Task taskToEdit = applicationToEditTask.getTask();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(taskToEdit)
                .withDeadline(VALID_DEADLINE).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexApplicationToEditTask, descriptor);

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToEditTask);
        Application editedApplication =
                applicationInList.withDeadline(VALID_DEADLINE).build();

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_SUCCESS, editedApplication.getTask());
        Model expectedModel = new ModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs()
        );
        expectedModel.setApplication(applicationToEditTask, editedApplication);
        expectedModel.commitInternshipBookChange();
        assertCommandSuccess(editTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        showApplicationAtIndex(model, indexLastApplication);

        Application applicationToEditTask = model.getFilteredApplicationList().get(0);
        Task taskToEdit = applicationToEditTask.getTask();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(taskToEdit)
                .withDeadline(VALID_DEADLINE).withDescription(VALID_DESCRIPTION).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(Index.fromZeroBased(0), descriptor);

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToEditTask);
        Application editedApplication =
                applicationInList.withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();
        String expectedMessage = String.format(EditTaskCommand.MESSAGE_SUCCESS, editedApplication.getTask());

        Model expectedModel = new ModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs()
        );
        expectedModel.setApplication(applicationToEditTask, editedApplication);
        expectedModel.commitInternshipBookChange();
        assertCommandSuccess(editTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Index indexSecondLastApplication =
                Index.fromOneBased(model.getFilteredApplicationList().size() - 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_DEADLINE)
                .withDescription(VALID_DESCRIPTION)
                .build();
        EditTaskDescriptor otherDescriptor = new EditTaskDescriptorBuilder()
                .withDeadline(VALID_DEADLINE)
                .withDescription(VALID_DESCRIPTION_INTERVIEW)
                .build();
        final EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastApplication, descriptor);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(descriptor);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(indexLastApplication, copyDescriptor);
        assertTrue(editTaskCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(editTaskCommand.equals(editTaskCommand));

        // null -> returns false
        assertFalse(editTaskCommand.equals(null));

        // different types -> returns false
        assertFalse(editTaskCommand.equals(new DeleteTaskCommand(indexLastApplication)));

        // different index -> returns false
        assertFalse(editTaskCommand.equals(new EditTaskCommand(indexSecondLastApplication, descriptor)));

        // different descriptor -> returns false
        assertFalse(editTaskCommand.equals(new EditTaskCommand(indexLastApplication, otherDescriptor)));
    }
}
