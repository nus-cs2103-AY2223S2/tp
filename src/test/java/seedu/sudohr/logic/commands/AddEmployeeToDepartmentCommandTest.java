package seedu.sudohr.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.nio.file.Path;
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
import seedu.sudohr.testutil.TypicalDepartmentNames;
import seedu.sudohr.testutil.TypicalEmployees;

public class AddEmployeeToDepartmentCommandTest {

    // Handle adding an employee
    @Test
    public void execute_employeeAcceptedByDepartment_addSuccessful() throws CommandException {

        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        CommandResult commandResult = new AddEmployeeToDepartmentCommand(new Id("101"),
                TypicalDepartmentNames.DEPARTMENT_NAME_FIRST).execute(modelStub);

        assertEquals(String.format(AddEmployeeToDepartmentCommand.MESSAGE_ADD_EMPLOYEE_TO_DEPARTMENT_SUCCESS,
                TypicalEmployees.ALICE, TypicalDepartmentNames.DEPARTMENT_NAME_FIRST),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.sudoHr.getDepartment(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                .hasEmployee(TypicalEmployees.ALICE));
    }
    // Handle adding duplicate employee
    @Test
    public void execute_duplicateEmployeeInDepartment_throwsCommandException() throws CommandException {

        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        new AddEmployeeToDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                .execute(modelStub);

        assertThrows(CommandException.class, AddEmployeeToDepartmentCommand.MESSAGE_DUPLICATE_EMPLOYEE, () ->
                new AddEmployeeToDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                .execute(modelStub));
    }

    // Handle adding non-existent department
    @Test
    public void execute_addEmployeeToNonExistentDepartment_throwsCommandException() {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);

        assertThrows(CommandException.class, AddEmployeeToDepartmentCommand.MESSAGE_DEPARTMENT_NOT_FOUND, () ->
                new AddEmployeeToDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                        .execute(modelStub));
    }

    // Handle adding non-existent employee
    @Test
    public void execute_addNonExistentEmployeeToDepartment_throwsCommandException() {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        assertThrows(CommandException.class, AddEmployeeToDepartmentCommand.MESSAGE_EMPLOYEE_NOT_FOUND, () ->
                new AddEmployeeToDepartmentCommand(new Id("101"), TypicalDepartmentNames.DEPARTMENT_NAME_FIRST)
                        .execute(modelStub));
    }

    // Handle adding null employee
    @Test
    public void execute_addNullEmployeeToDepartment_throwsCommandException() throws CommandException {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        Department toAdd = new Department(TypicalDepartmentNames.DEPARTMENT_NAME_FIRST);
        modelStub.addDepartment(toAdd);

        assertThrows(NullPointerException.class, () -> new AddEmployeeToDepartmentCommand(null,
                TypicalDepartmentNames.DEPARTMENT_NAME_FIRST));
    }

    // Handle adding null department
    @Test
    public void execute_addEmployeeToNullDepartment_throwsCommandException() throws CommandException {
        ModelStubAcceptingEmployeeAdded modelStub = new ModelStubAcceptingEmployeeAdded();
        modelStub.addEmployee(TypicalEmployees.ALICE);

        assertThrows(NullPointerException.class, () -> new AddEmployeeToDepartmentCommand(new Id("101"), null));
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
        public boolean checkEmployeeExists(Id id) {
            throw new AssertionError("This method should not be called.");
        };

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
     * A Model stub that always accept the employee being added to the department.
     */
    private class ModelStubAcceptingEmployeeAdded extends ModelStub {
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
