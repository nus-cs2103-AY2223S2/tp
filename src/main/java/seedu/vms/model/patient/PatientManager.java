package seedu.vms.model.patient;

import seedu.vms.model.StorageModel;

/**
 * Wraps all data at the patient-manager level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class PatientManager extends StorageModel<Patient> implements ReadOnlyPatientManager {
    public PatientManager() {}

    /**
     * Creates an PatientManager using the Patients in the {@code toBeCopied}
     */
    public PatientManager(ReadOnlyPatientManager toBeCopied) {
        super(toBeCopied);
    }
}
