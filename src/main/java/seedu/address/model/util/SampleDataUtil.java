package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.Parents;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Returns a sample PCClass.
     * @return a sample PCClass.
     */
    public static ReadOnlyPcClass getSamplePcClass() {
        PcClass sampleClasses = new PcClass();
        return sampleClasses;
    }

    /**
     * Returns a sample parents list.
     * @return a sample parents list.
     */
    public static ReadOnlyParents getSampleParents() {
        Parents sampleParents = new Parents();
        return sampleParents;
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
