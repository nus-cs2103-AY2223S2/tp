package seedu.vms.model.appointment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.GroupName;

class AppointmentManagerTest {

    private AppointmentManager appointmentManager;

    @BeforeEach
    void setUp() {
        appointmentManager = new AppointmentManager();
    }

    @Test
    void mark() {
        Index patientId = Index.fromOneBased(1);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        GroupName vaccine = new GroupName("Test Vaccine");
        Boolean isCompleted = false;

        appointmentManager.set(1, new Appointment(patientId, startTime, endTime, vaccine, isCompleted));

        assertDoesNotThrow(() -> appointmentManager.mark(1));
        assertThrows(AssertionError.class, () -> appointmentManager.mark(1));
    }

    @Test
    void unmark() {
        Index patientId = Index.fromOneBased(1);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        GroupName vaccine = new GroupName("Test Vaccine");
        Boolean isCompleted = true;

        appointmentManager.set(1, new Appointment(patientId, startTime, endTime, vaccine, isCompleted));

        assertDoesNotThrow(() -> appointmentManager.unmark(1));
        assertThrows(AssertionError.class, () -> appointmentManager.unmark(1));
    }
}
