package seedu.address.testutil;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.contact.Contact;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact BANK_OF_AMERICA_CONTACT = new ContactBuilder()
            .withPhone(CommandTestUtil.VALID_PHONE_BANK_OF_AMERICA)
            .withEmail(CommandTestUtil.VALID_EMAIL_BANK_OF_AMERICA)
            .build();

    public static final Contact META_CONTACT = new ContactBuilder()
            .withPhone(CommandTestUtil.VALID_PHONE_META)
            .withEmail(CommandTestUtil.VALID_EMAIL_META)
            .build();
    private TypicalContacts() {} // prevents instantiation
}
