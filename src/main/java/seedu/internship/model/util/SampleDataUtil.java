package seedu.internship.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internship.model.InternBuddy;
import seedu.internship.model.ReadOnlyInternBuddy;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;
import seedu.internship.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternBuddy} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new CompanyName("Apple"), new Role("iOS Developer"), new Status("applied"),
                new Date("2023-03-20"), new Comment("Yay! My dream company!"), getTagSet("iOS")),
            new Internship(new CompanyName("Amazon"), new Role("Cloud Architect"), new Status("new"),
                new Date("2023-03-28"), new Comment("Need to research more on cloud services."),
                    getTagSet("AWS", "Cloud Services")),
            new Internship(new CompanyName("Google"), new Role("Software Engineer"), new Status("assessment"),
                new Date("2023-04-02"), new Comment("Good company culture and environment."),
                    getTagSet("Golang", "Back-end")),
            new Internship(new CompanyName("Samsung"), new Role("Android Developer"), new Status("interview"),
                new Date("2023-04-10"), new Comment("To compare with Apple's offer again."),
                    getTagSet("Android", "Mobile")),
            new Internship(new CompanyName("Grab"), new Role("Frontend Designer"), new Status("offered"),
                new Date("2023-03-27"), new Comment("Good benefits. Can consider."),
                    getTagSet("React", "CSS")),
            new Internship(new CompanyName("Paypal"), new Role("Product Designer"), new Status("Accepted"),
                new Date("2023-03-26"), new Comment("Starting work on 1 May. Excited!"),
                    getTagSet("UI", "UX")),
            new Internship(new CompanyName("Facebook"), new Role("Backend Developer"), new Status("rejected"),
                new Date("2023-03-15"), new Comment("Rejected since I lack proficiency in SQL."),
                    getTagSet("SQL")),
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
