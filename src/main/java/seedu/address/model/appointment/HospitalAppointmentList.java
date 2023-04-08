package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}. As such,
 * adding and updating of appointments uses Appointment#isSameAppointment(Appointment) for equality so as to
 * ensure that the appointment being added or updated is unique in terms of booking in the HospitalAppointmentList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class HospitalAppointmentList implements Iterable<Appointment> {
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if an appointment already exists in the addressbook.
     * @param appointment
     * @return true if appointment already exists and hence a duplicate, false otherwise
     */
    public boolean isADuplicateAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return internalList.stream().anyMatch(patientAppointment ->
                patientAppointment.isSamePatientAppointmentTime(appointment)
                || patientAppointment.isSameDrAppointmentTime(appointment));
    }

    /**
     * Adds an appointment to the HospitalAppointmentList.
     * @param appointment
     */
    public void bookAppointment(Appointment appointment) {
        requireNonNull(appointment);
        if (isADuplicateAppointment(appointment)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(appointment);
    }

    /**
     * Deletes an appointment to the HospitalAppointmentList.
     * @param appointment
     */
    public void deleteAppointment(Appointment appointment) {
        requireNonNull(appointment);

        //boolean isSuccessfulDeletion = false;
        Appointment toDelete = null;
        //boolean isSuccessfulDeletion = internalList.remove(appointment);
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).isSameAppointment(appointment)) {
                toDelete = internalList.remove(i);
            }
        }
        if (toDelete == null) {
            throw new AppointmentNotFoundException();
        }
    }


    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.equals(editedAppointment) && isADuplicateAppointment(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
    }

    public void setAppointments(HospitalAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HospitalAppointmentList // instanceof handles nulls
                && internalList.equals(((HospitalAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

}
