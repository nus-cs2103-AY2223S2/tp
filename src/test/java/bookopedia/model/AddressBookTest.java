package bookopedia.model;

import static bookopedia.logic.commands.CommandTestUtil.VALID_PARCEL_LAZADA;
import static bookopedia.testutil.Assert.assertThrows;
import static bookopedia.testutil.TypicalPersons.ALICE;
import static bookopedia.testutil.TypicalPersons.BENSON;
import static bookopedia.testutil.TypicalPersons.CARL;
import static bookopedia.testutil.TypicalPersons.DANIEL;
import static bookopedia.testutil.TypicalPersons.ELLE;
import static bookopedia.testutil.TypicalPersons.FIONA;
import static bookopedia.testutil.TypicalPersons.GEORGE;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;
import static bookopedia.testutil.TypicalPersons.getTypicalPersons;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import bookopedia.model.person.Person;
import bookopedia.model.person.exceptions.DuplicatePersonException;
import bookopedia.testutil.PersonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(ALICE.getAddress().toString())
                .withParcels(VALID_PARCEL_LAZADA).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(ALICE.getAddress().toString())
                .withParcels(VALID_PARCEL_LAZADA).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void sortAddressBook_success() {
        addressBook.setPersons(getTypicalPersons());
        addressBook.sort();
        assertEquals(addressBook.getPersonList(), Arrays.asList(DANIEL, ELLE, FIONA, BENSON, CARL, ALICE, GEORGE));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
