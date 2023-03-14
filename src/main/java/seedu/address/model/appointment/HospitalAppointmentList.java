package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
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
