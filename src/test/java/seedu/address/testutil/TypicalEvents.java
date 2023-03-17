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

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ALICE = new EventBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withRate("94351253")
            .withStartTime("11-12-2023 02:00")
            .withEndTime("11-12-2023 10:00")
            .withMark("[X]")
            .withTags("friends").build();
    public static final Event BENSON = new EventBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withRate("98765432")
            .withStartTime("11-03-2024 12:00")
            .withEndTime("11-03-2024 17:00")
            .withMark("[X]")
            .withContact("Meier", "91239123")
            .withTags("owesMoney", "friends").build();
    public static final Event CARL = new EventBuilder().withName("Carl Kurz")
            .withRate("95352563")
            .withStartTime("25-01-2023 12:00")
            .withEndTime("26-01-2024 12:00")
            .withMark("[ ]")
            .withAddress("wall street").build();
    public static final Event DANIEL = new EventBuilder().withName("Daniel Meier")
            .withRate("87652533")
            .withStartTime("11-03-2024 12:00")
            .withEndTime("11-03-2024 13:00")
            .withMark("[X]")
            .withAddress("10th street").withTags("friends").build();
    public static final Event ELLE = new EventBuilder().withName("Elle Meyer")
            .withRate("9482224")
            .withStartTime("11-03-2025 12:00")
            .withEndTime("11-03-2025 17:00")
            .withMark("[X]")
            .withAddress("michegan ave").build();
    public static final Event FIONA = new EventBuilder().withName("Fiona Kunz")
            .withRate("9482427")
            .withStartTime("01-01-2024 12:30")
            .withEndTime("02-01-2024 02:15")
            .withMark("[ ]")
            .withAddress("little tokyo").build();
    public static final Event GEORGE = new EventBuilder().withName("George Best")
            .withRate("9482442")
            .withStartTime("11-03-2023 23:20")
            .withEndTime("12-03-2023 00:00")
            .withMark("[ ]")
            .withAddress("4th street").build();

    // Manually added
    public static final Event HOON = new EventBuilder().withName("Hoon Meier").withRate("8482424")
            .withStartTime("01-08-2023 04:50")
            .withEndTime("01-08-2023 08:25")
            .withMark("[ ]")
            .withAddress("little india").build();
    public static final Event IDA = new EventBuilder().withName("Ida Mueller").withRate("8482131")
            .withStartTime("12-11-2023 13:30")
            .withEndTime("12-11-2023 16:25")
            .withMark("[X]")
            .withAddress("chicago ave").build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event AMY = new EventBuilder().withName(VALID_NAME_AMY).withRate(VALID_PHONE_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withStartTime(VALID_START_TIME_AMY)
            .withEndTime(VALID_END_TIME_AMY)
            .withMark("[ ]")
            .withTags(VALID_TAG_FRIEND).build();
    public static final Event BOB = new EventBuilder().withName(VALID_NAME_BOB).withRate(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withStartTime(VALID_START_TIME_BOB)
            .withEndTime(VALID_END_TIME_BOB)
            .withMark("[X]")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook ab = new EventBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
