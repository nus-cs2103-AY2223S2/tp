package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.getTypicalContactBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.ContactBuilder;

public class ContactBookTest {

    private final ContactBook contactBook = new ContactBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactBook.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactBook_replacesData() {
        ContactBook newData = getTypicalContactBook();
        contactBook.resetData(newData);
        assertEquals(newData, contactBook);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two persons with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        ContactBookStub newData = new ContactBookStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> contactBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactBook.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInContactBook_returnsFalse() {
        assertFalse(contactBook.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactBook_returnsTrue() {
        contactBook.addContact(ALICE);
        assertTrue(contactBook.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInContactBook_returnsTrue() {
        contactBook.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE)
                .build();
        assertTrue(contactBook.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> contactBook.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ContactBookStub implements ReadOnlyContactBook {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        ContactBookStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }
    }

}
