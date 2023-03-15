package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ExecutiveProDb;
import seedu.address.model.ReadOnlyExecutiveProDb;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ExecutiveProDb} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new EmployeeId(), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Department("Marketing"),
                getTagSet("friends")),
            new Employee(new Name("Bernice Yu"), new EmployeeId(), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Department("Marketing"),
                getTagSet("colleagues", "friends")),
            new Employee(new Name("Charlotte Oliveiro"), new EmployeeId(), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Department("Marketing"),
                getTagSet("neighbours")),
            new Employee(new Name("David Li"), new EmployeeId(), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Department("Sales"),
                getTagSet("family")),
            new Employee(new Name("Irfan Ibrahim"), new EmployeeId(), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Department("Sales"),
                getTagSet("classmates")),
            new Employee(new Name("Roy Balakrishnan"), new EmployeeId(), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Department("Sales"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyExecutiveProDb getSampleExecutiveProDb() {
        ExecutiveProDb sampleExecutiveProDb = new ExecutiveProDb();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleExecutiveProDb.addEmployee(sampleEmployee);
        }
        return sampleExecutiveProDb;
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
