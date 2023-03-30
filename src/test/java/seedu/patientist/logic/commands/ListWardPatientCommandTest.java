package seedu.patientist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalPatients.CHARLIE;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.patient.PatientInWardPredicate;

public class ListWardPatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void equals() {
        ListWardPatientsCommand findFirstCommand = new ListWardPatientsCommand("first");
        ListWardPatientsCommand findSecondCommand = new ListWardPatientsCommand("second");

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ListWardPatientsCommand findFirstCommandCopy = new ListWardPatientsCommand("first");
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different ward -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_failure() {
        ListWardPatientsCommand command = new ListWardPatientsCommand(" ");
        String expectedMessage = String.format(String.format(ListWardPatientsCommand.MESSAGE_WARD_NOT_FOUND, " "), 0);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_onlyPatients_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PatientInWardPredicate predicate = new PatientInWardPredicate(model, "Block A Ward 1");
        ListWardPatientsCommand command = new ListWardPatientsCommand("Block A Ward 1");
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMY, CHARLIE), model.getFilteredPersonList());
    }
}
