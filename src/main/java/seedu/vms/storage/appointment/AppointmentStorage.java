package seedu.vms.storage.appointment;

import java.io.IOException;

import seedu.vms.model.appointment.AppointmentManager;

/**
 * Represents the storage for {@link AppointmentManager}.
 */
public interface AppointmentStorage {
    /**
     * Loads the {@code AppointmentManager} as defined by the user.
     *
     * @throws IOException if an I/O error occurs.
     */
    public AppointmentManager loadAppointments() throws IOException;


    /**
     * Saves the specified {@code AppointmentManager} to hard disk.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void saveAppointments(AppointmentManager manager) throws IOException;
}
