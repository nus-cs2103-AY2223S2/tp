package seedu.sudohr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.SALES;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;
import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.AMY;
import static seedu.sudohr.testutil.TypicalEmployees.BENSON;
import static seedu.sudohr.testutil.TypicalEmployees.BOB;
import static seedu.sudohr.testutil.TypicalEmployees.CARL;
import static seedu.sudohr.testutil.TypicalEmployees.GEORGE;
import static seedu.sudohr.testutil.TypicalEmployees.HOON;
import static seedu.sudohr.testutil.TypicalEmployees.IDA;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.exceptions.DuplicateDepartmentException;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
import seedu.sudohr.testutil.DepartmentBuilder;
import seedu.sudohr.testutil.EmployeeBuilder;

public class SudoHrTest {

    private final SudoHr sudoHr = new SudoHr();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), sudoHr.getEmployeeList());
    }

    //// Employee tests

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        SudoHr newData = getTypicalSudoHr();
        sudoHr.resetData(newData);
        assertEquals(newData, sudoHr);
    }

    @Test
    public void resetData_withDuplicateEmployees_throwsDuplicateEmployeeException() {
        // adding two employee with the exact same fields
        Employee editedAlice = new EmployeeBuilder(ALICE).build();
        List<Employee> newEmployees = Arrays.asList(ALICE, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newEmployees, newDepartments);

        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateId_throwsDuplicateEmployeeException() {
        // adding two employees with only id matching
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        List<Employee> newEmployees = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newEmployees, newDepartments);

        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEmail_throwsDuplicateEmailException() {
        // adding two employees with only email matching
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .build();
        List<Employee> newEmployees = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newEmployees, newDepartments);

        assertThrows(DuplicateEmailException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicatePhoneNumber_throwsDuplicatePhoneNumberException() {
        // adding two employees with only phone number matching
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .build();
        List<Employee> newEmployees = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newEmployees, newDepartments);

        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEmailPhoneNumber_throwsDuplicatePhoneNumberException() {
        // adding two employees with only phone number matching
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        List<Employee> newEmployees = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newEmployees, newDepartments);

        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasEmployee(null));
    }

    @Test
    public void hasEmployee_employeeNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeInAddressBook_returnsTrue() {
        sudoHr.addEmployee(ALICE);
        assertTrue(sudoHr.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeWithSameIdInAddressBook_returnsTrue() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(sudoHr.hasEmployee(editedAlice));
    }

    @Test
    public void hasClashingEmail_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasClashingEmail(null));
    }

    @Test
    public void hasClashingEmail_employeeNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasClashingEmail(ALICE));
    }

    @Test
    public void hasClashingEmail_employeeWithSameIdInAddressBook_returnsFalse() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertFalse(sudoHr.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingEmail_employeeWithDifferentIdInAddressBook_returnsTrue() {
        sudoHr.addEmployee(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(sudoHr.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingEmail_employeeWithSameEmailOnlyInAddressBook_returnsTrue() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(sudoHr.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasClashingPhoneNumber(null));
    }

    @Test
    public void hasClashingPhoneNumber_employeeNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasClashingPhoneNumber(ALICE));
    }

    @Test
    public void hasClashingPhoneNumber_employeeWithSameIdInAddressBook_returnsFalse() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertFalse(sudoHr.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_employeeWithDifferentIdInAddressBook_returnsTrue() {
        sudoHr.addEmployee(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(sudoHr.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_employeeWithSamePhoneNumberOnlyInAddressBook_returnsTrue() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .build();
        assertTrue(sudoHr.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sudoHr.getEmployeeList().remove(0));
    }

    /** Tests adding of a employee **/
    @Test
    public void add_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.addEmployee(null));
    }

    @Test
    public void add_duplicateEmployee_throwsDuplicateEmployeeException() {
        sudoHr.addEmployee(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.addEmployee(ALICE));
    }

    @Test
    public void add_employeeWithSameId_throwsDuplicateEmployeeException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    @Test
    public void add_differentEmployeeWithSamePhoneNumber_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    @Test
    public void add_differentEmployeeWithSameEmail_throwsDuplicateEmailException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertThrows(DuplicateEmailException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    // first error accounted is that of duplicate employee
    @Test
    public void add_employeeWithSameIdEmailPhone_throwsDuplicateEmployeeException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    // first error accounted is that of duplicate phone
    @Test
    public void add_employeeWithSameEmailPhone_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.addEmployee(editedAlice));
    }


    /** Tests editing of a employee **/
    @Test
    public void setEmployee_nullTargetEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.setEmployee(null, ALICE));
    }

    @Test
    public void setEmployee_nullEditedEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.setEmployee(ALICE, null));
    }

    @Test
    public void setEmployee_targetEmployeeNotInList_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> sudoHr.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setEmployee_editedEmployeeIsSameEmployee_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.setEmployee(ALICE, ALICE);
        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        assertEquals(expectedSudoHr, sudoHr);
    }

    @Test
    public void setEmployee_editedEmployeeAlreadyExists_throwsDuplicateEmployeeException() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.setEmployee(ALICE, BOB));
    }

    @Test
    public void setEmployee_editedEmployeeChangeAllUnique_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(CARL);
        sudoHr.setEmployee(ALICE, BOB);
        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(BOB);
        expectedSudoHr.addEmployee(CARL);
        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee kept same id (ie change every other field)
    @Test
    public void setEmployee_editedEmployeeHasSameIdentityOnly_success() {
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);
        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee changed id only (ie no change to other fields)
    @Test
    public void setEmployee_editedEmployeeChangeUniqueIdOnly_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee actually made no change to its own id
    @Test
    public void setEmployee_editedEmployeeNoChangeToId_success() {
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(ALICE);
        Employee newBob = new EmployeeBuilder(BOB).withId(VALID_ID_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(AMY);
        expectedSudoHr.addEmployee(ALICE);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee changed to an id that already exists
    @Test
    public void setEmployee_editedIdAlreadyExists_throwsDuplicateEmployeeException() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        Employee sameIdAsBob = new EmployeeBuilder().withId(VALID_ID_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.setEmployee(ALICE, sameIdAsBob));
    }

    // test with some fields changed, excluding id
    @Test
    public void setEmployee_editedEmployeeHasSameIdentityAndEmailOnly_success() {
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(CARL);
        expectedSudoHr.addEmployee(newBob);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // test with some fields changed, including id
    @Test
    public void setEmployee_editedEmployeeChangeIdEmailAddressOnly_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(CARL);
        sudoHr.addEmployee(BOB);
        Employee newBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee change to non-duplicated phone number
    @Test
    public void setEmployee_editedEmployeeNewPhoneIsUnique_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee made no change to phone number
    @Test
    public void setEmployee_editedEmployeePhoneNoChange_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }


    // edited employee change to duplicated phone number shared with someone SudoHR
    @Test
    public void setEmployee_editedEmployeeDuplicatedPhoneNumber_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.setEmployee(BOB, newBob));
    }

    // edited employee changed to duplicated phone number and email shared with someone in SudoHR
    @Test
    public void setEmployee_editedEmployeeChangeEmailPhone_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.setEmployee(BOB, newBob));
    }

    // edited employee changed some fields, including phone number
    @Test
    public void setEmployee_editedEmployeeSomeChangesAndPhone_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withId(VALID_ID_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);
        expectedSudoHr.addEmployee(ALICE);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee change to non-duplicated email
    @Test
    public void setEmployee_editedEmployeeNewEmailIsUnique_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee made no change to email
    @Test
    public void setEmployee_editedEmployeeEmailNoChange_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }


    // edited employee change to duplicated email as someone SudoHR
    @Test
    public void setEmployee_editedEmployeeDuplicatedEmail_throwsDuplicateEmailException() {
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicateEmailException.class, () -> sudoHr.setEmployee(BOB, newBob));
    }

    // edited employee changed some fields, including email
    @Test
    public void setEmployee_editedEmployeeSomeChangesAndEmail_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);
        expectedSudoHr.addEmployee(ALICE);

        assertEquals(expectedSudoHr, sudoHr);
    }

    //// Department tests

    @Test
    public void resetData_withDuplicateDepartments_throwsDuplicateDepartmentException() {
        // Two employees with the same identity fields
        Department editedHumanResources = new DepartmentBuilder(HUMAN_RESOURCES).withDepartmentName("Sales").build();
        List<Employee> newEmployees = Arrays.asList(ALICE, BENSON, CARL, GEORGE, HOON, IDA);
        List<Department> newDepartments = Arrays.asList(editedHumanResources, SALES);

        SudoHrStub newData = new SudoHrStub(newEmployees, newDepartments);

        assertThrows(DuplicateDepartmentException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void hasDepartment_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasDepartment(null));
    }

    @Test
    public void hasDepartment_departmentNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasDepartment(HUMAN_RESOURCES));
    }

    @Test
    public void hasDepartment_departmentInAddressBook_returnsTrue() {
        sudoHr.addDepartment(ENGINEERING);
        assertTrue(sudoHr.hasDepartment(ENGINEERING));
    }

    @Test
    public void hasDepartment_departmentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        sudoHr.addDepartment(HUMAN_RESOURCES);
        Department editedEngineering = new DepartmentBuilder(ENGINEERING).withDepartmentName("Human Resources")
                .build();
        assertTrue(sudoHr.hasDepartment(editedEngineering));
    }

    @Test
    public void getDepartmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sudoHr.getDepartmentList().remove(0));
    }

    /**
     * A stub ReadOnlySudoHr whose employees and departments list can violate interface constraints.
     */
    private static class SudoHrStub implements ReadOnlySudoHr {
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();
        private final ObservableList<Department> departments = FXCollections.observableArrayList();

        SudoHrStub(Collection<Employee> employees, Collection<Department> departments) {
            this.employees.setAll(employees);
            this.departments.setAll(departments);
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return employees;
        }

        @Override
        public ObservableList<Department> getDepartmentList() {
            return departments;
        }
    }

}
