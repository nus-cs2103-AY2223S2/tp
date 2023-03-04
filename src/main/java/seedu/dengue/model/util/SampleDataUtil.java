package seedu.dengue.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
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
            new Person(new Name("Alex Yeoh"), new Postal("674388"), new Date("2000-11-11"),
                new Age("111"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Postal("992727"), new Date("2000-11-11"),
                new Age("11"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Postal("932283"),
                    new Date("2000-11-11 00:00"), new Age("0"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Postal("910312"), new Date("2000-11-11"),
                new Age("1"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Postal("924921"), new Date("2000-11-11"),
                new Age("199"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Postal("926247"), new Date("2000-11-11"),
                new Age("2"),
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
