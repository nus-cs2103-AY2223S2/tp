package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nricDoesNotExist_throwsCommandException() {
        Set<Nric> nricList = new HashSet<>();
        Nric nric = new Nric("T0000000A");
        nricList.add(nric);
        DeleteCommand deleteCommand = new DeleteCommand(nricList);
        assertCommandFailure(deleteCommand, model, String.format(Messages.MESSAGE_NRIC_DOES_NOT_EXIST, "T0000000A"));
    }

    @Test
    public void execute_nricExist_success() {
        Set<Nric> nricList = new HashSet<>();
        Nric nric = new Nric("S1234567A");
        nricList.add(nric);
        DeleteCommand deleteCommand = new DeleteCommand(nricList);
        String expectedMessage = DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS + "S1234567A\n";
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(ALICE);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Set<Nric> nricListOne = new HashSet<>();
        nricListOne.add(new Nric("T0123456A"));

        Set<Nric> nricListTwo = new HashSet<>();
        nricListTwo.add(new Nric("T0654321Z"));
        DeleteCommand deleteFirstCommand = new DeleteCommand(nricListOne);
        DeleteCommand deleteSecondCommand = new DeleteCommand(nricListTwo);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        Set<Nric> nricListOneCopy = new HashSet<>();
        nricListOneCopy.add(new Nric("T0123456A"));
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(nricListOneCopy);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
