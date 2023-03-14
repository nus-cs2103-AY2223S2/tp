package seedu.vms.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.model.patient.Phone;
import seedu.vms.model.patient.ReadOnlyPatientManager;

/**
 * Contains utility methods for populating {@code PatientManager} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Dob("1983-12-23"),
                new BloodType("A+"),
                getAllergySet(),
                getVaccineSet("Moderna")),
            new Patient(new Name("Bernice Yu"),
                new Phone("99272758"),
                new Dob("2001-03-12"),
                new BloodType("B+"),
                getAllergySet("seafood", "gluten"),
                getVaccineSet()),
            new Patient(new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Dob("1997-04-05"),
                new BloodType("B-"),
                getAllergySet("gluten"),
                getVaccineSet("chinavax")),
            new Patient(new Name("David Li"),
                new Phone("91031282"),
                new Dob("1999-07-12"),
                new BloodType("AB-"),
                getAllergySet("dustmite"),
                getVaccineSet()),
            new Patient(new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Dob("1979-09-23"),
                new BloodType("AB+"),
                getAllergySet("cathair"),
                getVaccineSet("classmates")),
            new Patient(new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Dob("1995-05-29"),
                new BloodType("O+"),
                getAllergySet(),
                getVaccineSet())
        };
    }

    public static ReadOnlyPatientManager getSamplePatientManager() {
        PatientManager sampleAb = new PatientManager();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.add(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a allergy set containing the list of strings given.
     */
    public static Set<GroupName> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(GroupName::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a vaccine set containing the list of strings given.
     */
    public static Set<GroupName> getVaccineSet(String... strings) {
        return Arrays.stream(strings)
                .map(GroupName::new)
                .collect(Collectors.toSet());
    }

}
