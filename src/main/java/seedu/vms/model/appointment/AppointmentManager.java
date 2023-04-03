package seedu.vms.model.appointment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import seedu.vms.commons.core.ValueChange;
import seedu.vms.commons.core.index.Index;
import seedu.vms.model.GroupName;
import seedu.vms.model.IdData;
import seedu.vms.model.StorageModel;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.ReadOnlyPatientManager;
import seedu.vms.model.vaccination.VaxType;
import seedu.vms.model.vaccination.VaxTypeManager;

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
     * Removes all invalid appointments and returns the list of deleted.
     */
    public List<IdData<Appointment>> validate(ReadOnlyPatientManager patientManager, VaxTypeManager vaxTypeManager) {
        HashSet<Integer> validPatients = new HashSet<>(patientManager
                .getMapView()
                .keySet());
        HashSet<String> validVaxs = new HashSet<>(vaxTypeManager
                .asUnmodifiableObservableMap()
                .keySet());

        List<IdData<Appointment>> invalidAppointments = getMapView().entrySet().stream()
                .map(entry -> entry.getValue())
                .filter(entry -> !validPatients.contains(entry.getValue().getPatient().getZeroBased())
                        || !validVaxs.contains(entry.getValue().getVaccination().getName()))
                .collect(Collectors.toList());

        for (IdData<Appointment> appointment : invalidAppointments) {
            remove(appointment.getId());
        }

        return invalidAppointments;
    }


    /**
     * Validates patient changes in AppointmentManager.
     * Does not delete.
     */
    public List<IdData<Appointment>> validatePatientChange(ValueChange<IdData<Patient>> change) {
        List<IdData<Appointment>> invalidAppointments = new ArrayList<>();
        if (!change.getOldValue().equals(change.getNewValue())
                && change.getOldValue().isPresent()
                && change.getNewValue().isEmpty()) {
            Index patientToDelete = Index.fromZeroBased(change.getOldValue().get().getId());
            getMapView().entrySet().stream()
                    .filter(x->x.getValue().getValue().getPatient().equals(patientToDelete))
                    .forEach(x->invalidAppointments.add(x.getValue()));
        }
        return invalidAppointments;
    }

    /**
     * Handles patient changes in AppointmentManager.
     */
    public void handlePatientChange(ValueChange<IdData<Patient>> change) {
        if (!change.getOldValue().equals(change.getNewValue())
                && change.getOldValue().isPresent()
                && change.getNewValue().isEmpty()) {
            List<IdData<Appointment>> invalidAppointments = new ArrayList<>();
            Index patientToDelete = Index.fromZeroBased(change.getOldValue().get().getId());
            getMapView().entrySet().stream()
                    .filter(x->x.getValue().getValue().getPatient().equals(patientToDelete))
                    .forEach(x->invalidAppointments.add(x.getValue()));
            invalidAppointments.forEach(x->remove(x.getId()));
        }
    }

    /**
     * Validates vaccination changes in AppointmentManager.
     * Does not delete.
     */
    public List<IdData<Appointment>> validateVaccinationChange(ValueChange<VaxType> change) {
        List<IdData<Appointment>> invalidAppointments = new ArrayList<>();
        if (!change.getOldValue().equals(change.getNewValue())
                && change.getOldValue().isPresent()) {
            GroupName vaxToChange = change.getOldValue().get().getGroupName();
            getMapView().entrySet().stream()
                    .filter(x->x.getValue().getValue().getVaccination().equals(vaxToChange))
                    .forEach(x->invalidAppointments.add(x.getValue()));
        }
        return invalidAppointments;
    }

    /**
     * Handles vaccination changes in AppointmentManager.
     */
    public void handleVaccinationChange(ValueChange<VaxType> change) {
        if (!change.getOldValue().equals(change.getNewValue())
                && change.getOldValue().isPresent()) {
            List<IdData<Appointment>> invalidAppointments = new ArrayList<>();
            GroupName vaxToChange = change.getOldValue().get().getGroupName();
            getMapView().entrySet().stream()
                    .filter(x->x.getValue().getValue().getVaccination().equals(vaxToChange))
                    .forEach(x->invalidAppointments.add(x.getValue()));
            if (change.getNewValue().isPresent()) {
                // update
                GroupName vaxToEdit = change.getNewValue().get().getGroupName();
                invalidAppointments.forEach(x->set(x.getId(), x.getValue().setVaccination(vaxToEdit)));
            } else {
                // delete
                invalidAppointments.forEach(x->remove(x.getId()));
            }
        }
    }
}
