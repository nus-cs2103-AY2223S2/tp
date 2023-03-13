package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

class SelectCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToSelect = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        SelectCommand selectCommand = new SelectCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = SelectCommand.MESSAGE_SELECT_CLIENT_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, clientToSelect, true, false, false);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(selectCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_CLIENT);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertEquals(selectFirstCommand, selectFirstCommand);

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_CLIENT);
        assertEquals(selectFirstCommand, selectFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, selectFirstCommand);

        // null -> returns false
        assertNotEquals(null, selectFirstCommand);

        // different client -> returns false
        assertNotEquals(selectFirstCommand, selectSecondCommand);

    }
}
