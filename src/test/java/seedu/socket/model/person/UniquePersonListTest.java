package seedu.socket.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.socket.model.person.Person.CATEGORY_ADDRESS;
import static seedu.socket.model.person.Person.CATEGORY_EMAIL;
import static seedu.socket.model.person.Person.CATEGORY_GITHUB;
import static seedu.socket.model.person.Person.CATEGORY_NAME;
import static seedu.socket.model.person.Person.CATEGORY_PHONE;
import static seedu.socket.testutil.Assert.assertThrows;
import static seedu.socket.testutil.TypicalPersons.ALICE;
import static seedu.socket.testutil.TypicalPersons.BENSON;
import static seedu.socket.testutil.TypicalPersons.BOB;
import static seedu.socket.testutil.TypicalPersons.CARL;
import static seedu.socket.testutil.TypicalPersons.EMPTY;
import static seedu.socket.testutil.TypicalPersons.EMPTY_TWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.socket.model.person.exceptions.DuplicatePersonException;
import seedu.socket.model.person.exceptions.PersonNotFoundException;
import seedu.socket.testutil.PersonBuilder;

public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

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

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

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
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePersonList.setPerson(ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
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
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(ALICE, BOB));
    }

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

    @Test
    public void removeAll_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.removeAll(null));
    }

    @Test
    public void removeAll_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.removeAll(x -> x.isSamePerson(ALICE));
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

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

    @Test
    public void sort_name() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(EMPTY_TWO);
        uniquePersonList.add(EMPTY);
        uniquePersonList.add(CARL);
        uniquePersonList.add(BENSON);
        UniquePersonList sortByNameList = new UniquePersonList();
        sortByNameList.add(ALICE);
        sortByNameList.add(BENSON);
        sortByNameList.add(CARL);
        sortByNameList.add(EMPTY);
        sortByNameList.add(EMPTY_TWO);
        uniquePersonList.sort(CATEGORY_NAME);
        assertEquals(uniquePersonList, sortByNameList);
    }
    @Test
    public void sort_phone() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(EMPTY_TWO);
        uniquePersonList.add(EMPTY);
        uniquePersonList.add(CARL);
        uniquePersonList.add(BENSON);
        UniquePersonList sortByPhoneList = new UniquePersonList();
        sortByPhoneList.add(ALICE);
        sortByPhoneList.add(CARL);
        sortByPhoneList.add(BENSON);
        sortByPhoneList.add(EMPTY);
        sortByPhoneList.add((EMPTY_TWO));
        uniquePersonList.sort(CATEGORY_PHONE);
        assertEquals(uniquePersonList, sortByPhoneList);
    }
    @Test
    public void sort_email() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(CARL);
        uniquePersonList.add(EMPTY_TWO);
        uniquePersonList.add(EMPTY);
        uniquePersonList.add(BENSON);
        UniquePersonList sortByEmailList = new UniquePersonList();
        sortByEmailList.add(ALICE);
        sortByEmailList.add(CARL);
        sortByEmailList.add(BENSON);
        sortByEmailList.add(EMPTY);
        sortByEmailList.add(EMPTY_TWO);
        uniquePersonList.sort(CATEGORY_EMAIL);
        assertEquals(uniquePersonList, sortByEmailList);
    }
    @Test
    public void sort_address() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(CARL);
        uniquePersonList.add(EMPTY_TWO);
        uniquePersonList.add(EMPTY);
        uniquePersonList.add(BENSON);
        UniquePersonList sortByAddressList = new UniquePersonList();
        sortByAddressList.add(ALICE);
        sortByAddressList.add(BENSON);
        sortByAddressList.add(CARL);
        sortByAddressList.add(EMPTY);
        sortByAddressList.add(EMPTY_TWO);
        uniquePersonList.sort(CATEGORY_ADDRESS);
        assertEquals(uniquePersonList, sortByAddressList);
    }

    @Test
    public void sort_profile() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(CARL);
        uniquePersonList.add(EMPTY_TWO);
        uniquePersonList.add(EMPTY);
        uniquePersonList.add(BENSON);
        UniquePersonList sortByProfileList = new UniquePersonList();
        sortByProfileList.add(ALICE);
        sortByProfileList.add(BENSON);
        sortByProfileList.add(CARL);
        sortByProfileList.add(EMPTY);
        sortByProfileList.add(EMPTY_TWO);
        uniquePersonList.sort(CATEGORY_GITHUB);
        assertEquals(uniquePersonList, sortByProfileList);
    }
}
