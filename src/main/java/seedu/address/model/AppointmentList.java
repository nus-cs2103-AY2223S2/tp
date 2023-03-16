package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AppointmentList implements ReadOnlyAppointmentList {
    private final UniqueAppointmentList appointments;

    {
        appointments = new UniqueAppointmentList();
    }

    public AppointmentList() {}

    public AppointmentList(ReadOnlyAppointmentList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    public void resetData(ReadOnlyAppointmentList newData) {
        requireNonNull(newData);
        setAppointments(newData.getAppointmentList());
    }

    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        appointments.setAppointment(target, editedAppointment);
    }

    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    @Override
    public String toString() {
        return appointments.asUnmodifiableObservableList().size() + "appointments";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentList
                && appointments.equals(((AppointmentList) other).appointments));
    }
    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }
}
