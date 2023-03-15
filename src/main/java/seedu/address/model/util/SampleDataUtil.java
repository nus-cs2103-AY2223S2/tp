package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.job.Address;
import seedu.address.model.job.Deadline;
import seedu.address.model.job.Email;
import seedu.address.model.job.JobDescription;
import seedu.address.model.job.Name;
import seedu.address.model.job.Phone;
import seedu.address.model.job.Role;
import seedu.address.model.job.Salary;
import seedu.address.model.job.Experience;
import seedu.address.model.job.Website;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Role[] getSampleRoles() {
        return new Role[] {
            new Role(new Name("SWE at Google"), new Phone("87438807"), new Email("google@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new JobDescription("Penultimate year preferred"),
                getTagSet("Tech"), new Website("www.gohome.com"),
                    new Salary("8000"), new Deadline("2023-06-15"), new Experience("Javascript - 1 Year")),
            new Role(new Name("Data Analyst at Facebook"), new Phone("99272758"), new Email("facebook@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new JobDescription("Open to non-CS majors"),
                getTagSet("Tech"), new Website("www.gohome.com"),
                    new Salary("10000"), new Deadline("2023-05-05"), new Experience("C - 1 Year")),
            new Role(new Name("Cloud Architect at Amazon"), new Phone("93210283"), new Email("Amazon@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new JobDescription("Value contribution to open source"), getTagSet("Tech"),
                    new Website("www.gohome.com"),
                new Salary("9000"), new Deadline("2023-03-15"), new Experience("Python - 1 Year"))
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
