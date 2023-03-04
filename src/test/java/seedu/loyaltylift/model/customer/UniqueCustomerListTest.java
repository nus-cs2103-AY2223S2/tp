package seedu.loyaltylift.model.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalPersons.ALICE;
import static seedu.loyaltylift.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.customer.exceptions.DuplicatePersonException;
import seedu.loyaltylift.model.customer.exceptions.PersonNotFoundException;
import seedu.loyaltylift.testutil.PersonBuilder;

public class UniqueCustomerListTest {

    private final UniqueCustomerList uniqueCustomerList = new UniqueCustomerList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueCustomerList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueCustomerList.add(ALICE);
        assertTrue(uniqueCustomerList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCustomerList.add(ALICE);
        Customer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueCustomerList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueCustomerList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueCustomerList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomer(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomer(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueCustomerList.setCustomer(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.setCustomer(ALICE, ALICE);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(ALICE);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueCustomerList.add(ALICE);
        Customer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueCustomerList.setCustomer(ALICE, editedAlice);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(editedAlice);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.setCustomer(ALICE, BOB);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(BOB);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueCustomerList.setCustomer(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueCustomerList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.remove(ALICE);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomers((UniqueCustomerList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueCustomerList.add(ALICE);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(BOB);
        uniqueCustomerList.setCustomers(expectedUniqueCustomerList);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setCustomers((List<Customer>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueCustomerList.add(ALICE);
        List<Customer> personList = Collections.singletonList(BOB);
        uniqueCustomerList.setCustomers(personList);
        UniqueCustomerList expectedUniqueCustomerList = new UniqueCustomerList();
        expectedUniqueCustomerList.add(BOB);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Customer> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueCustomerList.setCustomers(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCustomerList.asUnmodifiableObservableList().remove(0));
    }
}
