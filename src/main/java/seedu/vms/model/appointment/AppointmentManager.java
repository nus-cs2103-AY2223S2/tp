package seedu.vms.model.appointment;

import java.util.Map;

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.IdData;
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
}
