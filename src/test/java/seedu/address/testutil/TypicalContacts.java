package seedu.address.testutil;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.contact.Contact;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalContacts {

    public static final Contact COMPANY_A = new ContactBuilder()
            .withPhone(CommandTestUtil.VALID_PHONE_COMPANY_A)
            .withEmail(CommandTestUtil.VALID_EMAIL_COMPANY_A)
            .build();

    public static final Contact COMPANY_B = new ContactBuilder()
            .withPhone(CommandTestUtil.VALID_PHONE_COMPANY_B)
            .withEmail(CommandTestUtil.VALID_EMAIL_COMPANY_B)
            .build();
    private TypicalContacts() {} // prevents instantiation
}
