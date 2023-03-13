package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Event;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Event ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withRate("94351253")
            .withTiming("11-12-2023 02:00", "11-12-2023 10:00")
            .withMark("[X]")
            .withTags("friends").build();
    public static final Event BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withRate("98765432")
            .withTiming("11-03-2024 12:00", "11-03-2024 17:00")
            .withMark("[X]")
            .withTags("owesMoney", "friends").build();
    public static final Event CARL = new PersonBuilder().withName("Carl Kurz")
            .withRate("95352563")
            .withTiming("25-01-2023 12:00", "26-01-2024 12:00")
            .withMark("[ ]")
            .withAddress("wall street").build();
    public static final Event DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withRate("87652533")
            .withTiming("11-03-2024 12:00", "11-03-2024 13:00")
            .withMark("[X]")
            .withAddress("10th street").withTags("friends").build();
    public static final Event ELLE = new PersonBuilder().withName("Elle Meyer")
            .withRate("9482224")
            .withTiming("11-03-2025 12:00", "11-03-2025 17:00")
            .withMark("[X]")
            .withAddress("michegan ave").build();
    public static final Event FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withRate("9482427")
            .withTiming("01-01-2024 12:30", "02-01-2024 02:15")
            .withMark("[ ]")
            .withAddress("little tokyo").build();
    public static final Event GEORGE = new PersonBuilder().withName("George Best")
            .withRate("9482442")
            .withTiming("11-03-2023 23:20", "12-03-2023 00:00")
            .withMark("[ ]")
            .withAddress("4th street").build();

    // Manually added
    public static final Event HOON = new PersonBuilder().withName("Hoon Meier").withRate("8482424")
            .withTiming("01-08-2023 04:50", "01-08-2023 08:25")
            .withMark("[ ]")
            .withAddress("little india").build();
    public static final Event IDA = new PersonBuilder().withName("Ida Mueller").withRate("8482131")
            .withTiming("12-11-2023 13:30", "12-11-2023 16:25")
            .withMark("[X]")
            .withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Event AMY = new PersonBuilder().withName(VALID_NAME_AMY).withRate(VALID_PHONE_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTiming(VALID_START_TIME_AMY, VALID_END_TIME_AMY)
            .withMark("[ ]")
            .withTags(VALID_TAG_FRIEND).build();
    public static final Event BOB = new PersonBuilder().withName(VALID_NAME_BOB).withRate(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withTiming(VALID_START_TIME_BOB, VALID_END_TIME_BOB)
            .withMark("[X]")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Event> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
