package seedu.vms.storage;

import seedu.vms.storage.appointment.AppointmentStorage;
import seedu.vms.storage.vaccination.VaxTypeStorage;

/**
 * API of the Storage component
 */
public interface Storage extends PatientManagerStorage, UserPrefsStorage, VaxTypeStorage, AppointmentStorage {

}
