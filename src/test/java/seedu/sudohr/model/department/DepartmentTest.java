package seedu.sudohr.model.department;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_ENGINEERING;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.testutil.DepartmentBuilder;
import seedu.sudohr.testutil.EmployeeBuilder;
import seedu.sudohr.testutil.TypicalEmployees;

public class DepartmentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Department department = new DepartmentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> department.getEmployees().remove(0));
    }

    @Test
    public void execute_addEmployee_success() {
        Department department = new DepartmentBuilder().build();

        department.addEmployee(TypicalEmployees.ALICE);
        assertTrue(department.hasEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void execute_addDuplicateEmployee_throwsException() {
        Department department = new DepartmentBuilder().build();

        department.addEmployee(new EmployeeBuilder().build());

        assertThrows(DuplicateEmployeeException.class, () -> department.addEmployee(new EmployeeBuilder().build()));
    }

    @Test
    public void execute_addExactDuplicateEmployee_throwsException() {
        Department department = new DepartmentBuilder().build();

        department.addEmployee(TypicalEmployees.ALICE);

        assertThrows(DuplicateEmployeeException.class, () -> department.addEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void execute_removeEmployee_success() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(new EmployeeBuilder().build());

        department.removeEmployee(new EmployeeBuilder().build());
        assertTrue(department.equals(new DepartmentBuilder().build()));
    }

    @Test
    public void execute_removeExactEmployee_success() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(TypicalEmployees.ALICE);

        department.removeEmployee(TypicalEmployees.ALICE);
        assertTrue(department.equals(new DepartmentBuilder().build()));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Department humanResourcesCopy = new DepartmentBuilder(HUMAN_RESOURCES).build();
        assertTrue(HUMAN_RESOURCES.equals(humanResourcesCopy));

        // same object -> returns true
        assertTrue(HUMAN_RESOURCES.equals(HUMAN_RESOURCES));

        // null -> returns false
        assertFalse(HUMAN_RESOURCES.equals(null));

        // different type -> returns false
        assertFalse(HUMAN_RESOURCES.equals(5));

        // different employee -> returns false
        assertFalse(HUMAN_RESOURCES.equals(ENGINEERING));

        // different name -> returns false
        Department editedHumanResources = new DepartmentBuilder(HUMAN_RESOURCES)
                .withDepartmentName(VALID_DEPARTMENT_NAME_ENGINEERING).build();
        assertFalse(HUMAN_RESOURCES.equals(editedHumanResources));
    }
}
