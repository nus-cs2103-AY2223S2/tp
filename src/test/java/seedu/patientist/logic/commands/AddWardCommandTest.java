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
import seedu.patientist.testutil.WardBuilder;

public class AddWardCommandTest {
    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddWardCommand(null));
    }

    @Test
    public void execute_wardAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingWardAdded modelStub = new ModelStubAcceptingWardAdded();
        Ward validWard = new WardBuilder().build();

        CommandResult commandResult = new AddWardCommand(validWard).execute(modelStub);

        assertEquals(String.format(AddWardCommand.MESSAGE_SUCCESS, validWard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validWard), modelStub.wardsAdded);
    }

    @Test
    public void execute_duplicateWard_throwsCommandException() {
        Ward validWard = new WardBuilder().build();
        AddWardCommand addWardCommand = new AddWardCommand(validWard);
        ModelStub modelStub = new ModelStubWithWard(validWard);

        assertThrows(CommandException.class, AddWardCommand.MESSAGE_DUPLICATE_WARD, () ->
                addWardCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Ward ward1 = new WardBuilder().withName("Ward1").build();
        Ward ward2 = new WardBuilder().withName("Ward2").build();
        AddWardCommand addWardCommand1 = new AddWardCommand(ward1);
        AddWardCommand addWardCommand2 = new AddWardCommand(ward2);

        // same object -> returns true
        assertTrue(addWardCommand1.equals(addWardCommand1));

        // same values -> returns true
        AddWardCommand addWardCommandCopy = new AddWardCommand(ward1);
        assertTrue(addWardCommand1.equals(addWardCommandCopy));

        // different types -> returns false
        assertFalse(addWardCommand1.equals(1));

        // null -> returns false
        assertFalse(addWardCommand1.equals(null));

        // different person -> returns false
        assertFalse(addWardCommand1.equals(addWardCommand2));
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
    private class ModelStubWithWard extends ModelStub {
        private final Ward ward;

        ModelStubWithWard(Ward ward) {
            requireNonNull(ward);
            this.ward = ward;
        }

        @Override
        public boolean hasWard(Ward ward) {
            requireNonNull(ward);
            return this.ward.equals(ward);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingWardAdded extends ModelStub {
        final ArrayList<Ward> wardsAdded = new ArrayList<>();

        @Override
        public boolean hasWard(Ward ward) {
            return false;
        }

        @Override
        public Ward getWard(String wardName) {
            return new Ward(wardName);
        }

        @Override
        public void addWard(Ward ward) {
            wardsAdded.add(ward);
        }

        @Override
        public ReadOnlyPatientist getPatientist() {
            return new Patientist();
        }
    }
}
