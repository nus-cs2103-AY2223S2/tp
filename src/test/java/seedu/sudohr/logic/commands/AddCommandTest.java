package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
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
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.Date;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.testutil.EmployeeBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_employeeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        Employee validEmployee = new EmployeeBuilder().build();

        CommandResult commandResult = new AddCommand(validEmployee).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEmployee), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEmployee), modelStub.employeesAdded);
    }

    @Test
    public void execute_duplicateEmployee_throwsCommandException() {
        Employee validEmployee = new EmployeeBuilder().build();
        AddCommand addCommand = new AddCommand(validEmployee);
        ModelStub modelStub = new ModelStubWithEmployee(validEmployee);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EMPLOYEE, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateIdOnly_throwsCommandException() {
        Employee validEmployee = new EmployeeBuilder().build();
        Employee sameIdEmployee = new EmployeeBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        AddCommand addCommand = new AddCommand(validEmployee);
        ModelStub modelStub = new ModelStubWithEmployee(sameIdEmployee);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_EMPLOYEE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEmailOnly_throwsCommandException() {
        Employee validEmployee = new EmployeeBuilder().build();
        Employee sameEmail = new EmployeeBuilder().withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
                .withAddress(VALID_ADDRESS_BOB).withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        AddCommand addCommand = new AddCommand(validEmployee);
        ModelStub modelStub = new ModelStubWithEmployee(sameEmail);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EMAIL, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePhoneNumberOnly_throwsCommandException() {
        Employee validEmployee = new EmployeeBuilder().build();
        Employee samePhone = new EmployeeBuilder().withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
                .withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        AddCommand addCommand = new AddCommand(validEmployee);
        ModelStub modelStub = new ModelStubWithEmployee(samePhone);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelStub));
    }

    // duplicate phone should be identified first
    @Test
    public void execute_differentIdOnly_throwsCommandException() {
        Employee validEmployee = new EmployeeBuilder().build();
        Employee diffId = new EmployeeBuilder().withId(VALID_ID_BOB).build();
        AddCommand addCommand = new AddCommand(validEmployee);
        ModelStub modelStub = new ModelStubWithEmployee(diffId);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        // note default ID for employee builder is "0777"
        Employee alice = new EmployeeBuilder().withName("Alice").build();
        Employee bob = new EmployeeBuilder().withName("Bob").build();
        Employee differentBob = new EmployeeBuilder().withName("BOB").withId(VALID_ID_BOB).build();

        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);
        AddCommand addDifferentBobCommand = new AddCommand(differentBob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // same employee id -> returns false
        // Note: strict equality across every field
        assertFalse(addAliceCommand.equals(addBobCommand));

        // different employee id -> returns false
        assertFalse(addDifferentBobCommand.equals(addAliceCommand));
    }

    /**
     * A default model stub that have all of its methods failing.
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
        public void addEmployee(Employee employee) {
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
        public boolean hasEmployee(Employee employee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmployee(Employee employee, Employee excludeFromCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingEmail(Employee employee) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasClashingEmail(Employee employee, Employee excludeFromCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingPhoneNumber(Employee employee) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public boolean hasClashingPhoneNumber(Employee employee, Employee excludeFromCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Employee getEmployee(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEmployee(Employee target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEmployee(Employee target, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Employee> getFilteredEmployeeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Department getDepartment(DepartmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDepartment(Department department) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDepartment(Department d) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDepartment(Department target, Department editedDepartment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeDepartment(Department key) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEmployeeToDepartment(Employee p, Department d) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addEmployeeToDepartment'");
        }

        @Override
        public void removeEmployeeFromDepartment(Employee p, Department d) {
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
        public boolean hasEmployeeOnLeave(Date date, Employee employee) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasEmployeeOnLeave'");
        }

        @Override
        public void addEmployeeToLeave(Leave leaveToAdd, Employee employeeToAdd) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addEmployeeToLeave'");
        }

        @Override
        public ObservableList<Leave> getFilteredLeaveList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFilteredLeaveList'");
        }

        @Override
        public ObservableList<Leave> getLeavesList() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getLeavesList'");
        }

        @Override
        public void deleteEmployeeFromLeave(Leave leaveToDelete, Employee employeeToDelete) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deleteEmployeeFromLeave'");
        }

        @Override
        public void updateFilteredLeaveList(Predicate<Leave> predicateShowAllLeave) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateFilteredLeaveList'");
        }

        @Override
        public void cascadeUpdateUserInLeaves(Employee employeeToEdit, Employee editedEmployee) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'cascadeUpdateUserInLeaves'");
        }

        @Override
        public void cascadeDeleteUserInLeaves(Employee employeeToDelete) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'cascadeDeleteUserInLeaves'");
        }
    }

    /**
     * A Model stub that contains a single employee.
     */
    private class ModelStubWithEmployee extends ModelStub {
        private final Employee employee;

        ModelStubWithEmployee(Employee employee) {
            requireNonNull(employee);
            this.employee = employee;
        }

        @Override
        public boolean hasEmployee(Employee employee) {
            requireNonNull(employee);
            return this.employee.isSameEmployee(employee);
        }

        @Override
        public boolean hasClashingEmail(Employee employee) {
            requireNonNull(employee);
            return this.employee.emailClashes(employee);
        }

        @Override
        public boolean hasClashingPhoneNumber(Employee employee) {
            requireNonNull(employee);
            return this.employee.phoneClashes(employee);
        }
    }

    /**
     * A Model stub that always accept the employee being added.
     */
    private class ModelStubAcceptingEmployeeAdded extends ModelStub {
        final ArrayList<Employee> employeesAdded = new ArrayList<>();

        @Override
        public boolean hasEmployee(Employee employee) {
            requireNonNull(employee);
            return employeesAdded.stream().anyMatch(employee::isSameEmployee);
        }

        @Override
        public boolean hasClashingEmail(Employee employee) {
            requireNonNull(employee);
            return employeesAdded.stream().anyMatch(employee::emailClashes);
        }

        @Override
        public boolean hasClashingPhoneNumber(Employee employee) {
            requireNonNull(employee);
            return employeesAdded.stream().anyMatch(employee::phoneClashes);
        }

        @Override
        public void addEmployee(Employee employee) {
            requireNonNull(employee);
            employeesAdded.add(employee);
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            return new SudoHr();
        }
    }

}
