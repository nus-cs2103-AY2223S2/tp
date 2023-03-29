package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BANK_OF_AMERICA_CONTACT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_META_CONTACT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_META;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_META;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditContactDescriptorBuilder;

public class EditContactDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditContactCommand.EditContactDescriptor descriptorWithSameValues =
                new EditContactCommand.EditContactDescriptor(DESC_BANK_OF_AMERICA_CONTACT);
        assertEquals(DESC_BANK_OF_AMERICA_CONTACT, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_BANK_OF_AMERICA_CONTACT, DESC_BANK_OF_AMERICA_CONTACT);

        // null -> returns false
        assertNotEquals(null, DESC_BANK_OF_AMERICA_CONTACT);

        // different types -> returns false
        assertNotEquals(5, DESC_BANK_OF_AMERICA_CONTACT);

        // different values -> returns false
        assertNotEquals(DESC_BANK_OF_AMERICA_CONTACT, DESC_META_CONTACT);

        // different phone -> returns false
        EditContactCommand.EditContactDescriptor editedGoogleDocuments =
                new EditContactDescriptorBuilder(DESC_BANK_OF_AMERICA_CONTACT)
                        .withPhone(VALID_PHONE_META).build();
        assertNotEquals(DESC_BANK_OF_AMERICA_CONTACT, editedGoogleDocuments);

        // different email -> returns false
        editedGoogleDocuments = new EditContactDescriptorBuilder(DESC_BANK_OF_AMERICA_CONTACT)
                .withEmail(VALID_EMAIL_META).build();
        assertNotEquals(DESC_BANK_OF_AMERICA_CONTACT, editedGoogleDocuments);
    }
}
