package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.ChsContacts.CHS_CONTACTS;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class ChsContactsTest {
    @Test
    public void equals() {
        Person chngShuSin = CHS_CONTACTS.get(0);
        Person chewFookTim = CHS_CONTACTS.get(1);

        // same object -> returns true
        assertTrue(chngShuSin.isSamePerson(chngShuSin));

        // different person -> returns false
        assertFalse(chngShuSin.isSamePerson(chewFookTim));
    }
}
