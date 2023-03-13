package seedu.sudohr.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalPersons.ALICE;
import static seedu.sudohr.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.person.exceptions.DuplicateEmailException;
import seedu.sudohr.model.person.exceptions.DuplicatePersonException;
import seedu.sudohr.model.person.exceptions.DuplicatePhoneNumberException;
import seedu.sudohr.model.person.exceptions.PersonNotFoundException;
import seedu.sudohr.testutil.PersonBuilder;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

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
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    // Checks against a different Person object with every field the same except id
    @Test
    public void contains_personWithSameFieldsButIdInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
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
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Person object with every field the same except id.
    // Returns true since different persons but same phone numbers.
    @Test
    public void sharesPhoneNumber_personWithSameFieldsButIdInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Person object with every field the same except phone number.
    // Returns true since different persons but same phone number field.
    @Test
    public void sharesPhoneNumber_personWithSameEmailOnlyInList_returnsTrue() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(uniquePersonList.sharesPhoneNumber(editedAlice));
    }

    // Checks against a different Person object with every field the same except phone number and id.
    // Returns false since same person after all.
    @Test
    public void sharesPhoneNumber_personWithSameEmailAndIdOnlyInList_returnsFalse() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
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
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertFalse(uniquePersonList.sharesEmail(editedAlice));
    }

    // Checks against a different Person object with every field the same except id.
    // Returns true since different persons but same email field.
    @Test
    public void sharesEmail_personWithSameFieldsButIdInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertTrue(uniquePersonList.sharesEmail(editedAlice));
    }

    // Checks against a different Person object with every field the same except email.
    // Returns true since different persons but same email field.
    @Test
    public void sharesEmail_personWithSameEmailOnlyInList_returnsTrue() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(uniquePersonList.sharesEmail(editedAlice));
    }

    // Checks against a different Person object with every field the same except email and id.
    // Returns false since same person after all.
    @Test
    public void sharesEmail_personWithSameEmailAndIdOnlyInList_returnsFalse() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
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
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(ALICE));
    }

    @Test
    public void add_personWithSameId_throwsDuplicatePersonException() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).build();
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(editedAlice));
    }

    @Test
    public void add_differentPersonWithSamePhoneNumber_throwsDuplicatePhoneNumberException() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniquePersonList.add(editedAlice));
    }

    @Test
    public void add_differentPersonWithSameEmail_throwsDuplicateEmailException() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertThrows(DuplicateEmailException.class, () -> uniquePersonList.add(editedAlice));
    }

    // first error accounted is that of duplicate person
    @Test
    public void add_PersonWithSameIdEmailPhone_throwsDuplicatePersonException() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(editedAlice));
    }

    // first error accounted is that of duplicate phone
    @Test
    public void add_PersonWithSameEmailPhone_throwsDuplicatePhoneNumberException() {
        uniquePersonList.add(BOB);
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertThrows(DuplicatePhoneNumberException.class, () -> uniquePersonList.add(editedAlice));
    }


    /** Tests editing of a person **/
    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentityOnly_success() {
        uniquePersonList.add(BOB);
        Person newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB)
                .build();
        uniquePersonList.setPerson(BOB, newBob);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(newBob);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentityAndEmailOnly_success() {
        uniquePersonList.add(BOB);
        Person newBob = new PersonBuilder(ALICE).withId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        uniquePersonList.setPerson(BOB, newBob);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(newBob);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonAlreadyExists_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(ALICE, BOB));
    }

    // edited person now has unique phone number that does not exist in SudoHR
    @Test
    public void setPerson_editedPersonNewPhoneIsUnique_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        Person newBob = new PersonBuilder().withPhone(VALID_PHONE_AMY)
                .build();
        uniquePersonList.setPerson(BOB, newBob);
    }

    // edited person now has phone number that already exists in SudoHR

    // edited person is different id but with a unique phone number

    // edited person is different id but with phone number that exists




    // edited person shares email with another

    // edited person shares same email and phone number with someone else


    /** Tests removal of a person **/
    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.remove(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    /** Testt setting of persons with a provided list **/
    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
