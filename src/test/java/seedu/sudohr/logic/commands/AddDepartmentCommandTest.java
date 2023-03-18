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
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
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

        // different employee -> returns false
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
