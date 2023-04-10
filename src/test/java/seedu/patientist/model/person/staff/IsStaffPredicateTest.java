package seedu.patientist.model.person.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.testutil.TypicalPatients.BOB;
import static seedu.patientist.testutil.TypicalStaff.AMY;

import org.junit.jupiter.api.Test;

public class IsStaffPredicateTest {

    @Test
    public void equals() {
        IsStaffPredicate first = new IsStaffPredicate();
        IsStaffPredicate second = new IsStaffPredicate();

        // same object -> true
        assertTrue(first.equals(first));

        // different object -> true
        assertTrue(first.equals(second));
    }

    @Test
    public void test() {
        // AMY is staff -> true
        assertTrue(new IsStaffPredicate().test(AMY));

        // BOB is patient -> false
        assertFalse(new IsStaffPredicate().test(BOB));
    }
}
