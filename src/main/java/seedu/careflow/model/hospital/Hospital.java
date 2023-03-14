package seedu.careflow.model.hospital;

/**
 * Represents a Hospital in the hospital record
 */
public class Hospital {
    private final String hospitalName;
    private final String hotline;

    /**
     * Constructs a Hospital,
     * Every field must be present and not null.
     */
    public Hospital(String hospitalName, String hotline) {
        this.hospitalName = hospitalName;
        this.hotline = hotline;
    }

    public String getHospitalName() {
        return this.hospitalName;
    }

    public String getHotline() {
        return this.hotline;
    }

    /**
     * Returns true if both hospital have the same name.
     * This defines a weaker notion of equality between two hospital.
     */
    public boolean isSameHospital(Hospital otherHospital) {
        if (otherHospital == this) {
            return true;
        }
        return otherHospital != null
                && otherHospital.getHospitalName().equals(getHospitalName());
    }
}
