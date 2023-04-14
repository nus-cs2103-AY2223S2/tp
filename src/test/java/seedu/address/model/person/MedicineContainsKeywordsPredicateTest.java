package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MedicineContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MedicineContainsKeywordsPredicate firstPredicate =
                new MedicineContainsKeywordsPredicate(firstPredicateKeywordList);
        MedicineContainsKeywordsPredicate secondPredicate =
                new MedicineContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MedicineContainsKeywordsPredicate firstPredicateCopy =
                new MedicineContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        MedicineContainsKeywordsPredicate predicate =
                new MedicineContainsKeywordsPredicate(Collections.singletonList("Aspirin"));
        assertTrue(predicate.test(new PersonBuilder().withMedicines("Aspirin").build()));

        // Multiple keywords
        predicate = new MedicineContainsKeywordsPredicate(Arrays.asList("Aspirin", "Paracetamol"));
        assertTrue(predicate.test(new PersonBuilder().withMedicines("Aspirin", "Paracetamol").build()));

        // Only one matching keyword
        predicate = new MedicineContainsKeywordsPredicate(Arrays.asList("Aspirin", "Paracetamol"));
        assertTrue(predicate.test(new PersonBuilder().withMedicines("Aspirin").build()));

        // Mixed-case keywords
        predicate = new MedicineContainsKeywordsPredicate(Arrays.asList("AspiRIN", "ParacetAMOL"));
        assertTrue(predicate.test(new PersonBuilder().withMedicines("AspiRIN", "ParacetAMOL").build()));
    }

}
