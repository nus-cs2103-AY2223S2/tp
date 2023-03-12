package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code assign command}.
 */
public class AssignTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validTaskPersonIndex_success() throws Exception {
        Index taskToAssign = INDEX_FIRST_TASK;
        Index personToAssign = INDEX_SECOND_PERSON;

        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(taskToAssign, personToAssign);

        String expectedMessage = String.format(MESSAGE_SUCCESS, taskToAssign, personToAssign);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.assignTask(taskToAssign, personToAssign);
        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index personToAssign = INDEX_SECOND_PERSON;

        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(outOfBoundTaskIndex, personToAssign);

        assertCommandFailure(assignTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index taskToAssign = INDEX_FIRST_TASK;
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(taskToAssign, outOfBoundPersonIndex);

        assertCommandFailure(assignTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index taskToAssign = INDEX_FIRST_TASK;
        Index personToAssign = INDEX_SECOND_PERSON;

        AssignTaskCommand assignFirstCommand = new AssignTaskCommand(taskToAssign, personToAssign);
        AssignTaskCommand assignSecondCommand = new AssignTaskCommand(taskToAssign, personToAssign);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        assertTrue(assignFirstCommand.equals(assignSecondCommand));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(assignFirstCommand.equals(new AssignTaskCommand(INDEX_SECOND_TASK, personToAssign)));

        // different person -> returns false
        assertFalse(assignFirstCommand.equals(new AssignTaskCommand(taskToAssign, INDEX_FIRST_PERSON)));
    }



}
