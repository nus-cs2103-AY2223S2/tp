package seedu.internship.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
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
    public static Internship[] getSampleInternships() {
        return new Internship[] {
                new Internship(new Position("Machine Learning"), new Company("Tiktok"),
                        new Status(0), new Description("This is a dummy internship"),
                        getTagSet("important")),
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

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
