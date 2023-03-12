package seedu.patientist.model.person.patient;

/**
 * Object representing the ID number of a patient. ID numbers are automatically capitalised when they are created.
 */
public class PatientIdNumber {
    private final String idNumber;

    //TODO: include error checking for invalid ID numbers
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
        if (other == this) {
            return true;
        }
        if (!(other instanceof PatientIdNumber)) {
            return false;
        }
        return this.idNumber.equals(((PatientIdNumber) other).idNumber);
    }
}
