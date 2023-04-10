package seedu.patientist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalPatients.ADAM;
import static seedu.patientist.testutil.TypicalPatients.AMY;
import static seedu.patientist.testutil.TypicalPatients.BOB;
import static seedu.patientist.testutil.TypicalPatients.CHARLIE;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.patient.IsPatientPredicate;

public class ListPatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void equals() {
        ListPatientsCommand findFirstCommand = new ListPatientsCommand();
        ListPatientsCommand findSecondCommand = new ListPatientsCommand();

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // different object -> returns true
        assertTrue(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_onlyPatients_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        IsPatientPredicate predicate = new IsPatientPredicate();
        ListPatientsCommand command = new ListPatientsCommand();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMY, CHARLIE, ADAM, BOB), model.getFilteredPersonList());
    }

}
