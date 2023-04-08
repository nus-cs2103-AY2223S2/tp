package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

//import seedu.address.model.AddressBook;
//import seedu.address.model.ReadOnlyAddressBook;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
//import seedu.address.model.person.doctor.Specialty;
//import seedu.address.model.person.doctor.Yoe;
//import seedu.address.model.person.patient.Diagnosis;
//import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Patient;
//import seedu.address.model.person.patient.Remark;
//import seedu.address.model.person.patient.Status;
//import seedu.address.model.person.patient.Weight;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    //    public static Doctor[] getSampleDoctors() {
    //        return new Doctor[]{
    //            new Doctor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
    //                    new Specialty("Cardiology"), new Yoe("5"), getTagSet("intern")),
    //            new Doctor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
    //                    new Specialty("Neurology"), new Yoe("10"), getTagSet("resident")),
    //            new Doctor(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
    //                    new Specialty("Orthopaedics"), new Yoe("1"), getTagSet("intern")),
    //            new Doctor(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
    //                    new Specialty("Cardiology"), new Yoe("5"), getTagSet("resident")),
    //            new Doctor(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
    //                    new Specialty("General Surgery"), new Yoe("15"), getTagSet("consultant")),
    //            new Doctor(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
    //                    new Specialty("General Surgery"), new Yoe("35"), getTagSet("chiefOfSurgery"))
    //        };
    //    }
    //
    //    public static Patient[] getSamplePatients() {
    //        return new Patient[]{
    //            new Patient(new Name("Bob Choo"), new Phone("94351253"), new Email("bchoo@gmail.com"),
    //                    new Height("1.70"),
    //                    new Weight("70.0"), new Diagnosis("Cancer"), new Status("Intensive Care Unit"),
    //                    new Remark("Prescribed chemo"), getTagSet("pendingReview")),
    //            new Patient(new Name("Alice Pauline"), new Phone("94351253"), new Email("apauline@example.com"),
    //                    new Height("1.60"), new Weight("50.0"), new Diagnosis("Cancer"),
    //                    new Status("Intensive Care Unit"),
    //                    new Remark("Prescribed chemo"), getTagSet("pendingReview"))
    //        };
    //    }
    //
    //    public static ReadOnlyAddressBook getSampleAddressBook() {
    //        AddressBook sampleAb = new AddressBook();
    //        for (Doctor sampleDoctor : getSampleDoctors()) {
    //            sampleAb.addDoctor(sampleDoctor);
    //        }
    //        for (Patient samplePatient : getSamplePatients()) {
    //            sampleAb.addPatient(samplePatient);
    //        }
    //        return sampleAb;
    //    }

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
    public static Set<Patient> getPatientSet(Patient... patients) {
        return Arrays.stream(patients)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a patient set containing the list of patients given.
     */
    public static Set<Doctor> getDoctorSet(Doctor... doctors) {
        return Arrays.stream(doctors)
                .collect(Collectors.toSet());
    }

}
