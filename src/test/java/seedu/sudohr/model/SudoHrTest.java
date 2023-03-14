package seedu.sudohr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.*;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.SALES;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;
import static seedu.sudohr.testutil.TypicalPersons.*;
import static seedu.sudohr.testutil.TypicalPersons.AMY;

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

    /** Tests adding of a person **/
    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.addEmployee(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        sudoHr.addEmployee(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.addEmployee(ALICE));
    }

    @Test
    public void add_personWithSameId_throwsDuplicatePersonException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    @Test
    public void add_differentPersonWithSamePhoneNumber_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    @Test
    public void add_differentPersonWithSameEmail_throwsDuplicateEmailException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertThrows(DuplicateEmailException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    // first error accounted is that of duplicate person
    @Test
    public void add_PersonWithSameIdEmailPhone_throwsDuplicatePersonException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.addEmployee(editedAlice));
    }

    // first error accounted is that of duplicate phone
    @Test
    public void add_PersonWithSameEmailPhone_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.addEmployee(editedAlice));
    }


    /** Tests editing of a person **/
    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.setEmployee(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sudoHr.setEmployee(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> sudoHr.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.setEmployee(ALICE, ALICE);
        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        assertEquals(expectedSudoHr, sudoHr);
    }

    @Test
    public void setPerson_editedPersonAlreadyExists_throwsDuplicatePersonException() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.setEmployee(ALICE, BOB));
    }

    @Test
    public void setPerson_editedPersonChangeAllUnique_success() {
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
    public void setPerson_editedPersonHasSameIdentityOnly_success() {
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);
        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited employee changed id only (ie no change to other fields)
    @Test
    public void setPerson_editedPersonChangeUniqueIdOnly_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited person actually made no change to its own id
    @Test
    public void setPerson_editedPersonNoChangeToId_success() {
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(ALICE);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(AMY);
        expectedSudoHr.addEmployee(ALICE);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited person changed to an id that already exists
    @Test
    public void setPerson_editedIdAlreadyExists_throwsDuplicatePersonException() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        Employee sameIdAsBob = new PersonBuilder().withId(VALID_ID_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> sudoHr.setEmployee(ALICE, sameIdAsBob));
    }

    // test with some fields changed, excluding id
    @Test
    public void setPerson_editedPersonHasSameIdentityAndEmailOnly_success() {
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(CARL);
        expectedSudoHr.addEmployee(newBob);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // test with some fields changed, including id
    @Test
    public void setPerson_editedPersonChangeIdEmailAddressOnly_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(CARL);
        sudoHr.addEmployee(BOB);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited person change to non-duplicated phone number
    @Test
    public void setPerson_editedPersonNewPhoneIsUnique_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited person made no change to phone number
    @Test
    public void setPerson_editedPersonPhoneNoChange_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }


    // edited person change to duplicated phone number shared with someone SudoHR
    @Test
    public void setPerson_editedPersonDuplicatedPhoneNumber_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.setEmployee(BOB, newBob));
    }

    // edited person changed to duplicated phone number and email shared with someone in SudoHR
    @Test
    public void setPerson_editedPersonChangeEmailPhone_throwsDuplicatePhoneNumberException() {
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> sudoHr.setEmployee(BOB, newBob));
    }

    // edited person changed some fields, including phone number
    @Test
    public void setPerson_editedPersonSomeChangesAndPhone_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withId(VALID_ID_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);
        expectedSudoHr.addEmployee(ALICE);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited person change to non-duplicated email
    @Test
    public void setPerson_editedPersonNewEmailIsUnique_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }

    // edited person made no change to email
    @Test
    public void setPerson_editedPersonEmailNoChange_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        sudoHr.setEmployee(BOB, newBob);

        SudoHr expectedSudoHr = new SudoHr();
        expectedSudoHr.addEmployee(ALICE);
        expectedSudoHr.addEmployee(newBob);
        expectedSudoHr.addEmployee(CARL);

        assertEquals(expectedSudoHr, sudoHr);
    }


    // edited person change to duplicated email as someone SudoHR
    @Test
    public void setPerson_editedPersonDuplicatedEmail_throwsDuplicateEmailException() {
        sudoHr.addEmployee(AMY);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicateEmailException.class, () -> sudoHr.setEmployee(BOB, newBob));
    }

    // edited person changed some fields, including email
    @Test
    public void setPerson_editedPersonSomeChangesAndEmail_success() {
        sudoHr.addEmployee(ALICE);
        sudoHr.addEmployee(BOB);
        sudoHr.addEmployee(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
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
