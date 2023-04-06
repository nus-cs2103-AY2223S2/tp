package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    public static final Event EVENT_1 = new OneTimeEvent(new Description("CS2101 Meeting"),
            new DateTime("2023-03-31 1600"), new DateTime("2023-03-31 1700"), Set.of(BOB, CARL));
    public static final Event EVENT_2 = new RecurringEvent(new Description("CS2103T Lecture"),
            new DateTime("2023-03-31 1400"), new DateTime("2023-03-31 1600"), new Recurrence(Recurrence.WEEKLY_CASE),
            Set.of(ALICE, BOB));
    public static final Event EVENT_3 = new RecurringEvent(new Description("Christmas Day"),
            new DateTime("2023-12-25 0000"), new DateTime("2023-12-25 2359"), new Recurrence(Recurrence.YEARLY_CASE),
            Set.of(BOB, CARL, DANIEL));
    public static final Event EVENT_4 = new RecurringEvent(new Description("Dinner with Olivia"),
            new DateTime("2023-03-31 1800"), new DateTime("2023-03-31 1900"), new Recurrence(Recurrence.DAILY_CASE),
            Set.of(CARL));
    public static final Event EVENT_5 = new RecurringEvent(new Description("Gym Session"),
            new DateTime("2023-03-31 0800"), new DateTime("2023-03-31 0900"), new Recurrence(Recurrence.DAILY_CASE),
            Set.of(DANIEL, ELLE));
}
