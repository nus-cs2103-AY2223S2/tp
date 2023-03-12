package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddPolicyCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class AddPolicyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final String policyName = "Health insurance";

        assertCommandFailure(new AddPolicyCommand(INDEX_FIRST_CLIENT, policyName), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_CLIENT.getOneBased(), policyName));
    }
    @Test
    public void equals() {
        final AddPolicyCommand standardCommand = new AddPolicyCommand(INDEX_FIRST_CLIENT, VALID_POLICY_NAME_AMY);

        // same values -> returns true
        AddPolicyCommand commandWithSameValues = new AddPolicyCommand(INDEX_FIRST_CLIENT, VALID_POLICY_NAME_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddPolicyCommand(INDEX_SECOND_CLIENT, VALID_POLICY_NAME_AMY)));

        // different policy name -> returns false
        assertFalse(standardCommand.equals(new AddPolicyCommand(INDEX_FIRST_CLIENT, VALID_POLICY_NAME_BOB)));
    }
}
