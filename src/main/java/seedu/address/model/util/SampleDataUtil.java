package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.*;
import seedu.address.model.patient.Patient;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePersons() {
        return new Patient[] {
                new Patient(new Name("Alex Yeoh")),
                new Patient(new Name("Bernice Yu")),
                new Patient(new Name("Charlotte Oliveiro")),
                new Patient(new Name("David Li")),
                new Patient(new Name("Irfan Ibrahim")),
                new Patient(new Name("Roy Balakrishnan"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePersons()) {
            sampleAb.addPerson(samplePatient);
        }
        return sampleAb;
    }

}
