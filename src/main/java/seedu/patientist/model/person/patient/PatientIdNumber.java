package seedu.patientist.model.person.patient;

public class PatientIdNumber {
    private final String idNumber;

    public PatientIdNumber(String idNumber) {
        this.idNumber = idNumber.toUpperCase();
    }

    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return idNumber;
    }

    @Override
    public int hashCode() {
        return idNumber.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof PatientIdNumber)) return false;
        return this.idNumber.equals(((PatientIdNumber) other).idNumber);
    }
}
