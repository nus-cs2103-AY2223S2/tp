package seedu.address.model.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Person;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public class UniqueAppointmentList implements Iterable<Appointment> {
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();

    public boolean contains(Appointment appointment) {
        requireNonNull(appointment);
        return internalList.stream().anyMatch(appointment::isSameAppointment);
    }

    public void bookAppointment(Appointment appointment) {
        requireNonNull(appointment);
        if (contains(appointment)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(appointment);
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }
}
