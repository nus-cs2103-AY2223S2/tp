package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Date;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new CompanyName("Apple"), new Role("iOS Developer"), new Status("applied"),
                new Date("2023-02-01"), getTagSet("front")),
            new Internship(new CompanyName("Amazon"), new Role("Cloud Architect"), new Status("new"),
                new Date("2023-02-02"), getTagSet("aws", "cloud services")),
            new Internship(new CompanyName("Google"), new Role("Software Engineer"), new Status("assessment"),
                new Date("2023-02-03"), getTagSet("golang", "back-end")),
            new Internship(new CompanyName("Samsung"), new Role("Android Developer"), new Status("interview"),
                new Date("2023-02-02"), getTagSet("android", "mobile")),
            new Internship(new CompanyName("Grab"), new Role("Front-end Designer"), new Status("offered"),
                new Date("2023-02-02"), getTagSet("react", "css")),
            new Internship(new CompanyName("Facebook"), new Role("Back-end Developer"), new Status("rejected"),
                new Date("2023-02-02"), getTagSet("sql"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleAb.addInternship(sampleInternship);
        }
        return sampleAb;
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
