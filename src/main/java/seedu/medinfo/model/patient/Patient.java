package seedu.medinfo.model.patient;

import static seedu.medinfo.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.medinfo.model.ward.Ward.wardWithName;

import java.util.Date;
import java.util.Objects;

import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.model.ward.WardName;

/**
 * Represents a Patient in the medinfo book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Patient {
    private static final int INVERTER = -1;

    // Identity fields
    private final Nric nric;
    private final Name name;

    // Data fields
    private Status status = new Status("GRAY");
    private WardName ward = new WardName("Waiting Room");
    private Discharge discharge = new Discharge("To Be Confirmed");

    /**
     * Constructor for Patient taking in nric and name.
     *
     * @param nric Patient NRIC
     * @param name Patient name
     *             Every field must be present and not null.
     */
    public Patient(Nric nric, Name name) {
        requireAllNonNull(nric, name);
        this.nric = nric;
        this.name = name;
    }

    /**
     * Constructor for Patient taking in nric, name, status.
     *
     * @param nric   Patient NRIC
     * @param name   Patient name
     * @param status Patient status
     *               Every field must be present and not null.
     */
    public Patient(Nric nric, Name name, Status status) {
        requireAllNonNull(nric, name, status);
        this.nric = nric;
        this.name = name;
        this.status = status;
    }

    /**
     * Constructor for Patient taking in nric, name, ward.
     *
     * @param nric Patient NRIC
     * @param name Patient name
     * @param ward Patient ward
     *             Every field must be present and not null.
     */
    public Patient(Nric nric, Name name, WardName ward) {
        requireAllNonNull(nric, name, ward);
        this.nric = nric;
        this.name = name;
        this.ward = ward;
    }

    /**
     * Constructor for Patient taking in nric, name, status, ward.
     *
     * @param nric   Patient NRIC
     * @param name   Patient name
     * @param status Patient status
     * @param ward   Patient ward
     *               Every field must be present and not null.
     */
    public Patient(Nric nric, Name name, Status status, WardName ward) {
        requireAllNonNull(nric, name, status, ward);
        this.nric = nric;
        this.name = name;
        this.status = status;
        this.ward = ward;
    }

    /**
     * Constructor for Patient taking in nric, name, status, ward.
     *
     * @param nric        Patient NRIC
     * @param name        Patient name
     * @param status      Patient status
     * @param ward        Patient ward
     * @param discharge   Patient discharge
     *               Every field must be present and not null.
     */
    public Patient(Nric nric, Name name, Status status, WardName ward, Discharge discharge) {
        requireAllNonNull(nric, name, status, ward, discharge);
        this.nric = nric;
        this.name = name;
        this.status = status;
        this.ward = ward;
        this.discharge = discharge;
    }

    /**
     * Returns the Nric.
     *
     * @return Patient Nric.
     */
    public Nric getNric() {
        return nric;
    }

    /**
     * Returns the Name.
     *
     * @return Patient Name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the Name as a String.
     *
     * @return Patient Name as a String.
     */
    public String getNameString() {
        return name.fullName;
    }

    /**
     * Returns the Status.
     *
     * @return Patient Status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns the Status as a String.
     *
     * @return Patient Status as a String.
     */
    public String getStatusDesc() {
        return status.getDesc();
    }

    /**
     * Returns the Ward for comparison.
     *
     * @return Placeholder Ward for comparison.
     */
    public Ward getWard() {
        return wardWithName(ward.wardName);
    }

    /**
     * Returns the WardName.
     *
     * @return Patient WardName.
     */
    public WardName getWardName() {
        return ward;
    }

    /**
     * Returns the WardName as a String.
     *
     * @return Patient WardName as a String.
     */
    public String getWardNameString() {
        return wardWithName(ward.wardName).getNameString();
    }

    /**
     * Returns the discharge date.
     *
     * @return Patient Discharge.
     */
    public Discharge getDischarge() {
        return discharge;
    }

    /**
     * Returns the discharge date as a String.
     *
     * @return Patient Discharge as a String.
     */
    public String getDischargeString() {
        return discharge.value;
    }

    /**
     * Returns the discharge date as LocalDateTime.
     *
     * @return LocalDateTime representing the discharge date-time.
     */
    public Date getDischargeDateTime() {
        return discharge.getDateTime();
    }

    /**
     * Sets a new Status.
     *
     * @param newStatus Status to be set.
     */
    public void setStatus(Status newStatus) {
        requireAllNonNull(newStatus);
        status = newStatus;
    }

    /**
     * Sets a new Ward.
     *
     * @param newWard Ward to be set.
     */
    public void setWard(WardName newWard) {
        requireAllNonNull(newWard);
        ward = newWard;
    }

    /**
     * Sets a new Discharge.
     *
     * @param newDischarge Discharge to be set.
     */
    public void setDischarge(Discharge newDischarge) {
        requireAllNonNull(newDischarge);
        discharge = newDischarge;
    }

    /**
     * Returns true if both patients have the same nric and name.
     * This defines a weaker notion of equality between two patients.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patients are the same.
     */
    public boolean isSamePatient(Patient otherPatient) {
        return this.equals(otherPatient);
    }

    /**
     * Returns true if both patients have the same Nric.
     * This defines a weaker notion of equality between two patients in order to prevent duplicate Nric
     * being added to the list.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patients have the same Nric.
     */
    public boolean isSameNric(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
            && otherPatient.getNric().equals(this.getNric());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Name in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByNameAsc(Patient otherPatient) {
        return this.getNameString().compareTo(otherPatient.getNameString());
    }


    /**
     * Returns compared result between {@code this} and the given {@code patient} by Name in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByNameDesc(Patient otherPatient) {
        return INVERTER * this.getNameString().compareTo(otherPatient.getNameString());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Status in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByStatusAsc(Patient otherPatient) {
        return this.getStatus().getValue().compareTo(otherPatient.getStatus().getValue());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Status in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByStatusDesc(Patient otherPatient) {
        return INVERTER * this.getStatus().getValue().compareTo(otherPatient.getStatus().getValue());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Discharge in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByDischargeAsc(Patient otherPatient) {
        return this.getDischargeDateTime().compareTo(otherPatient.getDischargeDateTime());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Discharge in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByDischargeDesc(Patient otherPatient) {
        return INVERTER * this.getDischargeDateTime().compareTo(otherPatient.getDischargeDateTime());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Ward in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByWardAsc(Patient otherPatient) {
        return this.getWardNameString().compareTo(otherPatient.getWardNameString());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Ward in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     *
     * @param otherPatient Patient to be compared with.
     * @return If the patient should be placed before or after.
     */
    public int compareToByWardDesc(Patient otherPatient) {
        return INVERTER * this.getWardNameString().compareTo(otherPatient.getWardNameString());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getNric().equals(getNric())
            && otherPatient.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nric, name, status, ward, discharge);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNric())
            .append("; Name: ")
            .append(getName())
            .append("; Status: ")
            .append(getStatus())
            .append("; Ward: ")
            .append(getWardNameString())
            .append("; Discharge: ")
            .append(getDischarge());

        return builder.toString();
    }
}
