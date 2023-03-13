package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsPatientPredicateTest {

    @Test
    public void equals() {
        IsPatientPredicate firstPredicate = new IsPatientPredicate();
        IsPatientPredicate secondPredicate = new IsPatientPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different object -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));
    }
}
