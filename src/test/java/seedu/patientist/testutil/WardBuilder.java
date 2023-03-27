package seedu.patientist.testutil;

import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.UniquePersonList;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

/**
 * A utility class to help with building Ward objects
 */
public class WardBuilder {

    public static final String DEFAULT_NAME = "Block A Ward 1";

    private String wardName;
    private UniquePersonList patients;
    private UniquePersonList staffs;

    /**
     * Creates a {@code WardBuilder} with the default details.
     */
    public WardBuilder() {
        wardName = DEFAULT_NAME;
        patients = new UniquePersonList(DEFAULT_NAME);
        staffs = new UniquePersonList(DEFAULT_NAME);
    }

    /**
     * Clones {@code toCopy} into the WardBuilder.
     */
    public WardBuilder(Ward toCopy) {
        wardName = toCopy.getWardName();
        patients = new UniquePersonList(wardName);
        for (Person pat : toCopy.getPatientsAsUnmodifiableObservableList()) {
            patients.add(pat);
        }
        staffs = new UniquePersonList(wardName);
        for (Person stf : toCopy.getStaffsAsUnmodifiableObservableList()) {
            staffs.add(stf);
        }
    }

    /**
     * Adds {@code patient} into ward we are building.
     */
    public WardBuilder withPatient(Patient patient) {
        this.patients.add(patient);
        return this;
    }

    /**
     * Adds {@code staff} into ward we are building.
     */
    public WardBuilder withStaff(Staff staff) {
        this.staffs.add(staff);
        return this;
    }

    /**
     * Sets {@code name} of the ward we are building.
     */
    public WardBuilder withName(String name) {
        this.wardName = name;
        return this;
    }

    /**
     * Builds and returns the ward.
     */
    public Ward build() {
        Ward result = new Ward(wardName);
        for (Person patient : patients) {
            result.addPatient((Patient) patient);
        }
        for (Person staff : staffs) {
            result.addStaff((Staff) staff);
        }
        return result;
    }
}
