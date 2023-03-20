package seedu.patientist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalStaff.AMY;
import static seedu.patientist.testutil.TypicalStaff.BOB;
import static seedu.patientist.testutil.TypicalStaff.CHARLES;
import static seedu.patientist.testutil.TypicalStaff.DACIA;
import static seedu.patientist.testutil.TypicalStaff.getTypicalPatientist;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.staff.IsStaffPredicate;

public class ListStaffCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void equals() {
        ListStaffCommand staffCommand1 = new ListStaffCommand();
        ListStaffCommand staffCommand2 = new ListStaffCommand();
        ListPatientsCommand patientsCommand = new ListPatientsCommand();

        // same object -> returns true
        assertTrue(staffCommand1.equals(staffCommand1));

        // different object -> returns true
        assertTrue(staffCommand1.equals(staffCommand2));

        // different class -> returns false
        assertFalse(staffCommand1.equals(patientsCommand));
    }

    @Test
    public void execute_onlyPatients_found() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        IsStaffPredicate predicate = new IsStaffPredicate();
        ListStaffCommand command = new ListStaffCommand();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AMY, BOB, CHARLES, DACIA), model.getFilteredPersonList());
    }
}
