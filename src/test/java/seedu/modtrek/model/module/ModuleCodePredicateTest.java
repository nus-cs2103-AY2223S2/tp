package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.ST2334;

import org.junit.jupiter.api.Test;

import seedu.modtrek.testutil.ModuleBuilder;

class ModuleCodePredicateTest {

    @Test
    public void equals() {
        ModuleCodePredicate firstPredicate = new ModuleCodePredicate(CS1101S.getCode());
        ModuleCodePredicate secondPredicate = new ModuleCodePredicate(ST2334.getCode());

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleCodePredicate firstPredicateCopy = new ModuleCodePredicate(CS1101S.getCode());
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ModuleCodePredicate predicate = new ModuleCodePredicate(CS1101S.getCode());
        assertFalse(predicate.test(new ModuleBuilder().withCode("ST2334").build()));
    }

}
