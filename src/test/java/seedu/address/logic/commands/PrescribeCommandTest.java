package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Cost;
import seedu.address.model.prescription.Medication;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.PersonBuilder;

public class PrescribeCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PrescribeCommand(new Nric(VALID_NRIC_AMY), null));
        assertThrows(NullPointerException.class, () -> new PrescribeCommand(null, new Prescription(
                new Medication(VALID_MEDICATION_AMY), new Cost(VALID_COST_AMY))));
    }

    @Test
    public void execute_addPrescription_success() {
        /*
        Prescription Parameter: [BOBMEDICATION, BOBCOST]
        Original Prescriptions: [(AMYMEDICATION, AMYCOST)]
        Expected Prescriptions: [(AMYMEDICATION, AMYCOST), (BOBMEDICATION, BOBCOST)]
         */
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(targetPerson)
                .withPrescriptions(VALID_MEDICATION_AMY, VALID_COST_AMY, VALID_MEDICATION_BOB, VALID_COST_BOB).build();

        PrescribeCommand prescribeCommand = new PrescribeCommand(new Nric(VALID_NRIC_AMY),
                new Prescription(new Medication(VALID_MEDICATION_BOB), new Cost(VALID_COST_BOB)));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(targetPerson, editedPerson);

        String expectedMessage = String.format(PrescribeCommand.MESSAGE_ADD_SUCCESS, editedPerson);

        assertCommandSuccess(prescribeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editPrescription_success() {
        /*
        Prescription Parameter: [AMYMEDICATION, BOBCOST]
        Original Prescriptions: [(AMYMEDICATION, AMYCOST)]
        Expected Prescriptions: [(AMYMEDICATION, BOBCOST)]
         */
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(targetPerson)
                .withPrescriptions(VALID_MEDICATION_AMY, VALID_COST_BOB).build();

        PrescribeCommand prescribeCommand = new PrescribeCommand(new Nric(VALID_NRIC_AMY),
                new Prescription(new Medication(VALID_MEDICATION_AMY), new Cost(VALID_COST_BOB)));

        String expectedMessage = String.format(PrescribeCommand.MESSAGE_EDIT_SUCCESS, editedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(prescribeCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_repeatPrescription_failure() {
        /*
        Prescription Parameter: [AMYMEDICATION, AMYCOST]
        Original Prescriptions: [(AMYMEDICATION, AMYCOST)]
        Expected Behaviour: Error
         */
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        PrescribeCommand prescribeCommand = new PrescribeCommand(new Nric(VALID_NRIC_AMY),
                new Prescription(new Medication(VALID_MEDICATION_AMY), new Cost(VALID_COST_AMY)));

        String expectedMessage = PrescribeCommand.MESSAGE_IDENTICAL_PRESCRIPTION;

        assertCommandFailure(prescribeCommand, model, expectedMessage);
    }

    @Test
    public void execute_noValidPatient_failure() {
        /*
        Prescription Parameter: [AMYMEDICATION, AMYCOST]
        Original Prescriptions: [(AMYMEDICATION, AMYCOST)]
        Expected Behaviour: Error
         */
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        String nooneNric = "T0000000T";

        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PrescribeCommand prescribeCommand = new PrescribeCommand(new Nric(nooneNric),
                new Prescription(new Medication(VALID_MEDICATION_AMY), new Cost(VALID_COST_AMY)));

        String expectedMessage = PrescribeCommand.MESSAGE_INVALID_PERSON;

        assertCommandFailure(prescribeCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Nric amyNric = new Nric(VALID_NRIC_AMY);
        Prescription amyPrescription = new Prescription(
                new Medication(VALID_MEDICATION_AMY), new Cost(VALID_COST_AMY));

        PrescribeCommand prescribeAmy = new PrescribeCommand(amyNric, amyPrescription);

        // same object -> returns true
        assertTrue(prescribeAmy.equals(prescribeAmy));

        // same values -> return true
        assertTrue(prescribeAmy.equals(new PrescribeCommand(amyNric, amyPrescription)));

        // different types -> returns false
        assertFalse(prescribeAmy.equals(1));

        // null -> returns false
        assertFalse(prescribeAmy.equals(null));

        Prescription bobPrescription = new Prescription(new Medication(VALID_MEDICATION_BOB),
                new Cost(VALID_COST_BOB));

        // different values -> returns false
        assertFalse(prescribeAmy.equals(new PrescribeCommand(new Nric(VALID_NRIC_BOB), amyPrescription)));
        assertFalse(prescribeAmy.equals(new PrescribeCommand(amyNric, bobPrescription)));
    }
}
