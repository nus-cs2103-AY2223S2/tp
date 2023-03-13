package seedu.vms.model.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.vms.model.StorageModel;

import java.util.Optional;

/**
 * Wraps all data at the patient-manager level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentManager extends StorageModel<Appointment> implements ReadOnlyAppointmentManager {

    private final ObservableMap<String, Appointment> typeMap;
    private final ObservableMap<String, Appointment> unmodifiableTypeMap;
    public AppointmentManager() {
        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }

    /**
     * Creates an AppointmentManager using the Appointments in the {@code toBeCopied}
     */
    public AppointmentManager(ReadOnlyAppointmentManager toBeCopied) {
        super(toBeCopied);
        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }


    /**
     * Adds the specified {@code Appointment}. If there is another {@code Appointment}
     * with the same patient_id as the specified, that {@code Appointment} is replaced.
     *
     * @param appointment - the {@code Appointment} to add.
     */
    public void add(Appointment appointment) {
        typeMap.put(appointment.getPatient(), appointment);
    }


    /**
     * Returns the {@code Appointment} mapped to the specified name, wrapped in an
     * {@code Optional}. If there are no mappings to the specified name,
     * {@code Optional.empty} is returned instead.
     *
     * @param patient_id - the patient_id of the {@code Appointment} to retrieve.
     * @return the {@code Appointment} mapped to the specified name, wrapped in an
     *      {@code Optional}.
     */
    public Optional<Appointment> get(String patient_id) {
        return Optional.ofNullable(typeMap.get(patient_id));
    }


    /**
     * Removes the {@code Appointment} with the specified name. The {@code Appointment}
     * removed is returned and wrapped in an {@code Optional}. If there are no
     * mappings, to the specified name, nothing is done and
     * {@code Optional.empty} is returned.
     *
     * @param patient_id - the patient_id of the {@code Appointment} to remove.
     * @return the {@code Appointment} removed wrapped in an {@code Optional}.
     */
    public Optional<Appointment> remove(String patient_id) {
        return Optional.ofNullable(typeMap.remove(patient_id));
    }


    /**
     * Returns if this storage map contains a mapping to the specified name.
     *
     * @return {@code true} if there is a {@code Appointment} mapped to the
     *      specified patient_id and {@code false} otherwise.
     */
    public boolean contains(String patient_id) {
        return typeMap.containsKey(patient_id);
    }


    /**
     * Returns an unmodifiable map view of this storage.
     *
     * @return an unmodifiable map view ot this storage.
     */
    public ObservableMap<String, Appointment> asUnmodifiableObservableMap() {
        return unmodifiableTypeMap;
    }
}
