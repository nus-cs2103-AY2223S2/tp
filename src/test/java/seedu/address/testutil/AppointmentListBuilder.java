package seedu.address.testutil;

import seedu.address.model.AppointmentList;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class to help with building AppointmentList objects.
 * Example usage: <br>
 *     {@code AppointmentList al = new AppointmentListBuilder().withAppointment().build();}
 */
public class AppointmentListBuilder {

    private AppointmentList appointmentList;

    public AppointmentListBuilder() {
        appointmentList = new AppointmentList();
    }

    public AppointmentListBuilder(AppointmentList appointmentList) {
        this.appointmentList = appointmentList;
    }

    /**
     * Adds a new {@code Appointment} to the {@code AppointmentList} that we are building.
     */
    public AppointmentListBuilder withAppointment(Appointment appointment) {
        appointmentList.addAppointment(appointment);
        return this;
    }

    public AppointmentList build() {
        return appointmentList;
    }
}
