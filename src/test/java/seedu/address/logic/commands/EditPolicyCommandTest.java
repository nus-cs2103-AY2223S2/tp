package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.EditPolicyCommand.MESSAGE_EDIT_POLICY_SUCCESS;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditPolicyDescriptorBuilder;
import seedu.address.testutil.PolicyBuilder;

class EditPolicyCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_allFieldsSpecifiedUnfilteredList_success() {
        Policy editedPolicy = new PolicyBuilder().build();
        EditPolicyCommand.EditPolicyDescriptor descriptor = new EditPolicyDescriptorBuilder(editedPolicy).build();
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY, descriptor);

        Client targetClient = model.getFilteredClientList().get(0);
        String expectedMessage = String.format(MESSAGE_EDIT_POLICY_SUCCESS, editedPolicy.toString()) + " from: "
                + targetClient.getName().toString();
        CommandResult expectedCommandResult = new CommandResult(String.format(expectedMessage, editedPolicy));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        editedPolicy = new PolicyBuilder(editedPolicy).build();


    }
}