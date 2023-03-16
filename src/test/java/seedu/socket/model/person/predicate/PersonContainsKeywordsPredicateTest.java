package seedu.socket.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void testEquals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonContainsKeywordsPredicate firstPersonPredicate = new PersonContainsKeywordsPredicate(
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondNamePersonPredicate = new PersonContainsKeywordsPredicate(
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondProfilePersonPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondPhonePersonPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondEmailPersonPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondAddressPersonPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondLanguagePersonPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondTagPersonPredicate = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(secondNamePersonPredicate.equals(secondNamePersonPredicate));
        assertTrue(firstPersonPredicate.equals(firstPersonPredicate));
        assertTrue(secondProfilePersonPredicate.equals(secondProfilePersonPredicate));
        assertTrue(secondPhonePersonPredicate.equals(secondPhonePersonPredicate));
        assertTrue(secondEmailPersonPredicate.equals(secondEmailPersonPredicate));
        assertTrue(secondAddressPersonPredicate.equals(secondAddressPersonPredicate));
        assertTrue(secondLanguagePersonPredicate.equals(secondLanguagePersonPredicate));
        assertTrue(secondTagPersonPredicate.equals(secondTagPersonPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPersonPredicateCopy = new PersonContainsKeywordsPredicate(
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondNamePersonPredicateCopy = new PersonContainsKeywordsPredicate(
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondProfilePersonPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondPhonePersonPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondEmailPersonPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondAddressPersonPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondLanguagePersonPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondTagPersonPredicateCopy = new PersonContainsKeywordsPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList);

        assertTrue(firstPersonPredicate.equals(firstPersonPredicateCopy));
        assertTrue(secondNamePersonPredicate.equals(secondNamePersonPredicateCopy));
        assertTrue(secondProfilePersonPredicate.equals(secondProfilePersonPredicateCopy));
        assertTrue(secondPhonePersonPredicate.equals(secondPhonePersonPredicateCopy));
        assertTrue(secondEmailPersonPredicate.equals(secondEmailPersonPredicateCopy));
        assertTrue(secondAddressPersonPredicate.equals(secondAddressPersonPredicateCopy));
        assertTrue(secondLanguagePersonPredicate.equals(secondLanguagePersonPredicateCopy));
        assertTrue(secondTagPersonPredicate.equals(secondTagPersonPredicateCopy));

        // different types -> returns false
        assertFalse(firstPersonPredicate.equals(1));
        assertFalse(secondNamePersonPredicate.equals(1));
        assertFalse(secondProfilePersonPredicate.equals(1));
        assertFalse(secondPhonePersonPredicate.equals(1));
        assertFalse(secondEmailPersonPredicate.equals(1));
        assertFalse(secondAddressPersonPredicate.equals(1));
        assertFalse(secondLanguagePersonPredicate.equals(1));
        assertFalse(secondTagPersonPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPersonPredicate.equals(null));
        assertFalse(secondNamePersonPredicate.equals(null));
        assertFalse(secondProfilePersonPredicate.equals(null));
        assertFalse(secondPhonePersonPredicate.equals(null));
        assertFalse(secondEmailPersonPredicate.equals(null));
        assertFalse(secondAddressPersonPredicate.equals(null));
        assertFalse(secondLanguagePersonPredicate.equals(null));
        assertFalse(secondTagPersonPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPersonPredicate.equals(secondNamePersonPredicate));
        assertFalse(secondNamePersonPredicate.equals(firstPersonPredicate));
        assertFalse(secondProfilePersonPredicate.equals(firstPersonPredicate));
        assertFalse(secondPhonePersonPredicate.equals(firstPersonPredicate));
        assertFalse(secondEmailPersonPredicate.equals(firstPersonPredicate));
        assertFalse(secondAddressPersonPredicate.equals(firstPersonPredicate));
        assertFalse(secondLanguagePersonPredicate.equals(firstPersonPredicate));
        assertFalse(secondTagPersonPredicate.equals(firstPersonPredicate));
    }
}
