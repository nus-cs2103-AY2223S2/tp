package seedu.internship.model.util;

import java.time.LocalDateTime;
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
    // Sample Internships
    private static Internship internship1 = new Internship(new Position("Machine Learning"),
            new Company("Tiktok"), new Status(0),
            new Description("This is a dummy internship"), getTagSet("Important"));
    private static Internship internship2 = new Internship(new Position("Software Engineer"),
            new Company("Grab"), new Status(1),
            new Description("This is a dummy internship"), getTagSet());
    private static Internship internship3 = new Internship(new Position("Data Analytics"),
            new Company("Google"), new Status(2),
            new Description("This is a dummy internship"), getTagSet("Important"));
    private static Internship internship4 = new Internship(new Position("Machine Learning"),
            new Company("GovTech"), new Status(3),
            new Description("This is a dummy internship"), getTagSet("Important", "Priority"));
    private static Internship internship5 = new Internship(new Position("Software Engineer"),
            new Company("GovTech"), new Status(3),
            new Description("This is a dummy internship"), getTagSet("Interesting"));
    private static Internship internship6 = new Internship(new Position("Software Testing"),
            new Company("Razor"), new Status(0),
            new Description("This is a dummy internship"), getTagSet());
    private static Internship internship7 = new Internship(new Position("Software Testing"),
            new Company("GovTech"), new Status(1),
            new Description("This is a dummy internship"), getTagSet());



    public static Internship[] getSampleInternships() {
        return new Internship[] {
            internship1, internship2, internship3, internship4, internship5, internship6, internship7
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
            new Event(new Name("Resume Submission"),
                    new Start(LocalDateTime.parse("1/01/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("1/01/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via Talent Connect"),
                     internship1),
            new Event(new Name("Resume Submission"),
                    new Start(LocalDateTime.parse("1/01/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("1/01/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via Talent Connect"),
                     internship2),
            new Event(new Name("Technical Examination"),
                    new Start(LocalDateTime.parse("10/03/2023 1200", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("10/03/2023 1400", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("On Zoom"),
                    internship2),
            new Event(new Name("Technical Interview"),
                    new Start(LocalDateTime.parse("17/03/2023 1500", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("17/03/2023 1700", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("On Zoom"),
                    internship2),
            new Event(new Name("HR Interview"),
                    new Start(LocalDateTime.parse("25/04/2023 1500", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("25/04/2023 1700", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("On Zoom"),
                    internship2),
            new Event(new Name("Resume Submission"),
                    new Start(LocalDateTime.parse("1/01/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("1/01/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via Talent Connect"),
                    internship3),
            new Event(new Name("Technical Interview"),
                    new Start(LocalDateTime.parse("11/02/2023 1500", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("11/02/2023 1700", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Venue TBC"),
                     internship3),
            new Event(new Name("HR Interview"),
                    new Start(LocalDateTime.parse("16/03/2023 1500", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("16/03/2023 1700", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("On Zoom"),
                     internship3),
            new Event(new Name("Offer Confirmation Submission"),
                    new Start(LocalDateTime.parse("25/04/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("25/04/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via Email"),
                    internship3),
            new Event(new Name("Resume Submission"),
                    new Start(LocalDateTime.parse("01/01/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("01/01/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via Email"),
                    internship4),
            new Event(new Name("Resume Submission"),
                    new Start(LocalDateTime.parse("01/02/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("01/02/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via TalentConnect"),
                    internship5),
            new Event(new Name("Resume Submission"),
                    new Start(LocalDateTime.parse("01/02/2023 2359", Start.NUMERIC_DATE_TIME_FORMATTER)),
                    new End(LocalDateTime.parse("01/02/2023 2359", End.NUMERIC_DATE_TIME_FORMATTER)),
                    new EventDescription("Via TalentConnect"),
                    internship6)
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
