package seedu.vms.model.appointment;

import seedu.vms.model.StorageModel;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentManager extends StorageModel<Appointment> implements ReadOnlyAppointmentManager {
    public AppointmentManager() {}

    /**
     * Creates an AppointmentManager using the Appointments in the {@code toBeCopied}
     */
    public AppointmentManager(ReadOnlyAppointmentManager toBeCopied) {
        super(toBeCopied);
    }
}
