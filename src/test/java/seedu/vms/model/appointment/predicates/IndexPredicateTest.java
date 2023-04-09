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

class IndexPredicateTest {

    private IndexPredicate indexPredicate;

    @BeforeEach
    void setUp() {
        indexPredicate = new IndexPredicate(Index.fromOneBased(1));
    }

    @Test
    void test() {
        Index patientId = Index.fromOneBased(1);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        GroupName vaccine = new GroupName("Test Vaccine");
        Boolean isCompleted = false;
        Appointment appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);

        assertTrue(indexPredicate.test(appointment));

        patientId = Index.fromOneBased(2);
        appointment = new Appointment(patientId, startTime, endTime, vaccine, isCompleted);

        assertFalse(indexPredicate.test(appointment));
    }

    @Test
    void equals() {
        assertEquals(indexPredicate, indexPredicate);
        assertNotEquals(null, indexPredicate);

        IndexPredicate otherIndexPredicate;

        otherIndexPredicate = new IndexPredicate(Index.fromOneBased(1));
        assertEquals(indexPredicate, otherIndexPredicate);

        otherIndexPredicate = new IndexPredicate(Index.fromOneBased(2));
        assertNotEquals(indexPredicate, otherIndexPredicate);
    }
}
