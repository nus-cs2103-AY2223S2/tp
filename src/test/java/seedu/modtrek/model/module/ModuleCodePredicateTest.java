package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.ST2334;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.testutil.ModuleBuilder;

class ModuleCodePredicateTest {

    @Test
    public void equals() {
        ModuleCodePredicate firstPredicate = new ModuleCodePredicate(CS1101S.getCode().toString(),
                "", "", "", new HashSet<>());
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("Mathematics And Sciences"));
        ModuleCodePredicate secondPredicate = new ModuleCodePredicate(ST2334.getCode().toString(),
                "4", "Y2S1", "A", tags);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(secondPredicate.equals(secondPredicate));

        // same values -> returns true
        ModuleCodePredicate firstPredicateCopy = new ModuleCodePredicate(CS1101S.getCode().toString(),
                "", "", "", new HashSet<>());
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        HashSet<Tag> tagsCopy = new HashSet<>();
        tagsCopy.add(new Tag("Mathematics And Sciences"));
        ModuleCodePredicate secondPredicateCopy = new ModuleCodePredicate(ST2334.getCode().toString(),
                "4", "Y2S1", "A", tagsCopy);
        assertTrue(secondPredicate.equals(secondPredicateCopy));

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
        ModuleCodePredicate predicate = new ModuleCodePredicate(CS1101S.getCode().toString(),
                "", "", "", new HashSet<>());
        assertFalse(predicate.test(new ModuleBuilder().withCode("ST2334").build()));
    }

}
