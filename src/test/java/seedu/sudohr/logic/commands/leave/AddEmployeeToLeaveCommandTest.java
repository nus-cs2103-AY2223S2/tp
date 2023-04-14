package seedu.sudohr.logic.commands.leave;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_1;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.ReadOnlyUserPrefs;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveDate;
import seedu.sudohr.testutil.TypicalEmployees;
import seedu.sudohr.testutil.TypicalLeave;

/**
 * Adds a employee using it's displayed index to a specific leave using it's
 * displayed index in sudohr book.
 */
public class AddEmployeeToLeaveCommandTest {

    @Test
    public void execute_employeeAcceptedByLeave_addSuccessful() throws CommandException {

        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);
        CommandResult commandResult = new AddEmployeeToLeaveCommand(
                TypicalEmployees.ALICE_ID,
                new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub);

        assertEquals(String.format(AddEmployeeToLeaveCommand.MESSAGE_ADD_LEAVE_SUCCESS,
                TypicalEmployees.ALICE, TypicalLeave.LEAVE_TYPE_1),
                commandResult.getFeedbackToUser());

        assertTrue(modelStub.sudoHr.getInternalLeaveIfExist(new Leave(new LeaveDate(LocalDate.parse(
                VALID_LEAVE_DATE_LEAVE_TYPE_1))))
                .hasEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void execute_addNonExistentEmployeeToLeave_throwsCommandException() throws CommandException {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        assertThrows(CommandException.class, Messages.MESSAGE_EMPLOYEE_NOT_FOUND, () ->
                new AddEmployeeToLeaveCommand(TypicalEmployees.ALICE_ID,
                        new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub));
    }


    // handle adding to leave objects that already exists

    @Test
    public void execute_employeeAcceptedByLeaveAndPreviousEmployeeExists_addSuccessful() throws CommandException {

        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);
        modelStub.addEmployee(TypicalEmployees.BENSON);

        new AddEmployeeToLeaveCommand(
                TypicalEmployees.ALICE_ID,
                new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub);

        CommandResult commandResult = new AddEmployeeToLeaveCommand(
                TypicalEmployees.BENSON_ID,
                new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub);

        assertEquals(String.format(AddEmployeeToLeaveCommand.MESSAGE_ADD_LEAVE_SUCCESS,
                TypicalEmployees.BENSON, TypicalLeave.LEAVE_TYPE_1),
                commandResult.getFeedbackToUser());

        assertTrue(modelStub.sudoHr.getInternalLeaveIfExist(new Leave(new LeaveDate(LocalDate.parse(
                VALID_LEAVE_DATE_LEAVE_TYPE_1))))
                .hasEmployee(TypicalEmployees.ALICE));
    }

    // handle duplicate employee in Leave
    @Test
    public void execute_duplicateEmployeeInLeave_throwsCommandException() throws CommandException {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);
        new AddEmployeeToLeaveCommand(TypicalEmployees.ALICE_ID,
                new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub);

        assertThrows(CommandException.class, AddEmployeeToLeaveCommand.MESSAGE_DUPLICATE_EMPLOYEE, () ->
                new AddEmployeeToLeaveCommand(TypicalEmployees.ALICE_ID_COPY,
                        new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub));

        assertThrows(CommandException.class, AddEmployeeToLeaveCommand.MESSAGE_DUPLICATE_EMPLOYEE, () ->
                new AddEmployeeToLeaveCommand(TypicalEmployees.ALICE_ID,
                        new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub));

    }

    // Handle adding null employee
    @Test
    public void execute_addNullEmployeeToLeave_throwsCommandException() throws CommandException {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();

        assertThrows(NullPointerException.class, () -> new AddEmployeeToLeaveCommand(null,
                new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(modelStub));
    }

    // Handle adding null leave date
    @Test
    public void execute_addEmployeeToNullLeave_throwsCommandException() throws CommandException {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();

        assertThrows(NullPointerException.class, () ->
                new AddEmployeeToLeaveCommand(TypicalEmployees.ALICE_ID, null).execute(modelStub));
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
        public Employee getEmployee(Id employeeId) {
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
        public void deleteEmployee(Employee target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEmployee(Employee target, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCountForDepartment(Department department) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCountForDepartment(DepartmentName departmentName) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeEmployeeFromDepartment(Employee p, Department d) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Department> getFilteredDepartmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDepartmentList(Predicate<Department> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cascadeDeleteEmployeeToDepartments(Employee employeeToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cascadeEditEmployeeToDepartments(Employee employeeToEdit, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Leave getLeave(LeaveDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLeave(Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLeave(Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Leave getInternalLeaveIfExist(Leave leaveToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmployeeOnLeave(LeaveDate date, Employee employee) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void addEmployeeToLeave(Leave leaveToAdd, Employee employeeToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Leave> getFilteredLeaveList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Leave> getLeavesList() {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void deleteEmployeeFromLeave(Leave leaveToDelete, Employee employeeToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCountOnLeave(Leave leave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCountOnLeave(LeaveDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLeaveList(Predicate<Leave> predicateShowAllLeave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cascadeUpdateUserInLeaves(Employee employeeToEdit, Employee editedEmployee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cascadeDeleteUserInLeaves(Employee employeeToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean checkEmployeeExists(Id id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refresh() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that always accept the employee being added to the leave.
     */
    private class ModelStubAcceptingEmployeeAdded extends ModelStub {
        private SudoHr sudoHr = new SudoHr();
        private final FilteredList<Employee> filteredEmployees;

        private ModelStubAcceptingEmployeeAdded() {
            this.filteredEmployees = new FilteredList<>(this.sudoHr.getEmployeeList());
        }

        @Override
        public void addEmployee(Employee employee) {
            sudoHr.addEmployee(employee);
        }

        @Override
        public Employee getEmployee(Id employeeId) {
            requireNonNull(employeeId);
            return sudoHr.getEmployee(employeeId);
        }

        @Override
        public ObservableList<Employee> getFilteredEmployeeList() {
            return filteredEmployees;

        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            return sudoHr;
        }

        @Override
        public void addLeave(Leave leave) {
            requireNonNull(leave);
            sudoHr.addLeave(leave);

        }

        @Override
        public boolean hasLeave(Leave leave) {
            requireNonNull(leave);
            return sudoHr.hasLeave(leave);
        }

        @Override
        public ObservableList<Leave> getLeavesList() {
            return this.sudoHr.getLeavesList();
        }

        @Override
        public Leave getInternalLeaveIfExist(Leave leaveToAdd) {
            if (sudoHr.hasLeave(leaveToAdd)) {
                return sudoHr.getInternalLeaveIfExist(leaveToAdd);
            } else {
                sudoHr.addLeave(leaveToAdd);
                return leaveToAdd;
            }
        }

        @Override
        public boolean hasEmployeeOnLeave(LeaveDate date, Employee employee) {
            requireAllNonNull(date, employee);
            return sudoHr.hasEmployeeOnLeave(date, employee);
        }

        @Override
        public void addEmployeeToLeave(Leave leaveToAdd, Employee employeeToAdd) {
            requireAllNonNull(leaveToAdd, employeeToAdd);

            sudoHr.addEmployeeToLeave(leaveToAdd, employeeToAdd);
        }

        @Override
        public void deleteEmployeeFromLeave(Leave leaveToDelete, Employee employeeToDelete) {
            requireAllNonNull(leaveToDelete, employeeToDelete);

            sudoHr.deleteEmployeeFromLeave(leaveToDelete, employeeToDelete);
        }

        // for this we do not modify the filtered list to facilitate testing
        @Override
        public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        }

        @Override
        public void updateFilteredLeaveList(Predicate<Leave> predicateShowAllLeave) {
        }

        @Override
        public boolean checkEmployeeExists(Id id) {
            requireNonNull(id);
            return sudoHr.checkEmployeeExists(id);
        }

        @Override
        public void refresh() {
        }

    }
}
