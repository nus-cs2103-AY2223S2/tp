package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalWards.VALID_WARD_NAME;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import seedu.patientist.testutil.StaffBuilder;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(VALID_WARD_NAME, null));

    }

    @Test
    public void execute_staffAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStaffAdded modelStub = new ModelStubAcceptingStaffAdded();
        Staff validStaff = new StaffBuilder().build();

        CommandResult commandResult = new AddStaffCommand(VALID_WARD_NAME, validStaff).execute(modelStub);

        assertEquals(String.format(AddStaffCommand.MESSAGE_SUCCESS, validStaff), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStaff), modelStub.staffAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Staff validStaff = new StaffBuilder().build();
        AddStaffCommand addStaffCommand = new AddStaffCommand(VALID_WARD_NAME, validStaff);
        ModelStub modelStub = new ModelStubWithPerson(validStaff);

        assertThrows(CommandException.class,
                AddStaffCommand.MESSAGE_DUPLICATE_PERSON, () -> addStaffCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Staff alex = new StaffBuilder().withName("Alex").build();
        Staff billy = new StaffBuilder().withName("Billy").build();
        AddStaffCommand addStaffAlexCommand = new AddStaffCommand(VALID_WARD_NAME, alex);
        AddStaffCommand addStaffBillyCommand = new AddStaffCommand(VALID_WARD_NAME, billy);

        // same object -> returns true
        assertTrue(addStaffAlexCommand.equals(addStaffAlexCommand));

        // same values -> returns true
        AddStaffCommand addStaffAlexCommandCopy = new AddStaffCommand(VALID_WARD_NAME, alex);
        assertTrue(addStaffAlexCommand.equals(addStaffAlexCommandCopy));

        // different types -> returns false
        assertFalse(addStaffAlexCommand.equals(1));

        // null -> returns false
        assertFalse(addStaffAlexCommand.equals(null));

        // different person -> returns false
        assertFalse(addStaffAlexCommand.equals(addStaffBillyCommand));
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
        public void setPatientistFilePath(Path addressBookFilePath) {
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
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void transferStaff(Staff staff, Ward original, Ward target) {
            throw new AssertionError("This method should not be called.");

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
        public List<String> getWardNames() {
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
        private final Staff staff;

        ModelStubWithPerson(Staff staff) {
            requireNonNull(staff);
            this.staff = staff;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.staff.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingStaffAdded extends ModelStub {
        final ArrayList<Staff> staffAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return staffAdded.stream().anyMatch(x -> x.isSamePerson(person));
        }

        @Override
        public boolean hasWard(Ward ward) {
            requireNonNull(ward);
            return true;
        }

        @Override
        public Ward getWard(String wardName) {
            return new Ward(wardName);
        }

        @Override
        public void addStaff(Staff staff, Ward ward) {
            staffAdded.add(staff);
        }

        @Override
        public ReadOnlyPatientist getPatientist() {
            return new Patientist();
        }
    }
}

