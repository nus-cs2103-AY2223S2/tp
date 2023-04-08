package seedu.techtrack.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.JobDescription;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.model.role.Salary;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;

/**
 * Contains utility methods for populating {@code RoleBook} with sample data.
 */
public class SampleDataUtil {

    public static Role[] getSampleRoles() {
        return new Role[] {
            new Role(new Name("SWE"), new Contact("87438807"), new Email("google@example.com"),
                new Company("Google"), new JobDescription("Penultimate year preferred"),
                getTagSet("Java", "Golang"), new Website("www.gohome.com"),
                new Salary("8000"), new Deadline(LocalDate.now().plusWeeks(3).toString()),
                new Experience("Javascript - 1 Year")),
            new Role(new Name("Data Analyst"), new Contact("99272758"), new Email("facebook@example.com"),
                new Company("Facebook"),
                new JobDescription("Open to non-CS majors"),
                getTagSet("FrontEnd", "React"), new Website("www.gohome.com"),
                new Salary("10000"), new Deadline(LocalDate.now().minusDays(3).toString()),
                new Experience("C - 1 Year")),
            new Role(new Name("Cloud Architect"), new Contact("93210283"), new Email("Amazon@example.com"),
                new Company("Amazon"),
                new JobDescription("Value contribution to open source"), getTagSet("AWS"),
                new Website("www.gohome.com"), new Salary("9000"),
                new Deadline(LocalDate.now().plusDays(30).toString()), new Experience("Python - 1 Year"))
        };
    }

    public static ReadOnlyRoleBook getSampleRoleBook() {
        RoleBook sampleAb = new RoleBook();
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
