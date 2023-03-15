package seedu.sudohr.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.AMY;
import static seedu.sudohr.testutil.TypicalEmployees.BOB;
import static seedu.sudohr.testutil.TypicalEmployees.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
import seedu.sudohr.testutil.EmployeeBuilder;

public class UniqueEmployeeListTest {

    private final UniqueEmployeeList uniqueEmployeeList = new UniqueEmployeeList();

    /** Tests equals method **/
    @Test
    public void equals_bothEmpty_success() {
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void equals_bothNull_success() {
        UniqueEmployeeList list = new UniqueEmployeeList();
        List<Integer> notList = new ArrayList<>();
        assertNotEquals(list, notList);
    }

    @Test
    public void equals_differentClass_success() {
        UniqueEmployeeList nullListOne = null;
        UniqueEmployeeList nullListTwo = null;
        assertEquals(nullListOne, nullListTwo);
    }

    @Test
    public void equals_bothContainSameContentSameOrder_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        Employee duplicatedAlice = new EmployeeBuilder(ALICE).build();
        Employee duplicatedBob = new EmployeeBuilder(BOB).build();
        expectedUniqueEmployeeList.add(duplicatedAlice);
        expectedUniqueEmployeeList.add(duplicatedBob);
        expectedUniqueEmployeeList.add(CARL);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void equals_bothContainSameContentDifferentOrder_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        Employee duplicatedAlice = new EmployeeBuilder(ALICE).build();
        Employee duplicatedBob = new EmployeeBuilder(BOB).build();
        expectedUniqueEmployeeList.add(CARL);
        expectedUniqueEmployeeList.add(duplicatedBob);
        expectedUniqueEmployeeList.add(duplicatedAlice);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void equals_sameContentButOneHasLess_notSuccess() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        Employee duplicatedAlice = new EmployeeBuilder(ALICE).build();
        Employee duplicatedBob = new EmployeeBuilder(BOB).build();
        expectedUniqueEmployeeList.add(duplicatedAlice);
        expectedUniqueEmployeeList.add(duplicatedBob);
        assertNotEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void equals_containDifferentContent_notSuccess() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        Employee duplicatedBob = new EmployeeBuilder(BOB).build();
        expectedUniqueEmployeeList.add(AMY);
        expectedUniqueEmployeeList.add(duplicatedBob);
        expectedUniqueEmployeeList.add(CARL);
        assertNotEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void equals_containFieldsChanged_notSuccess() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        Employee slightlyDifferentBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .build();
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(slightlyDifferentBob);
        expectedUniqueEmployeeList.add(CARL);
        assertNotEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    /** duplicated employee checks **/
    @Test
    public void contains_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.contains(null));
    }

    @Test
    public void contains_employeeNotInList_returnsFalse() {
        assertFalse(uniqueEmployeeList.contains(ALICE));
    }

    @Test
    public void contains_employeeInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        assertTrue(uniqueEmployeeList.contains(ALICE));
    }

    // Checks against a different Employee object with only the id field being the same
    @Test
    public void contains_employeeWithSameIdOnlyInList_returnsTrue() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniqueEmployeeList.contains(editedAlice));
    }

    // Checks against a different Employee object with every field the same except id
    @Test
    public void contains_employeeWithSameFieldsButIdInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniqueEmployeeList.contains(editedAlice));
    }

    /** duplicated phone number checks **/
    @Test
    public void sharesPhoneNumber_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.sharesPhoneNumber(null));
    }

    @Test
    public void sharesPhoneNumber_employeeNotInList_returnsFalse() {
        assertFalse(uniqueEmployeeList.sharesPhoneNumber(ALICE));
    }

    // returns false because it's the same employee
    @Test
    public void sharesPhoneNumber_employeeInList_returnsFalse() {
        uniqueEmployeeList.add(ALICE);
        assertFalse(uniqueEmployeeList.sharesPhoneNumber(ALICE));
    }

    // Checks against a different Employee object with only the id field being the same,
    // returns false even though phone number field is different because it's still the same employee.
    // Preventing 2 employees with the same ID is handled separately.
    @Test
    public void sharesPhoneNumber_employeeWithSameIdOnlyInList_returnsFalse() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniqueEmployeeList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Employee object with every field the same except id.
    // Returns true since different employees but same phone numbers.
    @Test
    public void sharesPhoneNumber_employeeWithSameFieldsButIdInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniqueEmployeeList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Employee object with every field the same except phone number.
    // Returns true since different employees but same phone number field.
    @Test
    public void sharesPhoneNumber_employeeWithSameEmailOnlyInList_returnsTrue() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(uniqueEmployeeList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Employee object with every field the same except phone number and id.
    // Returns false since same employee after all.
    @Test
    public void sharesPhoneNumber_employeeWithSameEmailAndIdOnlyInList_returnsFalse() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .build();
        assertFalse(uniqueEmployeeList.sharesPhoneNumber(editedAlice));
    }

    /** duplicated emails checks **/
    @Test
    public void sharesEmail_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.sharesEmail(null));
    }

    @Test
    public void sharesEmail_employeeNotInList_returnsFalse() {
        assertFalse(uniqueEmployeeList.sharesEmail(ALICE));
    }

    // returns false because it's the same employee
    @Test
    public void sharesEmail_employeeInList_returnsFalse() {
        uniqueEmployeeList.add(ALICE);
        assertFalse(uniqueEmployeeList.sharesEmail(ALICE));
    }

    // Checks against a different Employee object with only the id field being the same,
    // returns false even though email field is different because it's still the same employee.
    // Preventing 2 employees with the same ID is handled separately.
    @Test
    public void sharesEmail_employeeWithSameIdOnlyInList_returnsFalse() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniqueEmployeeList.sharesEmail(editedAlice));
    }

    // Checks against a different Employee object with every field the same except id.
    // Returns true since different employees but same email field.
    @Test
    public void sharesEmail_employeeWithSameFieldsButIdInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniqueEmployeeList.sharesEmail(editedAlice));
    }

    // Checks against a different Employee object with every field the same except email.
    // Returns true since different employees but same email field.
    @Test
    public void sharesEmail_employeeWithSameEmailOnlyInList_returnsTrue() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(uniqueEmployeeList.sharesEmail(editedAlice));
    }

    // Checks against a different Employee object with every field the same except email and id.
    // Returns false since same employee after all.
    @Test
    public void sharesEmail_employeeWithSameEmailAndIdOnlyInList_returnsFalse() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertFalse(uniqueEmployeeList.sharesEmail(editedAlice));
    }

    /** Tests adding of a employee **/
    @Test
    public void add_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.add(null));
    }

    @Test
    public void add_duplicateEmployee_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.add(ALICE));
    }

    @Test
    public void add_employeeWithSameId_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.add(editedAlice));
    }

    @Test
    public void add_differentEmployeeWithSamePhoneNumber_throwsDuplicatePhoneNumberException() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniqueEmployeeList.add(editedAlice));
    }

    @Test
    public void add_differentEmployeeWithSameEmail_throwsDuplicateEmailException() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertThrows(DuplicateEmailException.class, () -> uniqueEmployeeList.add(editedAlice));
    }

    // first error accounted is that of duplicate employee
    @Test
    public void add_employeeWithSameIdEmailPhone_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.add(editedAlice));
    }

    // first error accounted is that of duplicate phone
    @Test
    public void add_employeeWithSameEmailPhone_throwsDuplicatePhoneNumberException() {
        uniqueEmployeeList.add(BOB);
        Employee editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniqueEmployeeList.add(editedAlice));
    }


    /** Tests editing of a employee **/
    @Test
    public void setEmployee_nullTargetEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(null, ALICE));
    }

    @Test
    public void setEmployee_nullEditedEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(ALICE, null));
    }

    @Test
    public void setEmployee_targetEmployeeNotInList_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setEmployee_editedEmployeeIsSameEmployee_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.setEmployee(ALICE, ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeAlreadyExists_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.setEmployee(ALICE, BOB));
    }

    @Test
    public void setEmployee_editedEmployeeChangeAllUnique_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(CARL);
        uniqueEmployeeList.setEmployee(ALICE, BOB);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB);
        expectedUniqueEmployeeList.add(CARL);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee kept same id (ie change every other field)
    @Test
    public void setEmployee_editedEmployeeHasSameIdentityOnly_success() {
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee changed id only (ie no change to other fields)
    @Test
    public void setEmployee_editedEmployeeChangeUniqueIdOnly_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee actually made no change to its own id
    @Test
    public void setEmployee_editedEmployeeNoChangeToId_success() {
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(ALICE);
        Employee newBob = new EmployeeBuilder(BOB).withId(VALID_ID_BOB)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(AMY);
        expectedUniqueEmployeeList.add(ALICE);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee changed to an id that already exists
    @Test
    public void setEmployee_editedIdAlreadyExists_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        Employee sameIdAsBob = new EmployeeBuilder().withId(VALID_ID_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.setEmployee(ALICE, sameIdAsBob));
    }

    // test with some fields changed, excluding id
    @Test
    public void setEmployee_editedEmployeeHasSameIdentityAndEmailOnly_success() {
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(CARL);
        expectedUniqueEmployeeList.add(newBob);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // test with some fields changed, including id
    @Test
    public void setEmployee_editedEmployeeChangeIdEmailAddressOnly_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(CARL);
        uniqueEmployeeList.add(BOB);
        Employee newBob = new EmployeeBuilder(BOB).withId(VALID_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(CARL);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee change to non-duplicated phone number
    @Test
    public void setEmployee_editedEmployeeNewPhoneIsUnique_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee made no change to phone number
    @Test
    public void setEmployee_editedEmployeePhoneNoChange_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_BOB)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }


    // edited employee change to duplicated phone number shared with someone SudoHR
    @Test
    public void setEmployee_editedEmployeeDuplicatedPhoneNumber_throwsDuplicatePhoneNumberException() {
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniqueEmployeeList.setEmployee(BOB, newBob));
    }

    // edited employee changed to duplicated phone number and email shared with someone in SudoHR
    @Test
    public void setEmployee_editedEmployeeChangeEmailPhone_throwsDuplicatePhoneNumberException() {
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniqueEmployeeList.setEmployee(BOB, newBob));
    }

    // edited employee changed some fields, including phone number
    @Test
    public void setEmployee_editedEmployeeSomeChangesAndPhone_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withId(VALID_ID_AMY)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);
        expectedUniqueEmployeeList.add(ALICE);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee change to non-duplicated email
    @Test
    public void setEmployee_editedEmployeeNewEmailIsUnique_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    // edited employee made no change to email
    @Test
    public void setEmployee_editedEmployeeEmailNoChange_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }


    // edited employee change to duplicated email as someone SudoHR
    @Test
    public void setEmployee_editedEmployeeDuplicatedEmail_throwsDuplicateEmailException() {
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicateEmailException.class, () -> uniqueEmployeeList.setEmployee(BOB, newBob));
    }

    // edited employee changed some fields, including email
    @Test
    public void setEmployee_editedEmployeeSomeChangesAndEmail_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(BOB);
        uniqueEmployeeList.add(CARL);
        Employee newBob = new EmployeeBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
                .build();
        uniqueEmployeeList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(newBob);
        expectedUniqueEmployeeList.add(CARL);
        expectedUniqueEmployeeList.add(ALICE);

        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    /** Tests removal of a employee **/
    @Test
    public void remove_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.remove(null));
    }

    @Test
    public void remove_employeeDoesNotExist_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.remove(ALICE));
    }

    @Test
    public void remove_existingEmployee_removesEmployee() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.remove(ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    /** Tests setting of employees with a provided list **/
    @Test
    public void setEmployees_nullUniqueEmployeeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((UniqueEmployeeList) null));
    }

    @Test
    public void setEmployees_uniqueEmployeeList_replacesOwnListWithProvidedUniqueEmployeeList() {
        uniqueEmployeeList.add(ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB);
        uniqueEmployeeList.setEmployees(expectedUniqueEmployeeList);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((List<Employee>) null));
    }

    @Test
    public void setEmployees_list_replacesOwnListWithProvidedList() {
        uniqueEmployeeList.add(ALICE);
        List<Employee> employeeList = Collections.singletonList(BOB);
        uniqueEmployeeList.setEmployees(employeeList);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BOB);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_listWithDuplicateEmployees_throwsDuplicateEmployeeException() {
        List<Employee> listWithDuplicateEmployees = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEmployeeException.class, ()
                -> uniqueEmployeeList.setEmployees(listWithDuplicateEmployees));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEmployeeList.asUnmodifiableObservableList().remove(0));
    }
}
