package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class LoaderUtil {
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
