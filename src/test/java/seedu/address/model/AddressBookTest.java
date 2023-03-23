package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTransactions.COFFEE_MACHINES_A;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.exceptions.DuplicateTransactionException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TransactionBuilder;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTransactions;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getTransactionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newPersonData = TypicalPersons.getTypicalAddressBook();
        addressBook.resetData(newPersonData);
        assertEquals(newPersonData, addressBook);

        AddressBook newTxnData = TypicalTransactions.getTypicalAddressBook();
        addressBook.resetData(newTxnData);
        assertEquals(newTxnData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Transaction> newTxns = Arrays.asList(COFFEE_MACHINES_A);
        AddressBookStub newData = new AddressBookStub(newPersons, newTxns);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTxns_throwsDuplicateTransactionException() {
        // Two persons with the same identity fields
        Transaction dupeCoffeeMachines = new TransactionBuilder(COFFEE_MACHINES_A).build();
        List<Person> newPersons = Arrays.asList(ALICE);
        List<Transaction> newTxns = Arrays.asList(COFFEE_MACHINES_A, dupeCoffeeMachines);
        AddressBookStub newData = new AddressBookStub(newPersons, newTxns);

        assertThrows(DuplicateTransactionException.class, () -> addressBook.resetData(newData));
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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasTxn_nullTxn_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTransaction(null));
    }

    @Test
    public void hasTxn_txnNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTransaction(COFFEE_MACHINES_A));
    }

    @Test
    public void hasTxn_txnInAddressBook_returnsTrue() {
        addressBook.addTransaction(COFFEE_MACHINES_A);
        assertTrue(addressBook.hasTransaction(COFFEE_MACHINES_A));
    }

    @Test
    public void getTxnList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTransactionList().remove(0));
    }



    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons,
                        Collection<Transaction> txns) {
            this.persons.setAll(persons);
            this.transactions.setAll(txns);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Transaction> getTransactionList() {
            return transactions;
        }
    }

}
