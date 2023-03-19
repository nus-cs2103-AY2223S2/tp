package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.TypicalVolunteers;

public class DeleteVolunteerCommandTest {
    private final Model model = getTypicalModelManager();

    @Test
    public void execute_validNric_success() {
        Volunteer volunteerToDelete = TypicalVolunteers.getTypicalVolunteers().get(0);
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(volunteerToDelete.getNric());

        String expectedMessage = String.format(
                DeleteVolunteerCommand.MESSAGE_DELETE_VOLUNTEER_SUCCESS, volunteerToDelete
        );

        Model expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(model.getFriendlyLink())
                .build();
        expectedModel.deleteVolunteer(volunteerToDelete);

        assertCommandSuccess(deleteVolunteerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNric_throwsCommandException() {
        Nric invalidNric = new Nric("T9999999I");
        DeleteVolunteerCommand deleteVolunteerCommand = new DeleteVolunteerCommand(invalidNric);

        assertCommandFailure(deleteVolunteerCommand, model, Messages.MESSAGE_NRIC_NOT_EXIST);
    }
    @Test
    public void equals() {
        Nric firstNric = new Nric("S1234567I");
        Nric secondNric = new Nric("S7654321I");
        DeleteVolunteerCommand deleteFirstCommand = new DeleteVolunteerCommand(firstNric);
        DeleteVolunteerCommand deleteSecondCommand = new DeleteVolunteerCommand(secondNric);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteVolunteerCommand deleteFirstCommandCopy = new DeleteVolunteerCommand(firstNric);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered volunteer list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredVolunteerList(p -> false);

        assertTrue(model.getFilteredVolunteerList().isEmpty());
    }
}
