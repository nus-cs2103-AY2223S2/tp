package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Status;
import seedu.address.model.ward.Ward;
import seedu.address.model.ward.WardName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Nric("S1234567A"), new Name("Alex Yeoh"), new Status("RED"),
                    new WardName("Intensive Care")),
            new Patient(new Nric("S0000000A"), new Name("Bernice Yu"), new WardName("Class C")),
            new Patient(new Nric("S0000001A"), new Name("Charlotte Oliveiro"), new Status("GRAY")),
            new Patient(new Nric("S0000002A"), new Name("David Li"), new Status("GREEN")),
            new Patient(new Nric("S0000003A"), new Name("Irfan Ibrahim"), new Status("YELLOW")),
            new Patient(new Nric("S0000004A"), new Name("Roy Balakrishnan"), new Status("RED"))
        };
    }

    public static Ward[] getSampleWards() {
        return new Ward[] {
            new Ward(new WardName("Waiting Room")),
            new Ward(new WardName("Class A")),
            new Ward(new WardName("Class B")),
            new Ward(new WardName("Class C")),
            new Ward(new WardName("Intensive Care"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Ward sampleWard : getSampleWards()) {
            sampleAb.addWard(sampleWard);
        }

        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }

        return sampleAb;
    }
}
