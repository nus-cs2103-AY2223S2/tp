package seedu.dengue.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.DateAndTime;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.tag.Tag;

/**
 * Contains utility methods for populating {@code DengueHotspotTracker} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Postal("674388"), new DateAndTime("alexyeoh@example.com"),
                new Age("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Postal("992727"), new DateAndTime("berniceyu@example.com"),
                new Age("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Postal("932283"),
                    new DateAndTime("charlotte@example.com"), new Age("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Postal("910312"), new DateAndTime("lidavid@example.com"),
                new Age("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Postal("924921"), new DateAndTime("irfan@example.com"),
                new Age("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Postal("926247"), new DateAndTime("royb@example.com"),
                new Age("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyDengueHotspotTracker getSampleDengueHotspotTracker() {
        DengueHotspotTracker sampleAb = new DengueHotspotTracker();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
