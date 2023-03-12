package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternshipCatalogue;
import seedu.address.model.ReadOnlyInternshipCatalogue;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Id;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Position;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternshipCatalogue} with sample data.
 */
public class SampleDataUtil1 {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
                new Internship(new Position("Machine Learning"), new Company("Tiktok"),
                        new Id("10"), new Status(0), new Description("This is a dummy internship"),
                        getTagSet("important")),
                new Internship(new Position("Software Engineer"), new Company("Grab"),
                        new Id("11"), new Status(1), new Description("This is a dummy internship"),
                        getTagSet("fun")),
                new Internship(new Position("Data Analytics"), new Company("Google"),
                        new Id("12"), new Status(2), new Description("This is a dummy internship"),
                        getTagSet("important")),
                new Internship(new Position("Machine Learning"), new Company("GovTech"),
                        new Id("13"), new Status(3), new Description("This is a dummy internship"),
                        getTagSet("important")),
                new Internship(new Position("Software Engineer"), new Company("GovTech"),
                        new Id("14"), new Status(3), new Description("This is a dummy internship"),
                        getTagSet("interesting")),
                new Internship(new Position("Software Testing"), new Company("Razor"),
                        new Id("15"), new Status(0), new Description("This is a dummy internship"),
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
