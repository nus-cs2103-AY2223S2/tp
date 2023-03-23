package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION_INTERVIEW;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
//import seedu.address.model.InternshipBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class AddTaskCommandTest {
    private ApplicationModel model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());

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
        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /*
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;

        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_taskAlreadyExists_throwsCommandException() {
        Index indexApplicationToAddTask = Index.fromOneBased(model.getFilteredApplicationList().size());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        AddTaskCommand addTaskCommand = new AddTaskCommand(indexApplicationToAddTask, descriptor);
        String expectedMessage = String.format(AddTaskCommand.MESSAGE_TASK_EXISTS);
        assertCommandFailure(addTaskCommand, model, expectedMessage);
    }

    @Test
    public void execute_taskAccepted_success() {
        Index indexApplicationToAddTask = INDEX_SECOND_APPLICATION;
        Application applicationToAddTask = model.getFilteredApplicationList()
                .get(indexApplicationToAddTask.getZeroBased());

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(indexApplicationToAddTask, descriptor);

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToAddTask);
        Application editedApplication = applicationInList.withTask(VALID_DEADLINE, VALID_DESCRIPTION).build();

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_SUCCESS, editedApplication.getTask());

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(applicationToAddTask, editedApplication);
        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }
     */

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
