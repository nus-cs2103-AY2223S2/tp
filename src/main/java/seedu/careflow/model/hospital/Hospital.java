package seedu.careflow.model.hospital;

import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Phone;

/**
 * Represents a Hospital in the hospital record
 */
public class Hospital {
    private final Name hospitalName;
    private final Phone hotline;

    /**
     * Constructs a Hospital,
     * Every field must be present and not null.
     */
    public Hospital(Name hospitalName, Phone hotline) {
        requireAllNonNull(hospitalName, hotline);
        this.hospitalName = hospitalName;
        this.hotline = hotline;
    }

    public Name getHospitalName() {
        return this.hospitalName;
    }

    public Phone getHotline() {
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Hospital)) {
            return false;
        }

        Hospital otherHospital = (Hospital) other;
        return otherHospital.getHospitalName().equals(getHospitalName())
                && otherHospital.getHotline().equals(getHotline());
    }
}
