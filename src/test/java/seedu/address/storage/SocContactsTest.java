package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.SocContacts.SOC_CONTACTS;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;


public class SocContactsTest {
    @Test
    public void equals() {
        Person benLeong = SOC_CONTACTS.get(0);
        Person stevenHalim = SOC_CONTACTS.get(1);

        // same object -> returns true
        assertTrue(benLeong.isSamePerson(benLeong));

        // different person -> returns false
        assertFalse(benLeong.isSamePerson(stevenHalim));
    }
}
