package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalPolicies.HEALTH;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.UniquePolicyList;
import seedu.address.testutil.PolicyBuilder;

class AddPolicyCommandTest {

    private static final Policy POLICY_STUB = new PolicyBuilder().build();
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addPolicyUnfilteredList_success() {
        Client clientToAddPolicy = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        AddPolicyCommand addPolicyCommand = new AddPolicyCommand(INDEX_FIRST_CLIENT, POLICY_STUB);
        clientToAddPolicy.getPolicyList().add(POLICY_STUB);
        model.setClient(clientToAddPolicy, clientToAddPolicy);
        UniquePolicyList clientPolicyList = clientToAddPolicy.getPolicyList();
        assertFalse(clientPolicyList.isEmpty());
    }
    @Test
    public void equals() {
        final AddPolicyCommand standardCommand = new AddPolicyCommand(INDEX_FIRST_CLIENT, POLICY_STUB);

        // same values -> returns true
        AddPolicyCommand commandWithSameValues = new AddPolicyCommand(INDEX_FIRST_CLIENT, POLICY_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddPolicyCommand(INDEX_SECOND_CLIENT, POLICY_STUB)));

        // different policy name -> returns false
        assertFalse(standardCommand.equals(new AddPolicyCommand(INDEX_FIRST_CLIENT, HEALTH)));
    }
}
