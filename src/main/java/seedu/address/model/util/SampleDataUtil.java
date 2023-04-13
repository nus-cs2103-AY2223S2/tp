package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Booking;
import seedu.address.model.person.Address;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.prescription.Cost;
import seedu.address.model.prescription.Medication;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Nric("S1234567X"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new HashSet<>(), getTagSet(), new ArrayList<>(), new Role("Patient")),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Nric("T1234567Z"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new HashSet<>(), getTagSet("Outpatient"), new ArrayList<>(), new Role("Patient")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Nric("F1234567A"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new HashSet<>(), getTagSet(), new ArrayList<>(), new Role("Patient")),
            new Doctor(new Name("Jane Chew"), new Phone("93424232"), new Email("janec@example.com"),
                    new Nric("S3523567R"), new Address("Blk 11 Eunos Street 1, #14-09"),
                    getTagSet("Cardiology"), new ArrayList<>(), new Role("Doctor")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Nric("G1234567L"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new HashSet<>(), getTagSet("Outpatient"), new ArrayList<>(), new Role("Patient")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Nric("M1234567K"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new HashSet<>(), getTagSet(), new ArrayList<>(), new Role("Patient")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Nric("S1234567B"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new HashSet<>(),
                    getTagSet(), new ArrayList<>(), new Role("Patient")),
            new Doctor(new Name("Joseph Law"), new Phone("93424232"), new Email("joseph@example.com"),
                    new Nric("S7654321R"), new Address("Blk 11 Yishun Street 1, #14-09"),
                    getTagSet("Surgery"), new ArrayList<>(), new Role("Doctor"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            if (samplePerson.isPatient()) {
                Patient samplePatient = (Patient) samplePerson;
                sampleAb.addPatient(samplePatient);
            }
            if (samplePerson.isDoctor()) {
                Doctor sampleDoctor = (Doctor) samplePerson;
                sampleAb.addDoctor(sampleDoctor);
            }
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

    public static Set<Prescription> getPrescriptionSet(String ... strings) {
        assert strings.length % 2 == 0 : "getPrescriptionSet should only take an even number of arguments!";

        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < strings.length; i += 2) {
            prescriptions.add(new Prescription(new Medication(strings[i]), new Cost(strings[i + 1])));
        }
        return prescriptions;
    }

    public static Appointment getAppointment(String... strings) {
        return new Appointment(new Nric(strings[0]), new Booking(strings[1]),
                new Nric(strings[0]));
    }

}
