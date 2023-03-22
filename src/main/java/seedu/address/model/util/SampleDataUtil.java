package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.job.Company;
import seedu.address.model.job.Deadline;
import seedu.address.model.job.Email;
import seedu.address.model.job.Experience;
import seedu.address.model.job.JobDescription;
import seedu.address.model.job.Name;
import seedu.address.model.job.Phone;
import seedu.address.model.job.Role;
import seedu.address.model.job.Salary;
import seedu.address.model.job.Website;
import seedu.address.model.util.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Role[] getSampleRoles() {
        return new Role[] {
            new Role(new Name("SWE"), new Phone("87438807"), new Email("google@example.com"),
                new Company("Google"), new JobDescription("Penultimate year preferred"),
                getTagSet("Java", "Golang"), new Website("www.gohome.com"),
                new Salary("8000"), new Deadline(LocalDate.now().plusWeeks(3).toString()),
                new Experience("Javascript - 1 Year")),
            new Role(new Name("Data Analyst"), new Phone("99272758"), new Email("facebook@example.com"),
                new Company("Facebook"),
                new JobDescription("Open to non-CS majors"),
                getTagSet("FrontEnd", "React"), new Website("www.gohome.com"),
                new Salary("10000"), new Deadline(LocalDate.now().minusDays(3).toString()),
                new Experience("C - 1 Year")),
            new Role(new Name("Cloud Architect"), new Phone("93210283"), new Email("Amazon@example.com"),
                new Company("Amazon"),
                new JobDescription("Value contribution to open source"), getTagSet("AWS"),
                new Website("www.gohome.com"), new Salary("9000"),
                new Deadline(LocalDate.now().plusDays(30).toString()), new Experience("Python - 1 Year"))
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
