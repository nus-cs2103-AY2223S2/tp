package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

public class HospitalAppointmentList implements Iterable<Appointment> {
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    public boolean isADuplicateAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return internalList.stream().anyMatch(patientAppointment ->
                patientAppointment.isSameAppointment(appointment));
    }
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
