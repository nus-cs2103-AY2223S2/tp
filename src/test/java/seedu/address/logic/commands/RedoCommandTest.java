package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;


class RedoCommandTest {

    private final String expectedMessage = RedoCommand.MESSAGE_REDO_SUCCESS;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidRedo_throwsCommandException() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertFalse(expectedModel.canRedo());
        assertFalse(model.canRedo());
    }

    @Test
    public void execute_addRedo() {
        Client clientToAdd = new ClientBuilder().build();

        ModelManager expectedModelBeforeRedo = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager dummyModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelBeforeRedo.addClient(clientToAdd);
        dummyModel.addClient(clientToAdd);

        assertTrue(expectedModelBeforeRedo.canUndo());
        assertTrue(dummyModel.canUndo());
        assertFalse(expectedModelBeforeRedo.canRedo());
        assertFalse(dummyModel.canRedo());

        expectedModelBeforeRedo.undo();
        dummyModel.undo();

        assertTrue(expectedModelBeforeRedo.canRedo());
        assertTrue(dummyModel.canRedo());

        dummyModel.redo();
        ModelManager expectedModelAfterRedo = new ModelManager(dummyModel.getAddressBook(), new UserPrefs());
        RedoCommand redoCommand = new RedoCommand();
        assertCommandSuccess(redoCommand, expectedModelBeforeRedo, expectedMessage, expectedModelAfterRedo);
    }

    @Test
    public void execute_deleteRedo_success() {
        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());

        ModelManager expectedModelBeforeRedo = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager dummyModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelBeforeRedo.deleteClient(clientToDelete);
        dummyModel.deleteClient(clientToDelete);

        assertTrue(expectedModelBeforeRedo.canUndo());
        assertTrue(dummyModel.canUndo());
        assertFalse(expectedModelBeforeRedo.canRedo());
        assertFalse(dummyModel.canRedo());

        expectedModelBeforeRedo.undo();
        dummyModel.undo();

        assertTrue(expectedModelBeforeRedo.canRedo());
        assertTrue(dummyModel.canRedo());

        dummyModel.redo();
        ModelManager expectedModelAfterRedo = new ModelManager(dummyModel.getAddressBook(), new UserPrefs());
        RedoCommand redoCommand = new RedoCommand();
        assertCommandSuccess(redoCommand, expectedModelBeforeRedo, expectedMessage, expectedModelAfterRedo);
    }

    @Test
    public void execute_editRedo_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());
        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        ModelManager expectedModelBeforeRedo = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager dummyModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelBeforeRedo.setClient(lastClient, editedClient);
        dummyModel.setClient(lastClient, editedClient);

        assertTrue(expectedModelBeforeRedo.canUndo());
        assertTrue(dummyModel.canUndo());
        assertFalse(expectedModelBeforeRedo.canRedo());
        assertFalse(dummyModel.canRedo());

        expectedModelBeforeRedo.undo();
        dummyModel.undo();

        assertTrue(expectedModelBeforeRedo.canRedo());
        assertTrue(dummyModel.canRedo());

        dummyModel.redo();
        ModelManager expectedModelAfterRedo = new ModelManager(dummyModel.getAddressBook(), new UserPrefs());
        RedoCommand redoCommand = new RedoCommand();
        assertCommandSuccess(redoCommand, expectedModelBeforeRedo, expectedMessage, expectedModelAfterRedo);
    }

}
