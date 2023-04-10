package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.ST2334;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.testutil.ModuleBuilder;

class ModuleCodePredicateTest {

    @Test
    public void equals() {
        ModuleCodePredicate firstPredicate = new ModuleCodePredicate(true, CS1101S.getCode().toString(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());

        HashSet<Credit> credits = new HashSet<>();
        credits.add(new Credit("4"));
        HashSet<SemYear> semYears = new HashSet<>();
        semYears.add(new SemYear("Y2S1"));
        HashSet<Grade> grades = new HashSet<>();
        grades.add(new Grade("A"));
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("Mathematics And Sciences"));
        ModuleCodePredicate secondPredicate = new ModuleCodePredicate(true, ST2334.getCode().toString(),
                new HashSet<>(), credits, semYears, grades, tags);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        assertEquals(secondPredicate, secondPredicate);

        // same values -> returns true
        ModuleCodePredicate firstPredicateCopy = new ModuleCodePredicate(true, CS1101S.getCode().toString(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        assertEquals(firstPredicate, firstPredicateCopy);

        HashSet<Credit> creditsCopy = new HashSet<>();
        creditsCopy.add(new Credit("4"));
        HashSet<SemYear> semYearsCopy = new HashSet<>();
        semYearsCopy.add(new SemYear("Y2S1"));
        HashSet<Grade> gradesCopy = new HashSet<>();
        gradesCopy.add(new Grade("A"));
        HashSet<Tag> tagsCopy = new HashSet<>();
        tagsCopy.add(new Tag("Mathematics And Sciences"));
        ModuleCodePredicate secondPredicateCopy = new ModuleCodePredicate(true, ST2334.getCode().toString(),
                new HashSet<>(), creditsCopy, semYearsCopy, gradesCopy, tagsCopy);
        assertEquals(secondPredicate, secondPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ModuleCodePredicate predicate = new ModuleCodePredicate(true, CS1101S.getCode().toString(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        assertFalse(predicate.test(new ModuleBuilder().withCode("ST2334").build()));
    }

}
