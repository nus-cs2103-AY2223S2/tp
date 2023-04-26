package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.UnassignCommand.MESSAGE_NON_EXIST_ASSIGNMENT;
import static seedu.address.logic.commands.UnassignCommand.MESSAGE_SUCCESS;
import static seedu.address.model.util.TypicalPersons.ALICE;
import static seedu.address.model.util.TypicalPersons.BENSON;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;
import seedu.address.model.util.TypicalTasks;

/**
 * Contains integration and unit tests for {@code AssignCommand}.
 */
public class UnassignCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final OfficeConnectModel officeConnectModel = new OfficeConnectModel();
    private final List<Task> tasks = TypicalTasks.getTypicalTasks();
    private final Task taskA = tasks.get(0);
    private final Task taskB = tasks.get(1);

    @BeforeEach
    public void setUp() {
        officeConnectModel.getTaskModelManager().addItem(taskA);
        officeConnectModel.getTaskModelManager().addItem(taskB);

        AssignTask assignTask1 = new AssignTask(ALICE.getId(), taskA.getId());
        AssignTask assignTask2 = new AssignTask(BENSON.getId(), taskB.getId());
        officeConnectModel.getAssignTaskModelManager().addItem(assignTask1);
        officeConnectModel.getAssignTaskModelManager().addItem(assignTask2);
    }

    @Test
    public void execute_unassignValidPersonAndTask_success() throws CommandException {
        Index personIndex = Index.fromZeroBased(0); // ALICE
        Index taskIndex = Index.fromZeroBased(0); // TASK_A
        UnassignCommand unassignCommand = new UnassignCommand(personIndex, taskIndex);

        String expectedMessage = String.format(MESSAGE_SUCCESS, ALICE.getName(), taskA.getTitle());

        CommandResult commandResult = unassignCommand.execute(model, officeConnectModel);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_unassignNonexistentPerson_throwsCommandException() {
        Index personIndex = Index.fromZeroBased(model.getFilteredPersonList().size());
        Index taskIndex = Index.fromZeroBased(0);
        UnassignCommand unassignCommand = new UnassignCommand(personIndex, taskIndex);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
            -> unassignCommand.execute(model, officeConnectModel));
    }

    @Test
    public void execute_unassignNonexistentTask_throwsCommandException() {
        Index personIndex = Index.fromZeroBased(0);
        Index taskIndex = Index.fromZeroBased(officeConnectModel.getTaskModelManager().getFilteredItemList().size());
        UnassignCommand unassignCommand = new UnassignCommand(personIndex, taskIndex);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
            -> unassignCommand.execute(model, officeConnectModel));
    }

    @Test
    public void execute_unassignNonexistentAssignment_throwsCommandException() {
        Index personIndex = Index.fromZeroBased(0); // ALICE
        Index taskIndex = Index.fromZeroBased(1); // TASK_B
        UnassignCommand unassignCommand = new UnassignCommand(personIndex, taskIndex);

        assertThrows(CommandException.class, MESSAGE_NON_EXIST_ASSIGNMENT, ()
            -> unassignCommand.execute(model, officeConnectModel));
    }


    @Test
    public void equals() {
        UnassignCommand assignFirstCommand = new UnassignCommand(INDEX_FIRST, INDEX_FIRST);
        UnassignCommand assignSecondCommand = new UnassignCommand(INDEX_SECOND, INDEX_SECOND);

        // same object -> returns true
        assertEquals(assignFirstCommand, assignFirstCommand);

        // same values -> returns true
        UnassignCommand assignFirstCommandCopy = new UnassignCommand(INDEX_FIRST, INDEX_FIRST);
        assertEquals(assignFirstCommand, assignFirstCommandCopy);

        // null -> returns false
        assertNotEquals(null, assignFirstCommand);

        // different personTask -> returns false
        assertNotEquals(assignFirstCommand, assignSecondCommand);
    }

}
