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
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.testutil.StaffBuilder;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null));
    }

    @Test
    public void execute_patientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStaffAdded modelStub = new ModelStubAcceptingStaffAdded();
        Staff validStaff = new StaffBuilder().build();

        CommandResult commandResult = new AddStaffCommand(validStaff).execute(modelStub);

        assertEquals(String.format(AddStaffCommand.MESSAGE_SUCCESS, validStaff), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStaff), modelStub.staffAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Staff validStaff = new StaffBuilder().build();
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff);
        ModelStub modelStub = new ModelStubWithPerson(validStaff);

        assertThrows(CommandException.class,
                AddStaffCommand.MESSAGE_DUPLICATE_PERSON, () -> addStaffCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Staff alex = new StaffBuilder().withName("Alex").build();
        Staff billy = new StaffBuilder().withName("Billy").build();
        AddStaffCommand addStaffAlexCommand = new AddStaffCommand(alex);
        AddStaffCommand addStaffBillyCommand = new AddStaffCommand(billy);

        // same object -> returns true
        assertTrue(addStaffAlexCommand.equals(addStaffAlexCommand));

        // same values -> returns true
        AddStaffCommand addStaffAlexCommandCopy = new AddStaffCommand(alex);
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
        public void addPerson(Person person) {
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
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
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
        public void addPerson(Person person) {
            requireNonNull(person);
            staffAdded.add((Staff) person);
        }

        @Override
        public ReadOnlyPatientist getPatientist() {
            return new Patientist();
        }
    }
}

