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
import seedu.sudohr.testutil.DepartmentBuilder;

public class AddDepartmentCommandTest {

    @Test
    public void constructor_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDepartmentCommand(null));
    }

    @Test
    public void execute_departmentAcceptedByModel_addSuccessful() throws Exception {
        AddDepartmentCommandTest.ModelStubAcceptingDepartmentAdded modelStub =
                new AddDepartmentCommandTest.ModelStubAcceptingDepartmentAdded();
        Department validDepartment = new DepartmentBuilder().build();

        CommandResult commandResult = new AddDepartmentCommand(validDepartment).execute(modelStub);

        assertEquals(String.format(AddDepartmentCommand.MESSAGE_SUCCESS, validDepartment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDepartment), modelStub.departmentsAdded);
    }

    @Test
    public void execute_duplicateDepartment_throwsCommandException() {
        Department validDepartment = new DepartmentBuilder().build();
        AddDepartmentCommand addDepartmentCommand = new AddDepartmentCommand(validDepartment);
        AddDepartmentCommandTest.ModelStub modelStub =
                new AddDepartmentCommandTest.ModelStubWithDepartment(validDepartment);

        assertThrows(CommandException.class, AddDepartmentCommand.MESSAGE_DUPLICATE_DEPARTMENT, () ->
                addDepartmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Department humanResources = new DepartmentBuilder().withDepartmentName("Human Resources").build();
        Department engineering = new DepartmentBuilder().withDepartmentName("Engineering").build();
        AddDepartmentCommand addHumanResourcesCommand = new AddDepartmentCommand(humanResources);
        AddDepartmentCommand addEngineeringCommand = new AddDepartmentCommand(engineering);

        // same object -> returns true
        assertTrue(addHumanResourcesCommand.equals(addHumanResourcesCommand));

        // same values -> returns true
        AddDepartmentCommand addHumanResourcesCommandCopy = new AddDepartmentCommand(humanResources);
        assertTrue(addHumanResourcesCommand.equals(addHumanResourcesCommandCopy));

        // different types -> returns false
        assertFalse(addHumanResourcesCommand.equals(1));

        // null -> returns false
        assertFalse(addHumanResourcesCommand.equals(null));

        // different person -> returns false
        assertFalse(addHumanResourcesCommand.equals(addEngineeringCommand));
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
        public void setSudoHr(ReadOnlySudoHr sudoHr) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setSudoHr'");
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSudoHr'");
        }

        @Override
        public boolean hasPerson(Person person) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasPerson'");
        }

        @Override
        public void deletePerson(Person target) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deletePerson'");
        }

        @Override
        public void addPerson(Person person) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addPerson'");
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
     * A Model stub that contains a single department.
     */
    private class ModelStubWithDepartment extends AddDepartmentCommandTest.ModelStub {
        private final Department department;

        ModelStubWithDepartment(Department department) {
            requireNonNull(department);
            this.department = department;
        }

        @Override
        public boolean hasDepartment(Department department) {
            requireNonNull(department);
            return this.department.equals(department);
        }
    }

    /**
     * A Model stub that always accept the department being added.
     */
    private class ModelStubAcceptingDepartmentAdded extends AddDepartmentCommandTest.ModelStub {
        final ArrayList<Department> departmentsAdded = new ArrayList<>();

        @Override
        public boolean hasDepartment(Department department) {
            requireNonNull(department);
            return departmentsAdded.stream().anyMatch(department::equals);
        }

        @Override
        public void addDepartment(Department department) {
            requireNonNull(department);
            departmentsAdded.add(department);
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            return new SudoHr();
        }
    }
}
