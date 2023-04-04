package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InternshipApplication;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternshipApplication internshipToDelete = model.getSortedFilteredInternshipList()
                .get(Index.fromOneBased(3).getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(3));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_APPLICATION_SUCCESS, internshipToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.deleteInternship(internshipToDelete);

        try {
            CommandResult deleteMessage = deleteCommand.execute(model);
            assertCommandSuccess(deleteMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredInternshipList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, Index.fromOneBased(1));

        InternshipApplication internshipToDelete = model.getSortedFilteredInternshipList()
                .get(Index.fromOneBased(1).getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_APPLICATION_SUCCESS, internshipToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(),
                getTypicalNoteList());
        expectedModel.deleteInternship(internshipToDelete);

        try {
            CommandResult deleteMessage = deleteCommand.execute(model);
            assertCommandSuccess(deleteMessage, model, expectedMessage, expectedModel);

        } catch (CommandException ce) {
            fail(ce.toString());
        }
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, Index.fromOneBased(3));

        Index outOfBoundIndex = Index.fromOneBased(3);
        // ensures that outOfBoundIndex is still in bounds of internships list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getInternshipList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(Index.fromOneBased(3));
        DeleteCommand deleteSecondCommand = new DeleteCommand(Index.fromOneBased(5));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(Index.fromOneBased(3));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
