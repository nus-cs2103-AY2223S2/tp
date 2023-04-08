package seedu.vms.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.GroupName;


public class AppointmentTest {

    private final Index patientId = Index.fromOneBased(1);
    private final LocalDateTime startTime = LocalDateTime.now();
    private final LocalDateTime endTime = LocalDateTime.now().plusHours(1);
    private final GroupName vaccine = new GroupName("Test Vaccine");
    private final Boolean isCompleted = false;

    private final Appointment appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);


    @Test
    public void getPatient() {
        assertEquals(appointment.getPatient(), patientId);
    }

    @Test
    public void getAppointmentTime() {
        assertEquals(appointment.getAppointmentTime(), startTime);
    }

    @Test
    public void getAppointmentEndTime() {
        assertEquals(appointment.getAppointmentEndTime(), endTime);
    }

    @Test
    public void getVaccination() {
        assertEquals(appointment.getVaccination(), vaccine);
    }

    @Test
    public void getStatus() {
        assertEquals(appointment.getStatus(), isCompleted);
    }

    @Test
    public void setVaccination() {
        GroupName newVaccine = new GroupName("new Vaccine");
        Appointment newAppointment = appointment.setVaccination(newVaccine);

        assertEquals(newAppointment.getVaccination(), newVaccine);
    }

    @Test
    public void mark() {
        Appointment newAppointment = new Appointment(patientId, startTime, endTime, vaccine, false);

        assertTrue(newAppointment.mark().getStatus());
    }

    @Test
    public void unmark() {
        Appointment newAppointment = new Appointment(patientId, startTime, endTime, vaccine, true);

        assertFalse(newAppointment.unmark().getStatus());
    }

    @Test
    public void isInvalidAppointmentTime() {
        // startTime now -> returns true
        assertTrue(Appointment.isInvalidAppointmentTime(LocalDateTime.now()));

        // startTime now + 1s -> returns false
        assertFalse(Appointment.isInvalidAppointmentTime(LocalDateTime.now().plusSeconds(1)));

        // startTime now - 1s -> returns true
        assertTrue(Appointment.isInvalidAppointmentTime(LocalDateTime.now().minusSeconds(1)));
    }

    @Test
    public void isValidDuration() {
        // startTime == endTime -> returns true
        assertTrue(Appointment.isValidDuration(LocalDateTime.now(), LocalDateTime.now()));

        // startTime is after endTime -> returns false
        assertFalse(Appointment.isValidDuration(LocalDateTime.now().plusHours(1), LocalDateTime.now()));

        // startTime is before endTime -> returns true
        assertTrue(Appointment.isValidDuration(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
    }

    @Test
    public void equals() {
        Appointment appointmentCopy = new Appointment(appointment.getPatient(),
                appointment.getAppointmentTime(),
                appointment.getAppointmentEndTime(),
                appointment.getVaccination(),
                appointment.getStatus());

        // same object -> returns true
        assertTrue(appointment.equals(appointment));

        // same values -> returns true
        assertTrue(appointment.equals(appointmentCopy));

        // null -> returns false
        assertFalse(appointment.equals(null));

        Appointment appointmentCopyDiff;

        // different patientId -> returns false
        appointmentCopyDiff = new Appointment(Index.fromOneBased(appointment.getPatient().getOneBased() + 1),
                appointment.getAppointmentTime(),
                appointment.getAppointmentEndTime(),
                appointment.getVaccination(),
                appointment.getStatus());
        assertFalse(appointment.equals(appointmentCopyDiff));

        // different startTime -> returns false
        appointmentCopyDiff = new Appointment(appointment.getPatient(),
                appointment.getAppointmentTime().minusHours(1),
                appointment.getAppointmentEndTime(),
                appointment.getVaccination(),
                appointment.getStatus());
        assertFalse(appointment.equals(appointmentCopyDiff));

        // different endTime -> returns false
        appointmentCopyDiff = new Appointment(appointment.getPatient(),
                appointment.getAppointmentTime(),
                appointment.getAppointmentEndTime().plusHours(1),
                appointment.getVaccination(),
                appointment.getStatus());
        assertFalse(appointment.equals(appointmentCopyDiff));

        // different vaccine -> returns false
        appointmentCopyDiff = new Appointment(appointment.getPatient(),
                appointment.getAppointmentTime(),
                appointment.getAppointmentEndTime(),
                new GroupName(appointment.getVaccination().getName() + "Test"),
                appointment.getStatus());
        assertFalse(appointment.equals(appointmentCopyDiff));

        // different isCompleted -> returns false
        appointmentCopyDiff = new Appointment(appointment.getPatient(),
                appointment.getAppointmentTime(),
                appointment.getAppointmentEndTime(),
                appointment.getVaccination(),
                !appointment.getStatus());
        assertFalse(appointment.equals(appointmentCopyDiff));
    }
}
