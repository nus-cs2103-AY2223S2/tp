package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Elderly;
import seedu.address.model.person.information.RiskLevel.Risk;
import seedu.address.testutil.ElderlyBuilder;

class RiskLevelIsEqualPredicateTest {
    @Test
    public void equals() {
        RiskLevelIsEqualPredicate<Elderly> firstPredicate = new RiskLevelIsEqualPredicate<>(Risk.HIGH);
        RiskLevelIsEqualPredicate<Elderly> secondPredicate = new RiskLevelIsEqualPredicate<>(Risk.LOW);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RiskLevelIsEqualPredicate<Elderly> firstPredicateCopy = new RiskLevelIsEqualPredicate<>(Risk.HIGH);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different riskLevel -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_riskLevelIsEqual_returnsTrue() {
        RiskLevelIsEqualPredicate<Elderly> predicate = new RiskLevelIsEqualPredicate<>(Risk.HIGH);
        assertTrue(predicate.test(new ElderlyBuilder().withRiskLevel("high").build()));
    }

    @Test
    public void test_riskLevelIsNotEqual_returnsFalse() {
        // Non-matching risk level
        RiskLevelIsEqualPredicate<Elderly> predicate = new RiskLevelIsEqualPredicate<>(Risk.HIGH);
        assertFalse(predicate.test(new ElderlyBuilder().withRiskLevel("low").build()));
        assertFalse(predicate.test(new ElderlyBuilder().withRiskLevel("medium").build()));
    }
}
