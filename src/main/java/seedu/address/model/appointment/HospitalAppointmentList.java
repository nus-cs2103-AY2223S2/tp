package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
                patientAppointment.isSameAppointment(appointment));
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

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }
}
