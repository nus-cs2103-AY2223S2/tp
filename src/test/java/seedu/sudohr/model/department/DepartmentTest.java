package seedu.sudohr.model.department;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.AMY;
import static seedu.sudohr.testutil.TypicalEmployees.BOB;
import static seedu.sudohr.testutil.TypicalEmployees.CARL;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
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
    public void addEmployee_validEmployee_success() {
        Department department = new DepartmentBuilder().build();

        department.addEmployee(TypicalEmployees.ALICE);
        assertTrue(department.hasEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void addEmployee_duplicateEmployee_throwsDuplicateEmployeeException() {
        Department department = new DepartmentBuilder().build();

        department.addEmployee(new EmployeeBuilder().build());

        assertThrows(DuplicateEmployeeException.class, () -> department.addEmployee(new EmployeeBuilder().build()));
    }

    @Test
    public void addEmployee_sameReference_throwsDuplicateEmployeeException() {
        Department department = new DepartmentBuilder().build();

        department.addEmployee(TypicalEmployees.ALICE);

        assertThrows(DuplicateEmployeeException.class, () -> department.addEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void removeEmployee_sameEmployee_success() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(new EmployeeBuilder().build());

        department.removeEmployee(new EmployeeBuilder().build());
        assertTrue(department.equals(new DepartmentBuilder().build()));
    }

    @Test
    public void removeEmployee_sameReference_success() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(TypicalEmployees.ALICE);

        department.removeEmployee(TypicalEmployees.ALICE);
        assertTrue(department.equals(new DepartmentBuilder().build()));
    }

    @Test
    public void setEmployee_nullTargetEmployee_throwsNullPointerException() {
        Department department = new DepartmentBuilder().build();
        assertThrows(NullPointerException.class, () -> department.setEmployee(null, ALICE));
    }

    @Test
    public void setEmployee_nullEditedEmployee_throwsNullPointerException() {
        Department department = new DepartmentBuilder().build();
        assertThrows(NullPointerException.class, () -> department.setEmployee(ALICE, null));
    }

    @Test
    public void setEmployee_targetEmployeeNotInList_throwsEmployeeNotFoundException() {
        Department department = new DepartmentBuilder().build();
        assertThrows(EmployeeNotFoundException.class, () -> department.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setEmployee_editedIdAlreadyExists_throwsDuplicateEmployeeException() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(ALICE);
        department.addEmployee(BOB);
        Employee sameIdAsBob = new EmployeeBuilder().withId(VALID_ID_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> department.setEmployee(ALICE, sameIdAsBob));
    }

    @Test
    public void setEmployee_editedEmployeeDuplicatedPhoneNumber_throwsDuplicatePhoneNumberException() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(AMY);
        department.addEmployee(BOB);
        department.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> department.setEmployee(BOB, newBob));
    }

    @Test
    public void setEmployee_editedEmployeeDuplicatedEmail_throwsDuplicateEmailException() {
        Department department = new DepartmentBuilder().build();
        department.addEmployee(AMY);
        department.addEmployee(BOB);
        department.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicateEmailException.class, () -> department.setEmployee(BOB, newBob));
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
