package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Medication;
import seedu.address.testutil.PersonBuilder;

class UnprescribeCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnprescribeCommand(
                new Nric(VALID_NRIC_AMY), null));
        assertThrows(NullPointerException.class, () -> new UnprescribeCommand(
                null, new Medication(VALID_MEDICATION_AMY)));
    }

    @Test
    public void execute_patientExistsAndPrescribedWithMedication_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(targetPerson)
                .withPrescriptions().build();

        UnprescribeCommand unprescribeCommand = new UnprescribeCommand(new Nric(VALID_NRIC_AMY),
                new Medication(VALID_MEDICATION_AMY));

        String expectedMessage = String.format(UnprescribeCommand.MESSAGE_DELETE_SUCCESS, editedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(targetPerson, editedPerson);

        assertCommandSuccess(unprescribeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_patientExistsButNotPrescribed_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        UnprescribeCommand unprescribeCommand = new UnprescribeCommand(new Nric(VALID_NRIC_AMY),
                new Medication(VALID_MEDICATION_BOB));

        String expectedMessage = UnprescribeCommand.MESSAGE_INVALID_MEDICATION;

        assertCommandFailure(unprescribeCommand, model, expectedMessage);
    }

    @Test
    public void execute_patientDoesNotExist_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        String nooneNric = "T0000000T";

        UnprescribeCommand unprescribeCommand = new UnprescribeCommand(new Nric(nooneNric),
                new Medication(VALID_MEDICATION_AMY));

        String expectedMessage = UnprescribeCommand.MESSAGE_INVALID_PERSON;

        assertCommandFailure(unprescribeCommand, model, expectedMessage);
    }
}
