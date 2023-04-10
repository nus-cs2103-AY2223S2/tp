package seedu.patientist.model.person.patient;

/**
 * Represents the status details of a patient. This is where the treatment progress and notes are to be recorded.
 */
public class PatientStatusDetails {
    private String details;

    public PatientStatusDetails() {
        this.details = "";
    }

    public PatientStatusDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return details; //TODO?
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof PatientStatusDetails // instanceof handles nulls
                   && details.equals(((PatientStatusDetails) other).details)); // state check
    }
}
