package ezschedule.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ezschedule.logic.commands.CommandTestUtil;
import ezschedule.model.Scheduler;
import ezschedule.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ART = new EventBuilder().withName("Art class")
            .withDate("2023-05-01").withStartTime("12:00").withEndTime("14:00").build();
    public static final Event BOAT = new EventBuilder().withName("Boat race")
            .withDate("2023-05-02").withStartTime("14:00").withEndTime("16:00").build();
    public static final Event CARNIVAL = new EventBuilder().withName("Carnival")
            .withDate("2023-05-03").withStartTime("16:00").withEndTime("18:00").build();
    public static final Event DRAG = new EventBuilder().withName("Drag race")
            .withDate("2023-05-04").withStartTime("18:00").withEndTime("20:00").build();

    // Manually added
    public static final Event EAT = new EventBuilder().withName("Eating contest")
            .withDate("2023-05-05").withStartTime("12:00").withEndTime("13:00").build();
    public static final Event FISHING = new EventBuilder().withName("Fishing contest")
            .withDate("2023-05-06").withStartTime("14:00").withEndTime("16:00").build();
    public static final Event OVERLAP_ART_EVENT = new EventBuilder().withName("Golfing")
            .withDate("2023-05-01").withStartTime("13:00").withEndTime("15:00").build();


    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event EVENT_A = new EventBuilder().withName(CommandTestUtil.VALID_NAME_A)
            .withDate(CommandTestUtil.VALID_DATE_A).withStartTime(CommandTestUtil.VALID_START_TIME_A)
            .withEndTime(CommandTestUtil.VALID_END_TIME_A).build();
    public static final Event EVENT_B = new EventBuilder().withName(CommandTestUtil.VALID_NAME_B)
            .withDate(CommandTestUtil.VALID_DATE_B).withStartTime(CommandTestUtil.VALID_START_TIME_B)
            .withEndTime(CommandTestUtil.VALID_END_TIME_B).build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code Scheduler} with all the typical events.
     */
    public static Scheduler getTypicalScheduler() {
        Scheduler sc = new Scheduler();
        for (Event event : getTypicalEvents()) {
            sc.addEvent(event);
        }
        return sc;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ART, BOAT, CARNIVAL, DRAG));
    }
}
