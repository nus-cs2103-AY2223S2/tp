package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.medicine.Medicine;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Nric("S1234567A"), new Name("Alex Yeoh"), new DateOfBirth("01/04/2000"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new DrugAllergy("NKDA"), new Gender("male"), new Doctor("Ben"),
                    getTagSet("Diabetic"), getMedicineSet("Lantus")),
            new Person(new Nric("S2345678B"), new Name("Bernice Yu"), new DateOfBirth("22/09/2022"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new DrugAllergy("Panadol"), new Gender("female"), new Doctor("Alex"),
                    getTagSet("Osteoporotic", "Diabetic"), getMedicineSet("ibuprofen", "diclofenac")),
            new Person(new Nric("S3456789C"), new Name("Charlotte Oliveiro"), new DateOfBirth("18/12/2001"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new DrugAllergy("Penicillin"), new Gender("male"), new Doctor("Ben"),
                    getTagSet("Asthmatic"), getMedicineSet()),
            new Person(new Nric("S0012345D"), new Name("David Li"), new DateOfBirth("15/06/1998"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new DrugAllergy("Aspirin Panadol"), new Gender("male"), new Doctor("Alex"),
                    getTagSet("Epileptic"), getMedicineSet("DHAP", "DTIC")),
            new Person(new Nric("S1212345E"), new Name("Irfan Ibrahim"), new DateOfBirth("12/05/2008"),
                    new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new DrugAllergy("NKDA"), new Gender("male"), new Doctor("Shannon"),
                    getTagSet("Arthritic"), getMedicineSet("NTRI")),
            new Person(new Nric("S0001111F"), new Name("Roy Balakrishnan"), new DateOfBirth("11/04/2001"),
                    new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new DrugAllergy("NKDA"), new Gender("male"), new Doctor("Ben"),
                    getTagSet("Diabetic"), getMedicineSet())

        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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

    /**
     * Returns a medicine set containing the list of strings given.
     */
    public static Set<Medicine> getMedicineSet(String... strings) {
        return Arrays.stream(strings)
            .map(Medicine::new)
            .collect(Collectors.toSet());
    }

}
