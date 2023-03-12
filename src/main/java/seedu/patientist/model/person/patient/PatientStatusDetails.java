package seedu.patientist.model.person.patient;

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
}
