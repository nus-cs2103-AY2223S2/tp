package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.testutil.DoctorBuilder;



public class AddDoctorCommandTest {


    @Test
    public void constructor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDoctorCommand(null));
    }

    @Test
    public void execute_doctorAcceptedByModel_addSuccessful() throws Exception {
        AddDoctorCommandTest.ModelStubAcceptingDoctorAdded modelStub = new AddDoctorCommandTest
                .ModelStubAcceptingDoctorAdded();
        Doctor validDoctor = new DoctorBuilder().build();

        CommandResult commandResult = new AddDoctorCommand(validDoctor).execute(modelStub);

        assertEquals(String.format(AddDoctorCommand.getMessageSuccess(), validDoctor),
                commandResult.getCliFeedbackToUser());
        assertEquals(Arrays.asList(validDoctor), modelStub.doctorsAdded);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor validDoctor = new DoctorBuilder().build();
        AddDoctorCommand addDoctorCommand = new AddDoctorCommand(validDoctor);
        ModelStub modelStub = new AddDoctorCommandTest.ModelStubWithDoctor(validDoctor);

        assertThrows(CommandException.class, AddDoctorCommand.getMessageDuplicateDoctor(), () ->
                addDoctorCommand.execute(modelStub));
    }

    @Test
    public void execute_getCommandUsage_success() {
        String messageUsage = AddDoctorCommand.COMMAND_WORD + " (short form: "
                + AddDoctorCommand.SHORTHAND_COMMAND_WORD + ")"
                + ": Adds a doctor to the address book. "
                + "Parameters: "
                + PREFIX_NAME + "NAME "
                + PREFIX_PHONE + "PHONE "
                + PREFIX_EMAIL + "EMAIL "
                + PREFIX_SPECIALTY + "SPECIALTY "
                + PREFIX_YOE + "YEARS OF EXPERIENCE "
                + "[" + PREFIX_TAG + "TAG]...\n"
                + "Example: " + AddDoctorCommand.COMMAND_WORD + " "
                + PREFIX_NAME + "John Doe "
                + PREFIX_PHONE + "98765432 "
                + PREFIX_EMAIL + "johnd@example.com "
                + PREFIX_SPECIALTY + "Cardiology "
                + PREFIX_YOE + "5 "
                + PREFIX_TAG + "surgeon";
        assertEquals(AddDoctorCommand.getCommandUsage(), messageUsage);
    }

    @Test
    public void execute_addDoctor_getMessageSuccessSuccessful() {
        Doctor validDoctor = new DoctorBuilder().build();
        String messageSuccess = "New doctor added: %1$s";
        assertEquals(String.format(AddDoctorCommand.getMessageSuccess(), validDoctor), String.format(messageSuccess,
                validDoctor));
    }

    @Test
    public void execute_addDoctor_getMessageDuplicateDoctorSuccessful() {
        String messageDuplicateDoctor = "This doctor already exists in the address book";
        assertEquals(AddDoctorCommand.getMessageDuplicateDoctor(), messageDuplicateDoctor);
    }


    @Test
    public void equals() {
        Doctor alice = new DoctorBuilder().withName("Alice").build();
        Doctor bob = new DoctorBuilder().withName("Bob").build();
        AddDoctorCommand addAliceCommand = new AddDoctorCommand(alice);
        AddDoctorCommand addBobCommand = new AddDoctorCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDoctorCommand addAliceCommandCopy = new AddDoctorCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single doctor.
     */
    private class ModelStubWithDoctor extends ModelStub {
        private final Doctor doctor;

        ModelStubWithDoctor(Doctor doctor) {
            requireNonNull(doctor);
            this.doctor = doctor;
        }

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return this.doctor.isSameDoctor(doctor);
        }

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingDoctorAdded extends ModelStub {
        final ArrayList<Doctor> doctorsAdded = new ArrayList<>();

        @Override
        public boolean hasDoctor(Doctor doctor) {
            requireNonNull(doctor);
            return doctorsAdded.stream().anyMatch(doctor::isSamePerson);
        }

        @Override
        public void addDoctor(Doctor doctor) {
            requireNonNull(doctor);
            doctorsAdded.add(doctor);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
