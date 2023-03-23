package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.model.util.TypicalPersons.ALICE;
import static seedu.address.model.util.TypicalPersons.BENSON;
import static seedu.address.model.util.TypicalPersons.CARL;
import static seedu.address.model.util.TypicalPersons.getTypicalAddressBook;
import static seedu.address.model.util.TypicalTasks.COMPLETE_SLIDES;
import static seedu.address.model.util.TypicalTasks.SEND_EMAIL_TO_CLIENT;
import static seedu.address.model.util.TypicalTasks.STOCK_PANTRY;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Contains integration and unit tests for {@code AssignCommand}.
 */
public class AssignCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(getPersonTaskRepository()));

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        //null person index
        assertThrows(NullPointerException.class, () -> new AssignCommand(INDEX_FIRST, null));

        //null task index
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, INDEX_SECOND));

        //all null args
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, null));
    }

    /**
     * Test for valid indexes
     */
    @Test
    public void execute_validIndex_throwsCommandException() {
        try {
            Index personIndex = INDEX_FOURTH;
            Index taskIndex = INDEX_FOURTH;
            Person person = model.getFilteredPersonList().get(personIndex.getZeroBased());
            Task task = officeConnectModel.getTaskModelManager().getFilteredItemList().get(taskIndex.getZeroBased());

            AssignCommand command = new AssignCommand(personIndex, taskIndex);
            CommandResult result = command.execute(model, officeConnectModel);

            Repository<AssignTask> repo = getPersonTaskRepository();
            repo.addItem(new AssignTask(person.getId(), task.getId()));

            assertEquals(String.format(AssignCommand.MESSAGE_SUCCESS, person, task), result.getFeedbackToUser());
            assertEquals(repo, officeConnectModel.getAssignTaskModelManager().getReadOnlyRepository());
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    /**
     * Test for duplicate AssignTask
     */
    @Test
    public void execute_duplicatePersonTask_throwsCommandException() {
        AssignCommand command = new AssignCommand(INDEX_FIRST, INDEX_FIRST);

        assertThrows(CommandException.class, AssignCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () ->
                command.execute(model, officeConnectModel));
    }

    /**
     * Test if an invalid person index throws a CommandException and that the
     * AssignTask repo does not change.
     */
    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AssignCommand command = new AssignCommand(outOfBounds, INDEX_FIRST);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                command.execute(model, officeConnectModel));
        assertEquals(getPersonTaskRepository(),
                officeConnectModel.getAssignTaskModelManager().getReadOnlyRepository());
    }

    /**
     * Test if an invalid task index throws a CommandException and that the
     * AssignTask repo does not change.
     */
    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(officeConnectModel.getTaskModelManager()
                .getFilteredItemList().size() + 1);
        AssignCommand command = new AssignCommand(INDEX_FIRST, outOfBounds);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, () ->
                command.execute(model, officeConnectModel));
        assertEquals(getPersonTaskRepository(),
                officeConnectModel.getAssignTaskModelManager().getReadOnlyRepository());
    }

    @Test
    public void equals() {
        AssignCommand assignFirstCommand = new AssignCommand(INDEX_FIRST, INDEX_FIRST);
        AssignCommand assignSecondCommand = new AssignCommand(INDEX_SECOND, INDEX_SECOND);

        // same object -> returns true
        assertEquals(assignFirstCommand, assignFirstCommand);

        // same values -> returns true
        AssignCommand assignFirstCommandCopy = new AssignCommand(INDEX_FIRST, INDEX_FIRST);
        assertEquals(assignFirstCommand, assignFirstCommandCopy);

        // null -> returns false
        assertNotEquals(null, assignFirstCommand);

        // different personTask -> returns false
        assertNotEquals(assignFirstCommand, assignSecondCommand);
    }

    /**
     * Returns a {@code Repository} with a few AssignTask mappings for the TypicalTaskRepository and
     * TypicalAddressBook used in this class.
     */
    private Repository<AssignTask> getPersonTaskRepository() {
        AssignTask mapping1 = new AssignTask(ALICE.getId(), SEND_EMAIL_TO_CLIENT.getId());
        AssignTask mapping2 = new AssignTask(BENSON.getId(), COMPLETE_SLIDES.getId());
        AssignTask mapping3 = new AssignTask(CARL.getId(), STOCK_PANTRY.getId());

        Repository<AssignTask> ptl = new Repository<>();
        ptl.addItem(mapping1);
        ptl.addItem(mapping2);
        ptl.addItem(mapping3);

        return ptl;
    }
}
