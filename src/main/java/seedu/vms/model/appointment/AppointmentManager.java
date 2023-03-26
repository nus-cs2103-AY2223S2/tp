package seedu.vms.model.appointment;

import java.util.Map;

import seedu.vms.commons.core.ValueChange;
import seedu.vms.model.GroupName;
import seedu.vms.model.IdData;
import seedu.vms.model.StorageModel;
import seedu.vms.model.vaccination.VaxType;

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
        Appointment appointment = getMapView().get(id).getValue().mark();
        set(id, appointment);
    }

    /**
     * Unmarks the appointment at the given id as not completed.
     * The appointment must exist in the appointment manager.
     */
    public void unmark(int id) {
        Appointment appointment = getMapView().get(id).getValue().unmark();
        set(id, appointment);
    }

    /**
     * Handles vaccination changes in AppointmentManager
     */
    public void handleVaccinationChange(ValueChange<VaxType> change) {
        if (!change.getOldValue().equals(change.getNewValue())
                && change.getOldValue().isPresent()) {
            if (change.getNewValue().isPresent()) {
                // update
                GroupName vaxToEdit = change.getOldValue().get().getGroupName();
                GroupName editedVax = change.getNewValue().get().getGroupName();
                getMapView().entrySet().stream()
                        .filter(x->x.getValue().getValue().getVaccination().equals(vaxToEdit))
                        .forEach(x->set(x.getKey(), x.getValue().getValue().setVaccination(editedVax)));
            } else {
                // delete
                GroupName vaxToDelete = change.getOldValue().get().getGroupName();
                getMapView().entrySet().stream()
                        .filter(x->x.getValue().getValue().getVaccination().equals(vaxToDelete))
                        .forEach(x->remove(x.getKey()));
            }
        }
    }
}
