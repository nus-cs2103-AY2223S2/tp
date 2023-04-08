package seedu.vms.storage.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.GroupName;
import seedu.vms.model.appointment.Appointment;

class JsonAdaptedAppointmentTest {;

    private final Index patientId = Index.fromOneBased(1);
    private final LocalDateTime startTime = LocalDateTime.now();
    private final LocalDateTime endTime = LocalDateTime.now().plusHours(1);
    private final GroupName vaccine = new GroupName("Test Vaccine");
    private final Boolean isCompleted = false;

    private Appointment appointment;
    private JsonAdaptedAppointment jsonAdaptedAppointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);
        jsonAdaptedAppointment = new JsonAdaptedAppointment(appointment);
    }

    @Test
    void toModelType() throws IllegalValueException {
        Appointment loadedAppointment = jsonAdaptedAppointment.toModelType();

        assertEquals(appointment.getPatient(), loadedAppointment.getPatient());
        assertEquals(appointment.getAppointmentTime(), loadedAppointment.getAppointmentTime());
        assertEquals(appointment.getAppointmentEndTime(), loadedAppointment.getAppointmentEndTime());
        assertEquals(appointment.getVaccination(), loadedAppointment.getVaccination());
        assertEquals(appointment.getStatus(), loadedAppointment.getStatus());
    }
    
}
