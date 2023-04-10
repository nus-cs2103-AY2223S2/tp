package seedu.event.testutil;

import static seedu.event.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.event.model.ContactList;
import seedu.event.model.contact.Contact;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalContacts {
    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
            .withPhone("94351253")
            .build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
            .withPhone("98765432")
            .build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz").withPhone("95352563")
            .build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier").withPhone("87652533")
            .build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer").withPhone("94822241")
            .build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz").withPhone("948242712")
            .build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best").withPhone("94824421")
            .build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier").withPhone("848242412")
            .build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller").withPhone("84821311")
            .build();

    // Manually added - Contact's details found in {@code CommandTestUtil}
    public static final Contact AMY = new ContactBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .build();
    public static final Contact BOB = new ContactBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalContacts() {} // prevents instantiation

    /**
     * Returns an {@code ContactList} with all the typical Contacts.
     */
    public static ContactList getTypicalContactList() {
        ContactList cl = new ContactList();
        for (Contact contact : getTypicalContacts()) {
            cl.addContact(contact);
        }
        return cl;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
