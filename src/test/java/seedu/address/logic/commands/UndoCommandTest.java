package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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

class UndoCommandTest {

    private String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidUndo_throwsCommandException() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertTrue(!expectedModel.canUndo());
        assertTrue(!model.canUndo());
    }


    @Test
    public void execute_addUndo_success() {
        Client clientToAdd = new ClientBuilder().build();

        ModelManager expectedModelBeforeUndo = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager dummyModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelBeforeUndo.addClient(clientToAdd);
        dummyModel.addClient(clientToAdd);

        assertTrue(expectedModelBeforeUndo.canUndo());
        assertTrue(dummyModel.canUndo());

        dummyModel.undo();
        ModelManager expectedModelAfterUndo = new ModelManager(dummyModel.getAddressBook(), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, expectedModelBeforeUndo, expectedMessage, expectedModelAfterUndo);
    }

    @Test
    public void execute_deleteUndo_success() {
        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());

        ModelManager expectedModelBeforeUndo = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager dummyModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelBeforeUndo.deleteClient(clientToDelete);
        dummyModel.deleteClient(clientToDelete);

        assertTrue(expectedModelBeforeUndo.canUndo());
        assertTrue(dummyModel.canUndo());

        dummyModel.undo();
        ModelManager expectedModelAfterUndo = new ModelManager(dummyModel.getAddressBook(), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, expectedModelBeforeUndo, expectedMessage, expectedModelAfterUndo);
    }

    @Test
    public void execute_editUndo_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());
        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        ModelManager expectedModelBeforeUndo = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager dummyModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModelBeforeUndo.setClient(lastClient, editedClient);
        dummyModel.setClient(lastClient, editedClient);

        assertTrue(expectedModelBeforeUndo.canUndo());
        assertTrue(dummyModel.canUndo());

        dummyModel.undo();
        ModelManager expectedModelAfterUndo = new ModelManager(dummyModel.getAddressBook(), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, expectedModelBeforeUndo, expectedMessage, expectedModelAfterUndo);
    }

}
