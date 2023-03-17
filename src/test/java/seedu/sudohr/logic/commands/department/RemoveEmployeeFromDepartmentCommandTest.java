package seedu.sudohr.logic.commands.department;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
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
import seedu.sudohr.testutil.TypicalDepartmentNames;
import seedu.sudohr.testutil.TypicalEmployees;

import java.nio.file.Path;
import java.util.function.Predicate;

public class RemoveEmployeeFromDepartmentCommandTest {

    // Handle removing an employee
    @Test
    public void execute_employeeAcceptedByDepartment_addSuccessful() throws CommandException {

        RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved modelStub =
                new RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved();
        modelStub.addEmployee(TypicalEmployees.ALICE);
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        new AddEmployeeToDepartmentCommand(new Id("101"),
                TypicalDepartmentNames.DEPARTMENT_NAME_FIRST).execute(modelStub);

        CommandResult commandResult = new RemoveEmployeeFromDepartmentCommand(new Id("101"),
                TypicalDepartmentNames.DEPARTMENT_NAME_FIRST).execute(modelStub);

        assertEquals(String.format(RemoveEmployeeFromDepartmentCommand.MESSAGE_REMOVE_EMPLOYEE_FROM_DEPARTMENT_SUCCESS,
                        TypicalEmployees.ALICE, TypicalDepartmentNames.DEPARTMENT_NAME_FIRST),
                commandResult.getFeedbackToUser());
        assertFalse(modelStub.sudoHr.getDepartment(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                .hasEmployee(TypicalEmployees.ALICE));
    }

    // Remove from nonexistent department
    @Test
    public void execute_removeEmployeeFromNonExistentDepartment_throwsCommandException() {
        RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved modelStub =
                new RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved();
        modelStub.addEmployee(TypicalEmployees.ALICE);

        assertThrows(CommandException.class, RemoveEmployeeFromDepartmentCommand.MESSAGE_DEPARTMENT_NOT_FOUND, () ->
                new RemoveEmployeeFromDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                        .execute(modelStub));
    }

    // Remove a nonexistent employee
    @Test
    public void execute_removeNonExistentEmployeeFromDepartment_throwsCommandException() {
        RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved modelStub =
                new RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved();
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        assertThrows(CommandException.class, RemoveEmployeeFromDepartmentCommand.MESSAGE_EMPLOYEE_NOT_FOUND, () ->
                new RemoveEmployeeFromDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                        .execute(modelStub));
    }

    // Remove an employee that is not in the department
    @Test
    public void execute_removeNonExistentEmployeeInDepartmentFromDepartment_throwsCommandException() {
        RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved modelStub =
                new RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved();
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);
        modelStub.addEmployee(TypicalEmployees.ALICE);

        assertThrows(CommandException.class,
                RemoveEmployeeFromDepartmentCommand.MESSAGE_EMPLOYEE_NOT_FOUND_IN_DEPARTMENT, () ->
                new RemoveEmployeeFromDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                        .execute(modelStub));
    }

    // Remove null
    @Test
    public void execute_addNullEmployeeToDepartment_throwsCommandException() throws CommandException {
        RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved modelStub =
                new RemoveEmployeeFromDepartmentCommandTest.ModelStubAcceptingEmployeeRemoved();
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        assertThrows(NullPointerException.class, () -> new RemoveEmployeeFromDepartmentCommand(null,
                TypicalDepartmentNames.DEPARTMENT_NAME_FIRST));
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
    }

    /**
     * A Model stub that always accept the employee being removed from the department.
     */
    private class ModelStubAcceptingEmployeeRemoved extends RemoveEmployeeFromDepartmentCommandTest.ModelStub {
        private SudoHr sudoHr = new SudoHr();

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
        public boolean hasDepartment(Department department) {
            requireNonNull(department);
            return sudoHr.hasDepartment(department);
        }

        @Override
        public void addDepartment(Department d) {
            sudoHr.addDepartment(d);
        }

        @Override
        public Department getDepartment(DepartmentName name) {
            return sudoHr.getDepartment(name);
        }

        @Override
        public ReadOnlySudoHr getSudoHr() {
            return sudoHr;
        }
    }
}
