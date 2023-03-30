package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Volunteer;
import seedu.address.testutil.VolunteerBuilder;

class MedicalQualificationContainsKeywordPredicateTest {
    @Test
    public void equals() {
        MedicalQualificationContainsKeywordPredicate<Volunteer> firstPredicate =
                new MedicalQualificationContainsKeywordPredicate<>("cpr");
        MedicalQualificationContainsKeywordPredicate<Volunteer> secondPredicate =
                new MedicalQualificationContainsKeywordPredicate<>("ekg");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MedicalQualificationContainsKeywordPredicate<Volunteer> firstPredicateCopy =
                new MedicalQualificationContainsKeywordPredicate<>("cpr");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_medicalQualificationContainsKeyword_returnsTrue() {
        // exact match
        MedicalQualificationContainsKeywordPredicate<Volunteer> predicate =
                new MedicalQualificationContainsKeywordPredicate<>("cpr");
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("cpr basic").build()));

        // substring match
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("acpr basic").build()));

        // substring match with numbers
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("2a1cpr44 basic").build()));

        // case insensitive
        assertTrue(predicate.test(new VolunteerBuilder().withMedicalTags("CPR basic").build()));
    }

    @Test
    public void test_medicalQualificationDoesNotContainKeyword_returnsFalse() {
        // completely different
        MedicalQualificationContainsKeywordPredicate<Volunteer> predicate =
                new MedicalQualificationContainsKeywordPredicate<>("cpr");
        assertFalse(predicate.test(new VolunteerBuilder().withMedicalTags("hello basic").build()));

        // subsequence
        assertFalse(predicate.test(new VolunteerBuilder().withMedicalTags("coprring basic").build()));

        // anagram
        assertFalse(predicate.test(new VolunteerBuilder().withMedicalTags("pcr basic").build()));
    }

    @Test
    public void test_emptyMedicalQualification_throwsIllegalArgumentException() {
        String invalidMedicalQualification = "";
        assertThrows(IllegalArgumentException.class, () ->
                new MedicalQualificationContainsKeywordPredicate<>(invalidMedicalQualification));
    }
}
