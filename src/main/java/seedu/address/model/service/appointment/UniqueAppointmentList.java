package seedu.address.model.service.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.entity.person.Staff;
import seedu.address.model.entity.person.Technician;
import seedu.address.model.entity.person.exceptions.DuplicatePersonException;
import seedu.address.model.entity.person.exceptions.PersonNotFoundException;
import seedu.address.model.service.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.service.appointment.exceptions.DuplicateAppointmentException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of technicians that enforces uniqueness between its elements and does not allow nulls.
 * A technician is considered unique by comparing using
 * {@code Technician#isSameTechnician(Customer)}. As such, adding and updating of
 * technicians uses Technician#isSameStaff(Staff) for equality so as to
 * ensure that the technician being added or updated is
 * unique in terms of identity in the UniqueTechnicianList. However, the removal of a technician uses
 * Staff#equals(Object) so
 * that we ensure technicians with the same staff id will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Technician#isSameStaff(Staff)
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Technician as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Adds a technician to the list.
     * The technician must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the technician {@code target} in the list with {@code editedTechnician}.
     * {@code target} must exist in the list.
     * The identity of {@code editedTechnician} must not be the same as another existing technician in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setTechnicians(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setTechnicians(List<Appointment> technicians) {
        requireAllNonNull(technicians);
        if (!appointmentsAreUnique(technicians)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(technicians);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                        && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code appointments} contains only unique Appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
