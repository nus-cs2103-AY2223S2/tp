package seedu.medinfo.model.patient;

import static seedu.medinfo.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.medinfo.model.ward.Ward.wardWithName;

import java.time.LocalDateTime;
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

    // Identity fields
    private final Nric nric;
    private final Name name;

    // Data fields
    private Status status = new Status("GRAY");
    private WardName ward = new WardName("Waiting Room");
    private Discharge discharge = new Discharge("To Be Confirmed");
    private final int INVERTER = -1;

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

    public Nric getNric() {
        return nric;
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.fullName;
    }

    public Status getStatus() {
        return status;
    }
    public String getStatusDesc() {
        return status.getDesc();
    }

    public Ward getWard() {
        return wardWithName(ward.wardName);
    }
    public WardName getWardName() {
        return ward;
    }
    public String getWardNameString() {
        return wardWithName(ward.wardName).getNameString();
    }
    public Discharge getDischarge() {
        return discharge;
    }

    /**
     * Returns the discharge date as String.
     */
    public String getDischargeString() {
        return discharge.value;
    }

    /**
     * Returns the discharge date as LocalDateTime.
     * @return LocalDateTime representing the discharge date-time.
     */
    public Date getDischargeDateTime() {
        return discharge.getDateTime();
    }

    public void setStatus(Status newStatus) {
        requireAllNonNull(newStatus);
        status = newStatus;
    }

    public void setWard(WardName newWard) {
        requireAllNonNull(newWard);
        ward = newWard;
    }

    public void setDischarge(Discharge newDischarge) {
        requireAllNonNull(newDischarge);
        discharge = newDischarge;
    }

    /**
     * Returns true if both patients have the same nric and name.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        return this.equals(otherPatient);
    }

    /**
     * Returns true if both patients have the same nric.
     * This defines a weaker notion of equality between two patients in order to prevent duplicate nric
     * being added to the list.
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
     */
    public int compareToByNameAsc(Patient patient) {
        return this.getNameString().compareTo(patient.getNameString());
    }


    /**
     * Returns compared result between {@code this} and the given {@code patient} by Name in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByNameDesc(Patient patient) {
        return INVERTER * this.getNameString().compareTo(patient.getNameString());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Status in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByStatusAsc(Patient patient) {
        return this.getStatus().getValue().compareTo(patient.getStatus().getValue());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Status in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByStatusDesc(Patient patient) {
        return INVERTER * this.getStatus().getValue().compareTo(patient.getStatus().getValue());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Discharge in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByDischargeAsc(Patient patient) {
        // System.out.println(this.getDischargeDateTime().toString());
        return this.getDischargeDateTime().compareTo(patient.getDischargeDateTime());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Discharge in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByDischargeDesc(Patient patient) {
        return INVERTER * this.getDischargeDateTime().compareTo(patient.getDischargeDateTime());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Ward in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByWardAsc(Patient patient) {
        return this.getWardNameString().compareTo(patient.getWardNameString());
    }

    /**
     * Returns compared result between {@code this} and the given {@code patient} by Ward in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByWardDesc(Patient patient) {
        return INVERTER * this.getWardNameString().compareTo(patient.getWardNameString());
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
        // use this method for custom fields hashing instead of implementing your own
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
            .append(getWard())
            .append("; Discharge: ")
            .append(getDischarge());

        return builder.toString();
    }
}
