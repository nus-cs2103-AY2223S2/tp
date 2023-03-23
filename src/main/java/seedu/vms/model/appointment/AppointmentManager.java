package seedu.vms.model.appointment;

import seedu.vms.model.StorageModel;

/**
 * Wraps all data at the patient-manager level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentManager extends StorageModel<Appointment> implements ReadOnlyAppointmentManager {

    /**
     * Constructs an empty {@code AppointmentManager}.
     */
    public AppointmentManager() {

    }

    /**
     * Creates an AppointmentManager using the appointments in the {@code toBeCopied}
     */
    public AppointmentManager(ReadOnlyAppointmentManager toBeCopied) {
        super(toBeCopied);
    }

    /**
     * Marks the appointment at the given id as completed.
     * The appointment must exist in the appointment manager.
     */
    public void mark(int id) {
        this.getMapView().get(id).getValue().mark();
    }

    /**
     * Unmarks the appointment at the given id as not completed.
     * The appointment must exist in the appointment manager.
     */
    public void unmark(int id) {
        this.getMapView().get(id).getValue().unmark();
    }
}
