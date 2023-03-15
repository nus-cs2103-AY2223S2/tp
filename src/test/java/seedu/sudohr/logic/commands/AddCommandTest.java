package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.ReadOnlyUserPrefs;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.person.Person;
import seedu.sudohr.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
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
        public Path getSudoHrFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSudoHrFilePath(Path sudoHrFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSudoHr(ReadOnlySudoHr sudoHr) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deletePerson'");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setPerson'");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFilteredPersonList'");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredPersonList'");
        }

        @Override
        public Department getDepartment(DepartmentName name) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDepartment'");
        }

        @Override
        public boolean hasDepartment(Department department) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasDepartment'");
        }

        @Override
        public void addDepartment(Department d) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addDepartment'");
        }

        @Override
        public void setDepartment(Department target, Department editedDepartment) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setDepartment'");
        }

        @Override
        public void removeDepartment(Department key) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'removeDepartment'");
        }

        @Override
        public void addEmployeeToDepartment(Person p, Department d) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addEmployeeToDepartment'");
        }

        @Override
        public void removeEmployeeFromDepartment(Person p, Department d) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'removeEmployeeFromDepartment'");
        }

        @Override
        public ObservableList<Department> getFilteredDepartmentList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFilteredDepartmentList'");
        }

        @Override
        public void updateFilteredDepartmentList(Predicate<Department> predicate) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredDepartmentList'");
        }

        @Override
        public void addLeave(Leave leave) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addLeave'");
        }

        @Override
        public boolean hasLeave(Leave leave) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasLeave'");
        }

        @Override
        public Leave getInternalLeaveIfExist(Leave leaveToAdd) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getInternalLeaveIfExist'");
        }

        @Override
        public boolean hasEmployeeOnLeave(Date date, Person person) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasEmployeeOnLeave'");
        }

        @Override
        public void addEmployeeToLeave(Leave leaveToAdd, Person personToAdd) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addEmployeeToLeave'");
        }

        @Override
        public ObservableList<Leave> getFilteredLeaveList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFilteredLeaveList'");
        }

        @Override
        public ObservableList<Leave> getLeaveList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getLeaveList'");
        }

        @Override
        public void deleteEmployeeFromLeave(Leave leaveToDelete, Person personToDelete) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deleteEmployeeFromLeave'");
        }

        @Override
        public void updateFilteredLeaveList(Predicate<Leave> predicateShowAllLeave) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredLeaveList'");
        }

        @Override
        public void cascadeUpdateUserInLeaves(Person personToEdit, Person editedPerson) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'cascadeUpdateUserInLeaves'");
        }

        @Override
        public void cascadeDeleteUserInLeaves(Person personToDelete) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'cascadeDeleteUserInLeaves'");
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
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            return new SudoHr();
        }
    }

}
