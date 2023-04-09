package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POLICY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;

class DeletePolicyCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Client client = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Policy policyToDelete = client.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY);

        String expectedMessage = String.format(
                DeletePolicyCommand.MESSAGE_DELETE_POLICY_SUCCESS, policyToDelete.toString()
        ) + " from Client: " + client.getName().toString();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        //Client editedClient = expectedModel.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        //Policy dummyPolicy = editedClient.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());
        deletePolicyCommand.execute(expectedModel);
        // the model contains the same client that contains same policy list so naturally deletes the policy

        assertCommandSuccess(deletePolicyCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidClientIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(outOfBoundIndex, INDEX_FIRST_POLICY);

        assertCommandFailure(deletePolicyCommand, model, MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPolicyIndexUnfilteredList_throwsCommandException() {
        Client client = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(client.getFilteredPolicyList().size() + 1);
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, outOfBoundIndex);

        assertCommandFailure(deletePolicyCommand, model, MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        Client client = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Policy policyToDelete = client.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY);

        String expectedMessage = String.format(
                DeletePolicyCommand.MESSAGE_DELETE_POLICY_SUCCESS, policyToDelete.toString()
        ) + " from Client: " + client.getName().toString();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredClientList(p -> p.equals(client));
        deletePolicyCommand.execute(expectedModel);

        assertCommandSuccess(deletePolicyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClientIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getClientList().size());

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(outOfBoundIndex, INDEX_FIRST_POLICY);

        assertCommandFailure(deletePolicyCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /*
    Intentions on making test cases for filtered policy list, but am not sure how filtered list works in
    client at the moment.
     */

    @Test
    void testEquals() {
        DeletePolicyCommand deleteFirstPolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY);
        DeletePolicyCommand deleteSecondPolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT,
                INDEX_SECOND_POLICY);

        assertEquals(deleteFirstPolicyCommand, deleteFirstPolicyCommand);

        DeletePolicyCommand deleteFirstPolicyCommandCopy = new DeletePolicyCommand(INDEX_FIRST_CLIENT,
                INDEX_FIRST_POLICY);
        assertEquals(deleteFirstPolicyCommand, deleteFirstPolicyCommandCopy);


        assertNotEquals(1, deleteFirstPolicyCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstPolicyCommand);

        // different client -> returns false
        assertNotEquals(deleteFirstPolicyCommand, deleteSecondPolicyCommand);


        // can add more test cases that checks with different client index.
    }

}
