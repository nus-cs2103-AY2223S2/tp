package seedu.medinfo.model.util;

import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.patient.Name;
import seedu.medinfo.model.patient.Nric;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.Status;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.model.ward.WardName;

/**
 * Contains utility methods for populating {@code MedInfo} with sample data.
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

    public static ReadOnlyMedInfo getSampleMedInfo() throws CommandException {
        MedInfo sampleAb = new MedInfo();
        for (Ward sampleWard : getSampleWards()) {
            sampleAb.addWard(sampleWard);
        }

        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }

        return sampleAb;
    }
}
