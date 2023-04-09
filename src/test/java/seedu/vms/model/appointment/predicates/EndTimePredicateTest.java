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

class EndTimePredicateTest {

    private LocalDateTime now;
    private EndTimePredicate endTimePredicate;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        endTimePredicate = new EndTimePredicate(now.plusHours(1));
    }

    @Test
    void test() {
        Index patientId = Index.fromOneBased(1);
        LocalDateTime startTime = now.plusMinutes(5);
        LocalDateTime endTime = now.plusHours(1);
        GroupName vaccine = new GroupName("Test Vaccine");
        Boolean isCompleted = false;
        Appointment appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);

        assertTrue(endTimePredicate.test(appointment));

        endTime = endTime.plusMinutes(5);
        appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);

        assertFalse(endTimePredicate.test(appointment));
    }

    @Test
    void equals() {
        assertEquals(endTimePredicate, endTimePredicate);
        assertNotEquals(null, endTimePredicate);

        EndTimePredicate otherStartTimePredicate;

        otherStartTimePredicate = new EndTimePredicate(now.plusHours(1));
        assertEquals(endTimePredicate, otherStartTimePredicate);

        otherStartTimePredicate = new EndTimePredicate(now.plusMinutes(5));
        assertNotEquals(endTimePredicate, otherStartTimePredicate);
    }
}
