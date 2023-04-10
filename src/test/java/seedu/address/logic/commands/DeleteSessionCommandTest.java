package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.session.Session;
import seedu.address.testutil.SessionBuilder;

public class DeleteSessionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final Index validIndex = Index.fromOneBased(1);

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        model.addSession(SessionBuilder.SESSION_ONE);
        Session sessionToDelete = model.getFilteredSessionList().get(validIndex.getZeroBased());
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(validIndex);

        String expectedMessage = String.format(DeleteSessionCommand.MESSAGE_SUCCESS, sessionToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeSession(sessionToDelete);
        expectedModel.commitAddressBook();
        CommandTestUtil.assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSessionList().size() + 1);
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteSessionCommand, model,
                Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSessionCommand deleteFirstCommand = new DeleteSessionCommand(Index.fromOneBased(1));
        DeleteSessionCommand deleteSecondCommand = new DeleteSessionCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteSessionCommand deleteFirstCommandCopy = new DeleteSessionCommand(Index.fromOneBased(1));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different session -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}


