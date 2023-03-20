package seedu.patientist.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Patientist} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Patient(new Email("alexyeoh@example.com"), new Name("Alex Yeoh"), new Phone("87438807"),
                    new IdNumber("A12345A"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Patient(new Email("berniceyu@example.com"), new Name("Bernice Yu"), new Phone("99272758"),
                    new IdNumber("A12345B"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Patient(new Email("charlotte@example.com"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new IdNumber("A12345C"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Patient(new Email("lidavid@example.com"), new Name("David Li"), new Phone("91031282"),
                    new IdNumber("A12345D"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Patient(new Email("irfan@example.com"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new IdNumber("A12345E"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Patient(new Email("royb@example.com"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new IdNumber("A12345F"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyPatientist getSamplePatientist() {
        Patientist sampleAb = new Patientist();
        for (Person samplePerson : getSamplePersons()) {
            //sampleAb.addPerson(samplePerson); TODO: change implementation of sample
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
