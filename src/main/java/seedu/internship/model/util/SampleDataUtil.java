package seedu.internship.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internship.model.EventCatalogue;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.ReadOnlyEventCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.event.End;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventDescription;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Description;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternshipCatalogue} with sample data.
 */
public class SampleDataUtil {
    // Sample Data 1
    static private Position position_1 = new Position("Machine Learning");
    static private Company company_1 = new Company("Tiktok");
    static private Status status_1 = new Status(0);
    static private Description description_1 = new Description("This is a dummy internship");
    static private Set<Tag> tags = getTagSet("important");

    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(position_1, company_1, status_1, description_1, tags),
            new Internship(new Position("Software Engineer"), new Company("Grab"),
                        new Status(1), new Description("This is a dummy internship"),
                        getTagSet("fun")),
            new Internship(new Position("Data Analytics"), new Company("Google"),
                        new Status(2), new Description("This is a dummy internship"),
                        getTagSet("important")),
            new Internship(new Position("Machine Learning"), new Company("GovTech"),
                        new Status(3), new Description("This is a dummy internship"),
                        getTagSet("important")),
            new Internship(new Position("Software Engineer"), new Company("GovTech"),
                        new Status(3), new Description("This is a dummy internship"),
                        getTagSet("interesting")),
            new Internship(new Position("Software Testing"), new Company("Razor"),
                        new Status(0), new Description("This is a dummy internship"),
                        getTagSet("fun")),
        };
    }

    public static ReadOnlyInternshipCatalogue getSampleInternshipCatalogue() {
        InternshipCatalogue sampleIc = new InternshipCatalogue();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleIc.addInternship(sampleInternship);
        }
        return sampleIc;
    }

    public static Event[] getSampleEvents() {
        return new Event[]{
            new Event(new Name("Technical Interview"), new Start("10/09/2023 1500"),
                    new End("10/09/2023 1700"), new EventDescription("On Zoom"),
                    new Internship(position_1, company_1, status_1, description_1, tags)),
            new Event(new Name("HR Interview"), new Start("15/09/2023 1500"),
                    new End("15/09/2023 1700"), new EventDescription("On Zoom"),
                    new Internship(position_1, company_1, status_1, description_1, tags))
        };
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyEventCatalogue getSampleEventCatalogue() {
        EventCatalogue sampleEvents = new EventCatalogue();
        for (Event sampleEvent : getSampleEvents()) {
            sampleEvents.addEvent(sampleEvent);
        }
        return sampleEvents;
    }
}
