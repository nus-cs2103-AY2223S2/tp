package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_POLICY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_POLICY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.logic.commands.EditPolicyCommand.MESSAGE_EDIT_POLICY_SUCCESS;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_POLICY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;
import seedu.address.testutil.EditPolicyDescriptorBuilder;
import seedu.address.testutil.PolicyBuilder;

class EditPolicyCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_allFieldsSpecifiedUnfilteredList_success() {
        Policy editedPolicy = new PolicyBuilder().withPolicyName("Travel Insurance").build();
        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder(editedPolicy).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY, descriptor);

        Client targetClient = model.getFilteredClientList().get(0);
        Policy targetedPolicy = targetClient.getFilteredPolicyList().get(0);
        String expectedMessage = String.format(MESSAGE_EDIT_POLICY_SUCCESS, editedPolicy.toString()) + " from Client: "
                + targetClient.getName().toString();
        CommandResult expectedCommandResult = new CommandResult(String.format(expectedMessage, editedPolicy));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        editedPolicy = new PolicyBuilder(editedPolicy).build();
        Client editedClient = expectedModel.getFilteredClientList().get(0);
        editedClient.getPolicyList().setPolicy(targetedPolicy, editedPolicy);

        assertCommandSuccess(editPolicyCommand, model, expectedCommandResult, expectedModel);
    }

    /*
    @Test
    void execute_someFieldsSpecifiedUnfilteredList_success() {
        Client firstClient = model.getFilteredClientList().get(0);
        Index indexLastPolicy = Index.fromOneBased(firstClient.getFilteredPolicyList().size());
        Policy lastPolicy = firstClient.getFilteredPolicyList().get(indexLastPolicy.getZeroBased());
        Policy editedPolicy = new PolicyBuilder(lastPolicy).withPolicyName("Travel Insurance")
                .withPremium("5000").build();

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder(editedPolicy).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, indexLastPolicy, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_POLICY_SUCCESS, editedPolicy.toString()) + " from Client: "
                + firstClient.getName().toString();
        CommandResult expectedCommandResult = new CommandResult(String.format(expectedMessage, editedPolicy));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        editedPolicy = new PolicyBuilder(editedPolicy).build();
        Client editedClient = expectedModel.getFilteredClientList().get(0);
        editedClient.getPolicyList().setPolicy(lastPolicy, editedPolicy);

        assertCommandSuccess(editPolicyCommand, model, expectedCommandResult, expectedModel);
    }
    */

    @Test
    void execute_noFieldSpecifiedUnfilteredList_success() {
        Client firstClient = model.getFilteredClientList().get(0);
        Policy firstPolicy = firstClient.getFilteredPolicyList().get(0);

        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY,
                new EditPolicyCommand.EditPolicyDescriptor());

        String expectedMessage = String.format(MESSAGE_EDIT_POLICY_SUCCESS, firstPolicy.toString()) + " from Client: "
                + firstClient.getName().toString();
        CommandResult expectedCommandResult = new CommandResult(String.format(expectedMessage, firstPolicy));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editPolicyCommand, model, expectedCommandResult, expectedModel);
    }


    /*
    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Client editedPolicyClient = clientInFilteredList.cloneClient();
        Policy targetPolicy = editedPolicyClient.getFilteredPolicyList().get(0);

        Policy editedPolicy = new PolicyBuilder(targetPolicy).withPremium("5000").build();
        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder(editedPolicy).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showClientAtIndex(expectedModel, INDEX_FIRST_CLIENT);
        editedPolicy = new PolicyBuilder(editedPolicy).build();

        String expectedMessage = String.format(MESSAGE_EDIT_POLICY_SUCCESS, editedPolicy.toString()) + " from Client: "
                + clientInFilteredList.getName().toString();
        CommandResult expectedCommandResult = new CommandResult(String.format(expectedMessage, editedPolicy));
        //Client editedClient = expectedModel.getFilteredClientList().get(0);
        editedPolicyClient.getPolicyList().setPolicy(targetPolicy, editedPolicy);
        expectedModel.setClient(clientInFilteredList, editedPolicyClient);

        assertCommandSuccess(editPolicyCommand, model, expectedCommandResult, expectedModel);
    }
    */

    @Test
    public void execute_duplicatePolicyUnfilteredList_failure() {
        Client targetClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Policy targetPolicy = targetClient.getFilteredPolicyList().get(INDEX_FIRST_CLIENT.getZeroBased());
        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder(targetPolicy).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT,
                INDEX_SECOND_CLIENT, descriptor);

        assertCommandFailure(editPolicyCommand, model, EditPolicyCommand.MESSAGE_DUPLICATE_POLICY);
    }

    @Test
    public void execute_duplicatePolicyFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client targetClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Policy targetPolicy = targetClient.getFilteredPolicyList().get(INDEX_FIRST_CLIENT.getZeroBased());

        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder(targetPolicy).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT,
                INDEX_SECOND_CLIENT, descriptor);

        assertCommandFailure(editPolicyCommand, model, EditPolicyCommand.MESSAGE_DUPLICATE_POLICY);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_AMY).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(outOfBoundIndex, INDEX_FIRST_POLICY, descriptor);

        assertCommandFailure(editPolicyCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder()
                .withPolicyName(VALID_POLICY_NAME_AMY).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(outOfBoundIndex, INDEX_FIRST_POLICY, descriptor);

        assertCommandFailure(editPolicyCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY,
                DESC_POLICY_AMY);

        EditPolicyCommand.EditPolicyDescriptor copyDescriptor = new EditPolicyCommand
                .EditPolicyDescriptor(DESC_POLICY_AMY);
        EditPolicyCommand commandWithSameValues = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY,
                copyDescriptor);

        //assertEquals(editPolicyCommand, commandWithSameValues);
        assertEquals(editPolicyCommand, editPolicyCommand);
        assertNotEquals(null, editPolicyCommand);
        assertNotEquals(editPolicyCommand, new ClearCommand());
        assertNotEquals(editPolicyCommand, new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_SECOND_POLICY,
                DESC_POLICY_AMY));
        assertNotEquals(editPolicyCommand, new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY,
                DESC_POLICY_BOB));
    }

}
