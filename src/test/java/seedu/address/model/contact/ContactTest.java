package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_META;
import static seedu.address.testutil.TypicalContacts.BANK_OF_AMERICA_CONTACT;
import static seedu.address.testutil.TypicalContacts.META_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

public class ContactTest {

    @Test
    public void equals() {
        // same values -> returns true
        Contact companyACopy = new ContactBuilder(BANK_OF_AMERICA_CONTACT).build();
        assertEquals(BANK_OF_AMERICA_CONTACT, companyACopy);

        // same object -> returns true
        assertEquals(BANK_OF_AMERICA_CONTACT, BANK_OF_AMERICA_CONTACT);

        // null -> returns false
        assertNotEquals(null, BANK_OF_AMERICA_CONTACT);

        // different type -> returns false
        assertNotEquals(5, BANK_OF_AMERICA_CONTACT);

        // different contact -> returns false
        assertNotEquals(BANK_OF_AMERICA_CONTACT, META_CONTACT);

        // different phone -> returns false
        Contact editedContact = new ContactBuilder(BANK_OF_AMERICA_CONTACT).withPhone(VALID_PHONE_META).build();
        assertNotEquals(BANK_OF_AMERICA_CONTACT, editedContact);

        // different email -> returns false
        editedContact = new ContactBuilder(BANK_OF_AMERICA_CONTACT).withEmail(VALID_EMAIL_META).build();
        assertNotEquals(BANK_OF_AMERICA_CONTACT, editedContact);
    }
}
