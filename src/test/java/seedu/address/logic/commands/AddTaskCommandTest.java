package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddTaskCommand.
 */
public class AddTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Task task = new Task("Some task");

        assertCommandFailure(new AddTaskCommand(INDEX_FIRST_PERSON, task), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), task));
    }

    @Test
    public void equals() {
        final AddTaskCommand standardCommand = new AddTaskCommand(INDEX_FIRST_PERSON,
                new Task(VALID_TASK_AMY));

        // same values -> returns true
        AddTaskCommand commandWithSameValues = new AddTaskCommand(INDEX_FIRST_PERSON,
                new Task(VALID_TASK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTaskCommand(INDEX_SECOND_PERSON,
                new Task(VALID_TASK_AMY))));

        // different task -> returns false
        assertFalse(standardCommand.equals(new AddTaskCommand(INDEX_FIRST_PERSON,
                new Task(VALID_TASK_BOB))));
    }
}