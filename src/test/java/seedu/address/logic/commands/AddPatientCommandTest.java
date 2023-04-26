package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.PatientBuilder;



public class AddPatientCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPatientCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        AddPatientCommandTest.ModelStubAcceptingPatientAdded modelStub = new AddPatientCommandTest
                .ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddPatientCommand(validPatient).execute(modelStub);

        assertEquals(String.format(AddPatientCommand.getMessageSuccess(), validPatient),
                commandResult.getCliFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddPatientCommand addPatientCommand = new AddPatientCommand(validPatient);
        ModelStub modelStub = new AddPatientCommandTest.ModelStubWithPatient(validPatient);

        assertThrows(CommandException.class, AddPatientCommand.getMessageDuplicatePatient(), () ->
                addPatientCommand.execute(modelStub));
    }


    @Test
    public void execute_getCommandUsage_success() {
        String messageUsage = AddPatientCommand.COMMAND_WORD + " (short form: "
                + AddPatientCommand.SHORTHAND_COMMAND_WORD + ")"
                + ": Adds a patient to the address book.\n"
                + "Parameters: "
                + PREFIX_NAME + "NAME "
                + PREFIX_PHONE + "PHONE "
                + PREFIX_EMAIL + "EMAIL "
                + PREFIX_HEIGHT + "HEIGHT "
                + PREFIX_WEIGHT + "WEIGHT "
                + PREFIX_DIAGNOSIS + "DIAGNOSIS "
                + PREFIX_STATUS + "STATUS "
                + "[" + PREFIX_REMARK + "REMARK] "
                + "[" + PREFIX_TAG + "TAG]...\n"
                + "Example: " + AddPatientCommand.COMMAND_WORD + " "
                + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "jdoe@gmail.com "
                + PREFIX_HEIGHT + "1.85 "
                + PREFIX_WEIGHT + "70.5 "
                + PREFIX_DIAGNOSIS + "Fever "
                + PREFIX_STATUS + "Outpatient "
                + PREFIX_REMARK + "No known allergies "
                + PREFIX_TAG + "pendingReview";
        assertEquals(AddPatientCommand.getCommandUsage(), messageUsage);
    }

    @Test
    public void execute_addPatient_getMessageSuccessSuccessful() {
        Patient validPatient = new PatientBuilder().build();
        String messageSuccess = "New patient added: %1$s";
        assertEquals(String.format(AddPatientCommand.getMessageSuccess(), validPatient), String.format(messageSuccess,
                validPatient));
    }

    @Test
    public void execute_addPatient_getMessageDuplicatePatientSuccessful() {
        String messageDuplicatePatient = "This patient already exists in the address book";
        assertEquals(AddPatientCommand.getMessageDuplicatePatient(), messageDuplicatePatient);
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddPatientCommand addAliceCommand = new AddPatientCommand(alice);
        AddPatientCommand addBobCommand = new AddPatientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPatientCommand addAliceCommandCopy = new AddPatientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithPatient extends ModelStub {
        private final Patient patient;

        ModelStubWithPatient(Patient patient) {
            requireNonNull(patient);
            this.patient = patient;
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patient.isSamePatient(patient);
        }

    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        final ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return patientsAdded.stream().anyMatch(patient::isSamePerson);
        }

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            patientsAdded.add(patient);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
