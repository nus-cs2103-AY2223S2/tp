package seedu.vms.model.appointment;

import seedu.vms.model.StorageModel;

/**
 * Wraps all data at the patient-manager level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentManager extends StorageModel<Appointment> implements ReadOnlyAppointmentManager {

    public AppointmentManager() {}

    /**
     * Creates an PatientManager using the Patients in the {@code toBeCopied}
     */
    public AppointmentManager(ReadOnlyAppointmentManager toBeCopied) {
        super(toBeCopied);
    }
}
