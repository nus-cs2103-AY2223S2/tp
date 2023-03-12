package seedu.careflow.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.hospital.UniqueHospitalList;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;



/**
 * Wraps all data at the Hospital Record level
 */
public class HospitalRecord implements ReadOnlyHospitalRecords {
    private final UniqueHospitalList hospitals;

    {
        hospitals = new UniqueHospitalList();
    }

    public HospitalRecord() {}

    /**
     * creates a HospitalRecord using the hospitals in the {@code toBeCopied}
     */
    public HospitalRecord(ReadOnlyHospitalRecords toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals.setHospitals(hospitals);
    }

    /**
     * Resets the existing data of this hospital record with {@code newData}.
     */
    public void resetData(ReadOnlyHospitalRecords newData) {
        requireNonNull(newData);
        setHospitals(newData.getHospitalList());
    }

    /**
     * Returns true if a hospital with the same identity as {@code hospital} exists in the hospital records.
     */
    public boolean hasHospital(Hospital hospital) {
        requireNonNull(hospital);
        return hospitals.contains(hospital);
    }

    /**
     * Adds a hospital to the hospital records.
     * The hospital must not already exist in the hospital record.
     */
    public void addHospital(Hospital h) {
        hospitals.add(h);
    }

    /**
     * Replaces the given hospital {@code target} in the list with {@code editedHospital}.
     * {@code target} must exist in the hospital record.
     * The hospital identity of {@code editedHospital} must not be the same as another existing drug in the hospital
     * record.
     */
    public void setHospital(Hospital target, Hospital editedHospital) {
        requireNonNull(target);
        hospitals.setHospital(target, editedHospital);
    }

    /**
     * Removes {@code key} from drug inventory.
     * {@code key} must exist in the drug inventory.
     */
    public void removeHospital(Hospital key) {
        hospitals.remove(key);
    }

    @Override
    public String toString() {
        return hospitals.asUnmodifiableObservableList().size() + " hospitals";
    }

    @Override
    public ObservableList<Hospital> getHospitalList() {
        return hospitals.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof HospitalRecord
                && hospitals.equals(((HospitalRecord) other).hospitals));
    }

    @Override
    public int hashCode() {
        return hospitals.hashCode();
    }
}
