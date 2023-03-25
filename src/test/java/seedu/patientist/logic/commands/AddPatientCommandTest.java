package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.patientist.commons.core.GuiSettings;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.ReadOnlyUserPrefs;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;
import seedu.patientist.testutil.PatientBuilder;

public class AddPatientCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPatientCommand(null, null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddPatientCommand("Block A Ward 1", validPatient).execute(modelStub);

        assertEquals(String.format(AddPatientCommand.MESSAGE_SUCCESS, validPatient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient validPatient = new PatientBuilder().build();
        AddPatientCommand addPatientCommand = new AddPatientCommand("Block A Ward 1", validPatient);
        ModelStub modelStub = new ModelStubWithPerson(validPatient);

        assertThrows(CommandException.class, AddPatientCommand.MESSAGE_DUPLICATE_PERSON, () ->
                addPatientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Patient alice = new PatientBuilder().withName("Alice").build();
        Patient bob = new PatientBuilder().withName("Bob").build();
        AddPatientCommand addAliceCommand = new AddPatientCommand("Block A Ward 1", alice);
        AddPatientCommand addBobCommand = new AddPatientCommand("Block A Ward 1", bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPatientCommand addAliceCommandCopy = new AddPatientCommand("Block A Ward 1", alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPatientistFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatientistFilePath(Path patientistFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatientist(ReadOnlyPatientist patientist) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPatientist getPatientist() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStaff(Staff staff, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStaff(Staff target, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStaff(Staff staff, Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient edited) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStaff(Staff target, Staff edited) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void transferPatient(Patient patient, Ward original, Ward target) {

        }

        @Override
        public void transferStaff(Staff staff, Ward original, Ward target) {

        }

        @Override
        public boolean hasWard(Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addWard(Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteWard(Ward ward) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWard(Ward target, Ward editedWard) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Ward getWard(String wardName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean hasWard(Ward ward) {
            return true;
        }

        @Override
        public Ward getWard(String wardName) {
            return new Ward(wardName);
        }

        @Override
        public void addPatient(Patient patient, Ward ward) {
            personsAdded.add(patient);
        }

        @Override
        public ReadOnlyPatientist getPatientist() {
            return new Patientist();
        }
    }
}
