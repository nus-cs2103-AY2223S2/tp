package seedu.vms.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.vms.model.patient.AddressBook;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;
import seedu.vms.model.patient.ReadOnlyAddressBook;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"))
            new Patient(new Name("Bernice Yu"), new Phone("99272758"))
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"))
            new Patient(new Name("David Li"), new Phone("91031282"))
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"))
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.add(samplePatient);
        }
        return sampleAb;
    }

}
