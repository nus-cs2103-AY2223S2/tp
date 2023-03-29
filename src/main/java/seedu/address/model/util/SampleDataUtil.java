package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.Specialty;
import seedu.address.model.person.doctor.Yoe;
import seedu.address.model.person.patient.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Doctor[] getSampleDoctors() {
        return new Doctor[] {
            new Doctor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Specialty("Cardiology"), new Yoe("5"), getTagSet("senior")),
            new Doctor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Specialty("Pediatrics"), new Yoe("1"), getTagSet("resident")),
            new Doctor(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Specialty("Radiology"), new Yoe("6"), getTagSet("leave")),
            new Doctor(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Specialty("Radiology"), new Yoe("2"), getTagSet("resident")),
            new Doctor(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Specialty("Neurosurgery"), new Yoe("20"), getTagSet("genius")),
            new Doctor(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Specialty("Neurosurgery"), new Yoe("15"), getTagSet("available"))
        };
    }

    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alice Lee"), new Phone("91234567"), new Email("alicelee@example.com"),
                new Height("1.68"), new Weight("65.5"), new Diagnosis("Migraine"), new Status("Inpatient"),
                new Remark("Patient is allergic to penicillin"), getTagSet("dizziness")),
            new Patient(new Name("Bob Tan"), new Phone("92345678"), new Email("bobtan@example.com"),
                new Height("1.75"), new Weight("80.2"), new Diagnosis("Diabetes"), new Status("Inpatient"),
                new Remark("Patient needs to monitor blood sugar level regularly"), getTagSet("hypertension")),
            new Patient(new Name("Charlie Ng"), new Phone("93456789"), new Email("charlieng@example.com"),
                new Height("1.60"), new Weight("70.0"), new Diagnosis("Asthma"), new Status("Outpatient"),
                new Remark("Patient has asthma attacks during exercise"), getTagSet("asthma")),
            new Patient(new Name("Daniel Lim"), new Phone("94567890"), new Email("daniellim@example.com"),
                new Height("1.80"), new Weight("85.5"), new Diagnosis("Depression"), new Status("Inpatient"),
                new Remark("Patient has a history of suicide attempts"), getTagSet("depression")),
            new Patient(new Name("Emily Tan"), new Phone("95678901"), new Email("emilytan@example.com"),
                new Height("1.70"), new Weight("58.0"), new Diagnosis("Anxiety"), new Status("Outpatient"),
                new Remark("Patient has panic attacks when in crowds"), getTagSet("anxiety"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Doctor sampleDoctor : getSampleDoctors()) {
            sampleAb.addDoctor(sampleDoctor);
        }
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
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
     * Returns a patient set containing the list of patients given.
     */
    public static Set<Patient> getPatientSet(Patient ... patients) {
        return Arrays.stream(patients)
                .collect(Collectors.toSet());
    }

}
