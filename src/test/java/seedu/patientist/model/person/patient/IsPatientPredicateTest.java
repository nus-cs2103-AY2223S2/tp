package seedu.patientist.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.TypicalPatients.BOB;
import static seedu.patientist.testutil.TypicalStaff.AMY;

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

    @Test
    public void test() {
        // BOB is patient -> true
        assertTrue(new IsPatientPredicate().test(BOB));

        // AMY is staff -> false
        assertFalse(new IsPatientPredicate().test(AMY));
    }
}
