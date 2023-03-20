package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.ST2334;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.testutil.ModuleBuilder;

class ModulePredicateTest {

    @Test
    public void equals() {
        ModulePredicate firstPredicate = new ModulePredicate(CS1101S.getCode().toString(),
                "", "", "", new HashSet<>());
        ModulePredicate secondPredicate = new ModulePredicate(ST2334.getCode().toString(),
                "", "", "", new HashSet<>());

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModulePredicate firstPredicateCopy = new ModulePredicate(CS1101S.getCode().toString(),
                "", "", "", new HashSet<>());
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
        ModulePredicate predicate = new ModulePredicate(CS1101S.getCode().toString(), "", "", "", new HashSet<>());
        assertFalse(predicate.test(new ModuleBuilder().withCode("ST2334").build()));
    }

}
