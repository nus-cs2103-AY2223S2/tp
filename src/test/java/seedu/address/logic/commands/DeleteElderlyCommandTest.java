package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_NRIC_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;
import static seedu.address.testutil.TypicalElderly.getTypicalElderly;
import static seedu.address.testutil.TypicalVolunteers.getTypicalVolunteers;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.ModelManagerBuilder;

class DeleteElderlyCommandTest {

    @Test
    public void execute_validNric_success() {
        Model model = getTypicalModelManager();
        Elderly elderlyToDelete = getTypicalElderly().get(0);
        DeleteElderlyCommand deleteElderlyCommand =
                new DeleteElderlyCommand(elderlyToDelete.getNric());

        String expectedMessage = String.format(
                DeleteElderlyCommand.MESSAGE_DELETE_ELDERLY_SUCCESS, elderlyToDelete
        );
        Model expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(model.getFriendlyLink())
                .build();
        expectedModel.deleteElderly(elderlyToDelete);
        assertCommandSuccess(deleteElderlyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_pairedElderly_pairDeleted() {
        Model model = getTypicalModelManager();
        Elderly elderly = getTypicalElderly().get(0);
        Volunteer volunteer = getTypicalVolunteers().get(0);
        Pair pair = new Pair(elderly, volunteer);
        model.addPair(elderly.getNric(), volunteer.getNric());
        assertTrue(model.hasPair(pair));

        model.deleteElderly(elderly);
        assertFalse(model.hasPair(pair));
    }

    @Test
    public void execute_invalidNric_throwsCommandException() {
        Model model = getTypicalModelManager();
        Nric invalidNric = new Nric("T9999999I");
        DeleteElderlyCommand deleteElderlyCommand = new DeleteElderlyCommand(invalidNric);

        assertCommandFailure(deleteElderlyCommand, model, MESSAGE_NRIC_NOT_EXIST);
    }

    @Test
    public void equals() {
        Nric firstNric = new Nric("S1234567I");
        Nric secondNric = new Nric("S7654321I");
        DeleteElderlyCommand deleteFirstCommand = new DeleteElderlyCommand(firstNric);
        DeleteElderlyCommand deleteSecondCommand = new DeleteElderlyCommand(secondNric);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteElderlyCommand deleteFirstCommandCopy = new DeleteElderlyCommand(firstNric);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredElderlyList(p -> false);

        assertTrue(model.getFilteredElderlyList().isEmpty());
    }
}
