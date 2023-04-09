package seedu.medinfo.testutil;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.patient.Patient;

import static seedu.medinfo.testutil.TypicalPatients.ALEX;

/**
 * A utility class to help with building MedInfo objects.
 * Example usage: <br>
 *     {@code MedInfo ab = new MedInfoBuilder().withPerson("John", "Doe").build();}
 */
public class MedInfoBuilder {

    private MedInfo medInfo;

    public MedInfoBuilder() {
        medInfo = new MedInfo();
    }

    public MedInfoBuilder(MedInfo medInfo) {
        this.medInfo = medInfo;
    }

    /**
     * Adds a new {@code Patient} to the {@code MedInfo} that we are building.
     */
    public MedInfoBuilder withPerson(Patient patient) {
        try {
            medInfo.addPatient(patient);
        } catch (CommandException e) {
        }
        return this;
    }

    public MedInfo build() {
        return medInfo;
    }
}
