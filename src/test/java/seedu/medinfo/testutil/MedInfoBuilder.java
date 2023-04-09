package seedu.medinfo.testutil;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.MedInfo;
import seedu.medinfo.model.patient.Patient;

/**
 * A utility class to help with building Addressbook objects.
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
            System.out.println("Caught CommandException error!!!");
        }
        return this;
    }

    public MedInfo build() {
        return medInfo;
    }
}
