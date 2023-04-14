package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nricDoesNotExist_throwsCommandException() {
        ViewCommand viewCommand = new ViewCommand(new Nric("T0000000A"));
        assertCommandFailure(viewCommand, model, Messages.MESSAGE_NRIC_DOES_NOT_EXIST);
    }

    @Test
    public void execute_nricExist_success() {
        ViewCommand viewCommand = new ViewCommand(new Nric("S1234567A"));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(new NricContainsKeywordsPredicate(Arrays.asList("S1234567A")));
        assertCommandSuccess(viewCommand, model, ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(new Nric("T0654321Z"));
        ViewCommand viewSecondCommand = new ViewCommand(new Nric("T0123456A"));

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(new Nric("T0654321Z"));
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
