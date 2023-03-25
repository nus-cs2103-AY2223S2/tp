package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalPatients.BOB;

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





public class DeleteWardCommandTest {
    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddWardCommand(null));
    }

    @Test
    public void execute_wardDeletedByModel_deleteSuccessful() throws Exception {
        Ward ward = new Ward("Ward1");
        ModelStubAcceptingWardAdded modelStub = new ModelStubAcceptingWardAdded(ward);

        CommandResult commandResult = new DeleteWardCommand("Ward1").execute(modelStub);

        assertEquals(String.format(DeleteWardCommand.MESSAGE_DELETE_WARD_SUCCESS, ward),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.wardsAdded);
    }

    @Test
    public void execute_wardNotEmpty_throwsCommandException() {
        Ward validWard = new WardBuilder().withName("Ward1").withPatient(BOB).build();
        DeleteWardCommand deleteWardCommand = new DeleteWardCommand(validWard.getWardName());
        ModelStub modelStub = new ModelStubAcceptingWardAdded(validWard);

        assertThrows(CommandException.class, DeleteWardCommand.MESSAGE_NON_EMPTY_WARD, () ->
                deleteWardCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        DeleteWardCommand deleteWardCommand1 = new DeleteWardCommand("ward1");
        DeleteWardCommand deleteWardCommand2 = new DeleteWardCommand("ward2");

        // same object -> returns true
        assertTrue(deleteWardCommand1.equals(deleteWardCommand1));

        // same values -> returns true
        DeleteWardCommand deleteWardCommandCopy = new DeleteWardCommand("ward1");
        assertTrue(deleteWardCommand1.equals(deleteWardCommandCopy));

        // different types -> returns false
        assertFalse(deleteWardCommand1.equals(1));

        // null -> returns false
        assertFalse(deleteWardCommand1.equals(null));

        // different person -> returns false
        assertFalse(deleteWardCommand1.equals(deleteWardCommand2));
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
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingWardAdded extends ModelStub {
        final ArrayList<Ward> wardsAdded;

        ModelStubAcceptingWardAdded(Ward ward) {
            requireNonNull(ward);
            this.wardsAdded = new ArrayList<>(Arrays.asList(ward));
        }

        @Override
        public boolean hasWard(Ward ward) {
            return true;
        }

        @Override
        public Ward getWard(String wardName) {
            return wardsAdded.get(0);
        }

        @Override
        public void deleteWard(Ward ward) {
            wardsAdded.remove(ward);
        }

        @Override
        public ReadOnlyPatientist getPatientist() {
            return new Patientist();
        }
    }
}
