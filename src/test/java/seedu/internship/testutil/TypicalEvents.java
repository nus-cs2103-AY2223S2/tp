package seedu.internship.testutil;

import static seedu.internship.testutil.TypicalInternships.ML1;
import static seedu.internship.testutil.TypicalInternships.ML2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internship.model.EventCatalogue;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    // Values declared below should be moved to static seedu.address.logic.commands.CommandTestUtil package
    public static final String VALID_NAME_EM11 = "Interview";
    public static final String VALID_START_EM11 = "04/04/2023 1500";
    public static final String VALID_END_EM11 = "04/04/2023 1800";
    public static final String VALID_EVENT_DESCRIPTION_EM11 = "This is the first Event for ML1.";
    public static final Internship VALID_INTERNSHIP_EM11 = new InternshipBuilder(ML1).build();

    public static final String VALID_NAME_EM12 = "HR Meeting";
    public static final String VALID_START_EM12 = "05/04/2023 1500";
    public static final String VALID_END_EM12 = "05/04/2023 1800";
    public static final String VALID_EVENT_DESCRIPTION_EM12 = "This is the second Event for ML1.";
    public static final Internship VALID_INTERNSHIP_EM12 = new InternshipBuilder(ML1).build();

    public static final String VALID_NAME_EMD1 = "Take Home Project";
    public static final String VALID_START_EMD1 = "06/04/2023 1500";
    public static final String VALID_END_EMD1 = "06/04/2023 1500";
    public static final String VALID_EVENT_DESCRIPTION_EMD1 = "This is the first Deadline for ML1.";
    public static final Internship VALID_INTERNSHIP_EMD1 = new InternshipBuilder(ML1).build();

    public static final Event EM11 = new EventBuilder()
            .withName(VALID_NAME_EM11)
            .withStart(VALID_START_EM11)
            .withEnd(VALID_END_EM11)
            .withDescription(VALID_EVENT_DESCRIPTION_EM11)
            .withInternship(VALID_INTERNSHIP_EM11).build();

    public static final Event EM12 = new EventBuilder()
            .withName(VALID_NAME_EM12)
            .withStart(VALID_START_EM12)
            .withEnd(VALID_END_EM12)
            .withDescription(VALID_EVENT_DESCRIPTION_EM12)
            .withInternship(VALID_INTERNSHIP_EM12).build();

    public static final Event EMD1 = new EventBuilder()
            .withName(VALID_NAME_EMD1)
            .withStart(VALID_START_EMD1)
            .withEnd(VALID_END_EMD1)
            .withDescription(VALID_EVENT_DESCRIPTION_EMD1)
            .withInternship(VALID_INTERNSHIP_EMD1).build();

    public static final Event EM21 = new EventBuilder(EM12)
            .withStart("05/04/2023 1200")
            .withEnd("05/04/2023 1400")
            .withDescription("This is the first Event for ML2.")
            .withInternship(new InternshipBuilder(ML2).build()).build();

    public static final Event EMD2 = new EventBuilder(EMD1)
            .withInternship(new InternshipBuilder(ML2).build())
            .withDescription("This is the first Deadline for ML2.").build();


    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code InternshipCatalogue} with all the typical internships.
     */
    public static EventCatalogue getTypicalEventCatalogue() {
        EventCatalogue ec = new EventCatalogue();
        for (Event event : getTypicalEvents()) {
            ec.addEvent(event);
        }
        return ec;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EM11, EM12, EMD1, EM21, EMD2));
    }
}
