package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Volunteer;
import seedu.address.testutil.VolunteerBuilder;

class SkillLevelIsEqualPredicateTest {
    @Test
    public void equals() {
        SkillLevelIsEqualPredicate<Volunteer> firstPredicate = new SkillLevelIsEqualPredicate<>("advance");
        SkillLevelIsEqualPredicate<Volunteer> secondPredicate = new SkillLevelIsEqualPredicate<>("basic");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SkillLevelIsEqualPredicate<Volunteer> firstPredicateCopy = new SkillLevelIsEqualPredicate<>("advance");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different skill Level -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_skillLevelIsEqual_returnsTrue() {
        // exact skill level match
        SkillLevelIsEqualPredicate<Volunteer> predicate = new SkillLevelIsEqualPredicate<>("advanced");
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("cpr advanced").build()));

        // case insensitive
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("cpr ADVANCED").build()));

        // different skill type
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("ekg advanced").build()));

        // one match out of many
        assertTrue(predicate.test(new VolunteerBuilder()
                .withMedicalTags("cpr advanced", "ekg basic", "Injections intermediate").build()));
    }

    @Test
    public void test_skillLevelIsNotEqual_returnsFalse() {
        // Non-matching skill level
        SkillLevelIsEqualPredicate<Volunteer> predicate = new SkillLevelIsEqualPredicate<>("advanced");
        assertFalse(predicate.test(new VolunteerBuilder().withMedicalTags("cpr basic").build()));
        assertFalse(predicate.test(new VolunteerBuilder().withMedicalTags("cpr intermediate").build()));

        // skill type has same name as skill level
        assertFalse(predicate.test(new VolunteerBuilder().withMedicalTags("advanced basic").build()));

        // multiple skill level without match
        assertFalse(predicate.test(new VolunteerBuilder()
                .withMedicalTags("cpr basic", "ekg basic", "Injections intermediate").build()));
    }
}
