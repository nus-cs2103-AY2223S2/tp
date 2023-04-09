package seedu.vms.model.appointment.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.GroupName;
import seedu.vms.model.appointment.Appointment;

class StartTimePredicateTest {

    private LocalDateTime now;
    private StartTimePredicate startTimePredicate;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        startTimePredicate = new StartTimePredicate(now);
    }

    @Test
    void test() {
        Index patientId = Index.fromOneBased(1);
        LocalDateTime startTime = now.plusMinutes(5);
        LocalDateTime endTime = now.plusHours(1);
        GroupName vaccine = new GroupName("Test Vaccine");
        Boolean isCompleted = false;
        Appointment appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);

        assertTrue(startTimePredicate.test(appointment));

        startTime = now.minusMinutes(5);
        appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);

        assertFalse(startTimePredicate.test(appointment));
    }

    @Test
    void equals() {
        assertEquals(startTimePredicate, startTimePredicate);
        assertNotEquals(null, startTimePredicate);

        StartTimePredicate otherStartTimePredicate;

        otherStartTimePredicate = new StartTimePredicate(now);
        assertEquals(startTimePredicate, otherStartTimePredicate);

        otherStartTimePredicate = new StartTimePredicate(now.plusMinutes(5));
        assertNotEquals(startTimePredicate, otherStartTimePredicate);
    }
}
