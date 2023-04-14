package seedu.sudohr.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.employee.Address;
import seedu.sudohr.model.employee.Email;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.Name;
import seedu.sudohr.model.employee.Phone;
import seedu.sudohr.model.tag.Tag;

/**
 * Contains utility methods for populating SudoHR with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Id("1"), new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Employee(new Id("2"), new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Employee(new Id("3"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Employee(new Id("4"), new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Employee(new Id("5"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Employee(new Id("6"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues")),
            new Employee(new Id("7"), new Name("TheMythDaMith"), new Phone("98987676"),
                    new Email("themyth@example.com"), new Address("COM2 NUS 85, #03-10"),
                    getTagSet("professor"))
        };
    }

    public static ReadOnlySudoHr getSampleSudoHr() {
        SudoHr sudoHr = new SudoHr();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sudoHr.addEmployee(sampleEmployee);
        }
        return sudoHr;
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
