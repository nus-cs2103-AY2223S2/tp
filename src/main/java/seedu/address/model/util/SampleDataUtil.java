package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternBuddy;
import seedu.address.model.ReadOnlyInternBuddy;
import seedu.address.model.internship.Comment;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Date;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternBuddy} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new CompanyName("Apple"), new Role("iOS Developer"), new Status("applied"),
                new Date("2023-02-01"), new Comment("My dream company!"), getTagSet("front")),
            new Internship(new CompanyName("Amazon"), new Role("Cloud Architect"), new Status("new"),
                new Date("2023-02-02"), new Comment("Need to research more on cloud technology."),
                    getTagSet("aws", "cloud services")),
            new Internship(new CompanyName("Google"), new Role("Software Engineer"), new Status("assessment"),
                new Date("2023-02-03"), new Comment("Good company culture"),
                    getTagSet("golang", "backend")),
            new Internship(new CompanyName("Samsung"), new Role("Android Developer"), new Status("interview"),
                new Date("2023-02-02"), new Comment("To compare with Apple again."),
                    getTagSet("android", "mobile")),
            new Internship(new CompanyName("Grab"), new Role("Frontend Designer"), new Status("offered"),
                new Date("2023-02-02"), new Comment("Good benefits."),
                    getTagSet("react", "css")),
            new Internship(new CompanyName("Facebook"), new Role("Backend Developer"), new Status("rejected"),
                new Date("2023-02-02"), new Comment("Rejected because I lack proficiency in SQL."),
                    getTagSet("sql")),
            new Internship(new CompanyName("Paypal"), new Role("Product Manager"), new Status("Accepted"),
                    new Date("2023-04-05"), new Comment("Starting work on 1 May."),
                    getTagSet("UI", "UX"))
        };
    }

    public static ReadOnlyInternBuddy getSampleInternBuddy() {
        InternBuddy sampleAb = new InternBuddy();
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
