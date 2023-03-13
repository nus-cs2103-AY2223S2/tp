package seedu.sudohr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.SALES;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;
import static seedu.sudohr.testutil.TypicalPersons.ALICE;
import static seedu.sudohr.testutil.TypicalPersons.BENSON;
import static seedu.sudohr.testutil.TypicalPersons.BOB;
import static seedu.sudohr.testutil.TypicalPersons.CARL;
import static seedu.sudohr.testutil.TypicalPersons.GEORGE;
import static seedu.sudohr.testutil.TypicalPersons.HOON;
import static seedu.sudohr.testutil.TypicalPersons.IDA;

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
import seedu.sudohr.testutil.DepartmentBuilder;
import seedu.sudohr.testutil.PersonBuilder;

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
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // adding two person with the exact same fields
        Employee editedAlice = new PersonBuilder(ALICE).build();
        List<Employee> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newPersons, newDepartments);

        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateId_throwsDuplicatePersonException() {
        // adding two persons with only id matching
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        List<Employee> newPersons = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newPersons, newDepartments);

        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEmail_throwsDuplicateEmailException() {
        // adding two persons with only email matching
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .build();
        List<Employee> newPersons = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newPersons, newDepartments);

        assertThrows(DuplicateEmailException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicatePhoneNumber_throwsDuplicatePhoneNumberException() {
        // adding two persons with only phone number matching
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .build();
        List<Employee> newPersons = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newPersons, newDepartments);

        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEmailPhoneNumber_throwsDuplicatePhoneNumberException() {
        // adding two persons with only phone number matching
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        List<Employee> newPersons = Arrays.asList(BOB, editedAlice);
        List<Department> newDepartments = Arrays.asList();
        SudoHrStub newData = new SudoHrStub(newPersons, newDepartments);

        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasEmployee(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasEmployee(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        sudoHr.addEmployee(ALICE);
        assertTrue(sudoHr.hasEmployee(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdInAddressBook_returnsTrue() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(sudoHr.hasEmployee(editedAlice));
    }

    @Test
    public void hasClashingEmail_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasClashingEmail(null));
    }

    @Test
    public void hasClashingEmail_personNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasClashingEmail(ALICE));
    }

    @Test
    public void hasClashingEmail_personWithSameIdInAddressBook_returnsFalse() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertFalse(sudoHr.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingEmail_personWithDifferentIdInAddressBook_returnsTrue() {
        sudoHr.addEmployee(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(sudoHr.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingEmail_personWithSameEmailOnlyInAddressBook_returnsTrue() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .build();
        assertTrue(sudoHr.hasClashingEmail(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.hasClashingPhoneNumber(null));
    }

    @Test
    public void hasClashingPhoneNumber_personNotInAddressBook_returnsFalse() {
        assertFalse(sudoHr.hasClashingPhoneNumber(ALICE));
    }

    @Test
    public void hasClashingPhoneNumber_personWithSameIdInAddressBook_returnsFalse() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertFalse(sudoHr.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_personWithDifferentIdInAddressBook_returnsTrue() {
        sudoHr.addEmployee(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        assertTrue(sudoHr.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void hasClashingPhoneNumber_personWithSamePhoneNumberOnlyInAddressBook_returnsTrue() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .build();
        assertTrue(sudoHr.hasClashingPhoneNumber(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sudoHr.getEmployeeList().remove(0));
    }

    //// Department tests

    @Test
    public void resetData_withDuplicateDepartments_throwsDuplicateDepartmentException() {
        // Two persons with the same identity fields
        Department editedHumanResources = new DepartmentBuilder(HUMAN_RESOURCES).withDepartmentName("Sales").build();
        List<Employee> newPersons = Arrays.asList(ALICE, BENSON, CARL, GEORGE, HOON, IDA);
        List<Department> newDepartments = Arrays.asList(editedHumanResources, SALES);

        SudoHrStub newData = new SudoHrStub(newPersons, newDepartments);

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
     * A stub ReadOnlySudoHr whose persons and departments list can violate interface constraints.
     */
    private static class SudoHrStub implements ReadOnlySudoHr {
        private final ObservableList<Employee> persons = FXCollections.observableArrayList();
        private final ObservableList<Department> departments = FXCollections.observableArrayList();

        SudoHrStub(Collection<Employee> persons, Collection<Department> departments) {
            this.persons.setAll(persons);
            this.departments.setAll(departments);
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return persons;
        }

        @Override
        public ObservableList<Department> getDepartmentList() {
            return departments;
        }
    }

}
