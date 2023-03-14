package seedu.vms.model.appointment;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.vms.model.StorageModel;

/**
 * Wraps all data at the patient-manager level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentManager extends StorageModel<Appointment> implements ReadOnlyAppointmentManager {
    private final ObservableMap<Integer, Appointment> typeMap;
    private final ObservableMap<Integer, Appointment> unmodifiableTypeMap;

    /**
     * Constructs an empty {@code AppointmentManager}.
     */
    public AppointmentManager() {
        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }

    /**
     * Creates an AppointmentManager using the appointments in the {@code toBeCopied}
     */
    public AppointmentManager(ReadOnlyAppointmentManager toBeCopied) {
        super(toBeCopied);

        typeMap = FXCollections.observableHashMap();
        unmodifiableTypeMap = FXCollections.unmodifiableObservableMap(typeMap);
    }


    /**
     * Adds the specified {@code Appointment}. If there is another {@code Appointment}
     * with the same patient id as the specified, that {@code Appointment} is replaced.
     *
     * @param appointment - the {@code Appointment} to add.
     */
    public void add(Appointment appointment) {
        typeMap.put(appointment.getPatient().getZeroBased(), appointment);
    }


    /**
     * Returns the {@code Appointment} mapped to the specified patient id, wrapped in an
     * {@code Optional}. If there are no mappings to the specified patient id,
     * {@code Optional.empty} is returned instead.
     *
     * @param patientId - the patient id of the {@code Appointment} to retrieve.
     * @return the {@code Appointment} mapped to the specified name, wrapped in an
     *      {@code Optional}.
     */
    public Optional<Appointment> get(Integer patientId) {
        return Optional.ofNullable(typeMap.get(patientId));
    }


    /**
     * Removes the {@code Appointment} with the specified patient id. The {@code Appointment}
     * removed is returned and wrapped in an {@code Optional}. If there are no
     * mappings, to the specified patient id, nothing is done and
     * {@code Optional.empty} is returned.
     *
     * @param patientId - the patient id of the {@code Appointment} to remove.
     * @return the {@code Appointment} removed wrapped in an {@code Optional}.
     */
    public Optional<Appointment> remove(Integer patientId) {
        return Optional.ofNullable(typeMap.remove(patientId));
    }


    /**
     * Returns if this storage map contains a mapping to the specified patient id.
     *
     * @return {@code true} if there is a {@code Appointment} mapped to the
     *      specified patient id and {@code false} otherwise.
     */
    public boolean contains(Integer name) {
        return typeMap.containsKey(name);
    }


    /**
     * Returns an unmodifiable map view of this storage.
     *
     * @return an unmodifiable map view ot this storage.
     */
    public ObservableMap<Integer, Appointment> asUnmodifiableObservableMap() {
        return unmodifiableTypeMap;
    }
}
