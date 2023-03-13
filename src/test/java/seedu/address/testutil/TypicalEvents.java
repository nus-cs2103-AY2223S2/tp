package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event WEDDING_DINNER = new EventBuilder().withName("Wedding Dinner")
            .withStartDateTime("01-01-2024 19:00")
            .withEndDateTime("01-01-2024 22:00").build();
    public static final Event CARNIVAL = new EventBuilder().withName("Carnival")
            .withStartDateTime("17-07-2023 12:00")
            .withEndDateTime("21-07-2023 22:00").build();
    public static final Event SPORTS_DAY = new EventBuilder().withName("Company ABC Sports Day")
            .withStartDateTime("03-03-2024 09:00")
            .withEndDateTime("03-03-2024 18:00").build();
    public static final Event BIRTHDAY_PARTY = new EventBuilder().withName("CEO's 50th birthday party")
            .withStartDateTime("03-08-2023 15:00")
            .withEndDateTime("03-08-2023 21:00").build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(WEDDING_DINNER, CARNIVAL, SPORTS_DAY, BIRTHDAY_PARTY));
    }
}
