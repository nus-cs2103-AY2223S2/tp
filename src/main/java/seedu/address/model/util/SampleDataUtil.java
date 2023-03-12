package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.job.Address;
import seedu.address.model.job.Email;
import seedu.address.model.job.Name;
import seedu.address.model.job.Phone;
import seedu.address.model.job.Role;
import seedu.address.model.job.Salary;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Role[] getSampleRoles() {
        return new Role[] {
            new Role(new Name("Google"), new Phone("87438807"), new Email("google@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("tech"), new Salary("8000")),
            new Role(new Name("facebook"), new Phone("99272758"), new Email("facebook@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("Tech"), new Salary("10000")),
            new Role(new Name("Amazon"), new Phone("93210283"), new Email("Amazon@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("Tech"), new Salary("9000"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Role sampleRole : getSampleRoles()) {
            sampleAb.addRole(sampleRole);
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
