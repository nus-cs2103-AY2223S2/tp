package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION_INTERVIEW;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.sprint.testutil.Assert.assertThrows;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_THIRD_APPLICATION;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.testutil.ApplicationBuilder;
import seedu.sprint.testutil.EditApplicationDescriptorBuilder;

public class AddTaskCommandTest {
    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Application editedApplication = new ApplicationBuilder().build();
        EditApplicationDescriptor editApplicationDescriptor =
                new EditApplicationDescriptorBuilder(editedApplication).build();
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, editApplicationDescriptor));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(addTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;

        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(addTaskCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_taskAlreadyExists_throwsCommandException() {
        Index indexApplicationToAddTask = Index.fromOneBased(model.getFilteredApplicationList().size());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        AddTaskCommand addTaskCommand = new AddTaskCommand(indexApplicationToAddTask, descriptor);
        String expectedMessage = String.format(AddTaskCommand.MESSAGE_TASK_EXISTS);
        assertCommandFailure(addTaskCommand, model, commandHistory, expectedMessage);
    }


    @Test
    public void execute_taskAccepted_success() {
        Index indexApplicationToAddTask = INDEX_THIRD_APPLICATION;
        Application applicationToAddTask = model.getFilteredApplicationList()
                .get(indexApplicationToAddTask.getZeroBased());

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(indexApplicationToAddTask, descriptor);

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToAddTask);
        Application editedApplication = applicationInList.withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_SUCCESS, editedApplication.getTask());

        Model expectedModel = new ModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(applicationToAddTask, editedApplication);
        expectedModel.commitInternshipBookChange();
        assertCommandSuccess(addTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();
        final AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_APPLICATION, descriptor);
        EditApplicationDescriptor otherDescriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION_INTERVIEW).build();

        // same values -> returns true
        EditApplicationDescriptor copyDescriptor = new EditApplicationDescriptor(descriptor);
        AddTaskCommand commandWithSameValues = new AddTaskCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(addTaskCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(addTaskCommand.equals(addTaskCommand));

        // null -> returns false
        assertFalse(addTaskCommand.equals(null));

        // different types -> returns false
        assertFalse(addTaskCommand.equals(new DeleteTaskCommand(INDEX_FIRST_APPLICATION)));

        // different index -> returns false
        assertFalse(addTaskCommand.equals(new AddTaskCommand(INDEX_SECOND_APPLICATION, descriptor)));

        // different descriptor -> returns false
        assertFalse(addTaskCommand.equals(new AddTaskCommand(INDEX_FIRST_APPLICATION, otherDescriptor)));
    }
}
