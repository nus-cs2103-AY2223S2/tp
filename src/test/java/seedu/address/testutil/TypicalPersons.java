package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.entity.Entity;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Entity ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTags("friends").build();
    public static final Entity BENSON = new PersonBuilder().withName("Benson Meier")
            .withTags("owesMoney", "friends").build();
    public static final Entity CARL = new PersonBuilder().withName("Carl Kurz").build();
    public static final Entity DANIEL = new PersonBuilder().withName("Daniel Meier").withTags("friends").build();
    public static final Entity ELLE = new PersonBuilder().withName("Elle Meyer").build();
    public static final Entity FIONA = new PersonBuilder().withName("Fiona Kunz").build();
    public static final Entity GEORGE = new PersonBuilder().withName("George Best").build();

    // Manually added
    public static final Entity HOON = new PersonBuilder().withName("Hoon Meier").build();
    public static final Entity IDA = new PersonBuilder().withName("Ida Mueller").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Entity AMY = new PersonBuilder().withName(VALID_NAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Entity BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Entity entity : getTypicalPersons()) {
            ab.addPerson(entity);
        }
        return ab;
    }

    public static List<Entity> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
