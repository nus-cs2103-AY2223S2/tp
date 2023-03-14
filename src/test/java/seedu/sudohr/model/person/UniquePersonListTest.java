package seedu.sudohr.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import static seedu.sudohr.testutil.TypicalPersons.ALICE;
import static seedu.sudohr.testutil.TypicalPersons.AMY;
import static seedu.sudohr.testutil.TypicalPersons.BOB;
import static seedu.sudohr.testutil.TypicalPersons.CARL;

import java.util.*;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.UniqueEmployeeList;
import seedu.sudohr.model.employee.exceptions.DuplicateEmailException;
import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;

import seedu.sudohr.testutil.PersonBuilder;

public class UniquePersonListTest {

    private final UniqueEmployeeList uniquePersonList = new UniqueEmployeeList();

    /** Tests equals method **/
    @Test
    public void equals_bothEmpty_success() {
        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void equals_bothNull_success() {
        UniqueEmployeeList nullListOne = null;
        UniqueEmployeeList nullListTwo = null;
        assertEquals(nullListOne, nullListTwo);
    }

    @Test
    public void equals_bothContainSameContentSameOrder_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        Employee duplicatedAlice = new PersonBuilder(ALICE).build();
        Employee duplicatedBob = new PersonBuilder(BOB).build();
        expectedUniquePersonList.add(duplicatedAlice);
        expectedUniquePersonList.add(duplicatedBob);
        expectedUniquePersonList.add(CARL);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void equals_bothContainSameContentDifferentOrder_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        Employee duplicatedAlice = new PersonBuilder(ALICE).build();
        Employee duplicatedBob = new PersonBuilder(BOB).build();
        expectedUniquePersonList.add(CARL);
        expectedUniquePersonList.add(duplicatedBob);
        expectedUniquePersonList.add(duplicatedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void equals_sameContentButOneHasLess_notSuccess() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        Employee duplicatedAlice = new PersonBuilder(ALICE).build();
        Employee duplicatedBob = new PersonBuilder(BOB).build();
        expectedUniquePersonList.add(duplicatedAlice);
        expectedUniquePersonList.add(duplicatedBob);
        assertNotEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void equals_containDifferentContent_notSuccess() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        Employee duplicatedBob = new PersonBuilder(BOB).build();
        expectedUniquePersonList.add(AMY);
        expectedUniquePersonList.add(duplicatedBob);
        expectedUniquePersonList.add(CARL);
        assertNotEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void equals_containFieldsChanged_notSuccess() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        Employee slightlyDifferentBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .build();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(slightlyDifferentBob);
        expectedUniquePersonList.add(CARL);
        assertNotEquals(expectedUniquePersonList, uniquePersonList);
    }

    /** duplicated person checks **/
    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        assertTrue(uniquePersonList.contains(ALICE));
    }

    // Checks against a different Person object with only the id field being the same
    @Test
    public void contains_personWithSameIdOnlyInList_returnsTrue() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    // Checks against a different Person object with every field the same except id
    @Test
    public void contains_personWithSameFieldsButIdInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniquePersonList.contains(editedAlice));
    }

    /** duplicated phone number checks **/
    @Test
    public void sharesPhoneNumber_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.sharesPhoneNumber(null));
    }

    @Test
    public void sharesPhoneNumber_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.sharesPhoneNumber(ALICE));
    }

    // returns false because it's the same person
    @Test
    public void sharesPhoneNumber_personInList_returnsFalse() {
        uniquePersonList.add(ALICE);
        assertFalse(uniquePersonList.sharesPhoneNumber(ALICE));
    }

    // Checks against a different Person object with only the id field being the same,
    // returns false even though phone number field is different because it's still the same person.
    // Preventing 2 persons with the same ID is handled separately.
    @Test
    public void sharesPhoneNumber_personWithSameIdOnlyInList_returnsFalse() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Person object with every field the same except id.
    // Returns true since different persons but same phone numbers.
    @Test
    public void sharesPhoneNumber_personWithSameFieldsButIdInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Person object with every field the same except phone number.
    // Returns true since different persons but same phone number field.
    @Test
    public void sharesPhoneNumber_personWithSameEmailOnlyInList_returnsTrue() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Person object with every field the same except phone number and id.
    // Returns false since same person after all.
    @Test
    public void sharesPhoneNumber_personWithSameEmailAndIdOnlyInList_returnsFalse() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .build();
        assertFalse(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    /** duplicated emails checks **/
    @Test
    public void sharesEmail_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.sharesEmail(null));
    }

    @Test
    public void sharesEmail_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.sharesEmail(ALICE));
    }

    // returns false because it's the same person
    @Test
    public void sharesEmail_personInList_returnsFalse() {
        uniquePersonList.add(ALICE);
        assertFalse(uniquePersonList.sharesEmail(ALICE));
    }

    // Checks against a different Person object with only the id field being the same,
    // returns false even though email field is different because it's still the same person.
    // Preventing 2 persons with the same ID is handled separately.
    @Test
    public void sharesEmail_personWithSameIdOnlyInList_returnsFalse() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniquePersonList.sharesEmail(editedAlice));
    }

    // Checks against a different Person object with every field the same except id.
    // Returns true since different persons but same email field.
    @Test
    public void sharesEmail_personWithSameFieldsButIdInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniquePersonList.sharesEmail(editedAlice));
    }

    // Checks against a different Person object with every field the same except email.
    // Returns true since different persons but same email field.
    @Test
    public void sharesEmail_personWithSameEmailOnlyInList_returnsTrue() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(uniquePersonList.sharesEmail(editedAlice));
    }

    // Checks against a different Person object with every field the same except email and id.
    // Returns false since same person after all.
    @Test
    public void sharesEmail_personWithSameEmailAndIdOnlyInList_returnsFalse() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        assertFalse(uniquePersonList.sharesEmail(editedAlice));
    }

    /** Tests adding of a person **/
    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> uniquePersonList.add(ALICE));
    }

    @Test
    public void add_personWithSameId_throwsDuplicatePersonException() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertThrows(DuplicateEmployeeException.class, () -> uniquePersonList.add(editedAlice));
    }

    @Test
    public void add_differentPersonWithSamePhoneNumber_throwsDuplicatePhoneNumberException() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniquePersonList.add(editedAlice));
    }

    @Test
    public void add_differentPersonWithSameEmail_throwsDuplicateEmailException() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertThrows(DuplicateEmailException.class, () -> uniquePersonList.add(editedAlice));
    }

    // first error accounted is that of duplicate person
    @Test
    public void add_PersonWithSameIdEmailPhone_throwsDuplicatePersonException() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> uniquePersonList.add(editedAlice));
    }

    // first error accounted is that of duplicate phone
    @Test
    public void add_PersonWithSameEmailPhone_throwsDuplicatePhoneNumberException() {
        uniquePersonList.add(BOB);
        Employee editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniquePersonList.add(editedAlice));
    }


    /** Tests editing of a person **/
    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEmployee(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEmployee(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniquePersonList.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setEmployee(ALICE, ALICE);
        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonAlreadyExists_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> uniquePersonList.setEmployee(ALICE, BOB));
    }

    @Test
    public void setPerson_editedPersonChangeAllUnique_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(CARL);
        uniquePersonList.setEmployee(ALICE, BOB);
        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(BOB);
        expectedUniquePersonList.add(CARL);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person kept same id (ie change every other field)
    @Test
    public void setPerson_editedPersonHasSameIdentityOnly_success() {
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited employee changed id only (ie no change to other fields)
    @Test
    public void setPerson_editedPersonChangeUniqueIdOnly_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_AMY)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person actually made no change to its own id
    @Test
    public void setPerson_editedPersonNoChangeToId_success() {
        uniquePersonList.add(BOB);
        uniquePersonList.add(AMY);
        uniquePersonList.add(ALICE);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_BOB)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(AMY);
        expectedUniquePersonList.add(ALICE);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person changed to an id that already exists
    @Test
    public void setPerson_editedIdAlreadyExists_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        Employee sameIdAsBob = new PersonBuilder().withId(VALID_ID_BOB)
                .build();
        assertThrows(DuplicateEmployeeException.class, () -> uniquePersonList.setEmployee(ALICE, sameIdAsBob));
    }

    // test with some fields changed, excluding id
    @Test
    public void setPerson_editedPersonHasSameIdentityAndEmailOnly_success() {
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(CARL);
        expectedUniquePersonList.add(newBob);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // test with some fields changed, including id
    @Test
    public void setPerson_editedPersonChangeIdEmailAddressOnly_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(CARL);
        uniquePersonList.add(BOB);
        Employee newBob = new PersonBuilder(BOB).withId(VALID_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person change to non-duplicated phone number
    @Test
    public void setPerson_editedPersonNewPhoneIsUnique_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person made no change to phone number
    @Test
    public void setPerson_editedPersonPhoneNoChange_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_BOB)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }


    // edited person change to duplicated phone number shared with someone SudoHR
    @Test
    public void setPerson_editedPersonDuplicatedPhoneNumber_throwsDuplicatePhoneNumberException() {
        uniquePersonList.add(AMY);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniquePersonList.setEmployee(BOB, newBob));
    }

    // edited person changed to duplicated phone number and email shared with someone in SudoHR
    @Test
    public void setPerson_editedPersonChangeEmailPhone_throwsDuplicatePhoneNumberException() {
        uniquePersonList.add(AMY);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniquePersonList.setEmployee(BOB, newBob));
    }

    // edited person changed some fields, including phone number
    @Test
    public void setPerson_editedPersonSomeChangesAndPhone_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY).withId(VALID_ID_AMY)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);
        expectedUniquePersonList.add(ALICE);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person change to non-duplicated email
    @Test
    public void setPerson_editedPersonNewEmailIsUnique_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    // edited person made no change to email
    @Test
    public void setPerson_editedPersonEmailNoChange_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(ALICE);
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }


    // edited person change to duplicated email as someone SudoHR
    @Test
    public void setPerson_editedPersonDuplicatedEmail_throwsDuplicateEmailException() {
        uniquePersonList.add(AMY);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .build();
        assertThrows(DuplicateEmailException.class, () -> uniquePersonList.setEmployee(BOB, newBob));
    }

    // edited person changed some fields, including email
    @Test
    public void setPerson_editedPersonSomeChangesAndEmail_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        uniquePersonList.add(CARL);
        Employee newBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
                .build();
        uniquePersonList.setEmployee(BOB, newBob);

        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(newBob);
        expectedUniquePersonList.add(CARL);
        expectedUniquePersonList.add(ALICE);

        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    /** Tests removal of a person **/
    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.remove(ALICE);
        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    /** Tests setting of persons with a provided list **/
    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEmployees((UniqueEmployeeList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(ALICE);
        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setEmployees(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEmployees((List<Employee>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(ALICE);
        List<Employee> personList = Collections.singletonList(BOB);
        uniquePersonList.setEmployees(personList);
        UniqueEmployeeList expectedUniquePersonList = new UniqueEmployeeList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Employee> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> uniquePersonList.setEmployees(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
