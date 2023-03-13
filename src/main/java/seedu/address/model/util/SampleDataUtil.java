package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.*;
import seedu.address.model.patient.Patient;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
                new Patient(new NRIC("S1234567A"), new Name("Alex Yeoh")),
                new Patient(new NRIC("S0000000A"), new Name("Bernice Yu")),
                new Patient(new NRIC("S0000001A"), new Name("Charlotte Oliveiro"), Status.GRAY),
                new Patient(new NRIC("S0000002A"), new Name("David Li"), Status.GREEN),
                new Patient(new NRIC("S0000003A"), new Name("Irfan Ibrahim"), Status.YELLOW),
                new Patient(new NRIC("S0000004A"), new Name("Roy Balakrishnan"), Status.RED)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }
}
