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

        FindCommandPersonPredicate firstPersonPredicate = new FindCommandPersonPredicate(
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList);
        FindCommandPersonPredicate secondNamePersonPredicate = new FindCommandPersonPredicate(
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondProfilePersonPredicate = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondPhonePersonPredicate = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondEmailPersonPredicate = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondAddressPersonPredicate = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondLanguagePersonPredicate = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondTagPersonPredicate = new FindCommandPersonPredicate(
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
        FindCommandPersonPredicate firstPersonPredicateCopy = new FindCommandPersonPredicate(
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList,
                        firstPredicateKeywordList);
        FindCommandPersonPredicate secondNamePersonPredicateCopy = new FindCommandPersonPredicate(
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondProfilePersonPredicateCopy = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondPhonePersonPredicateCopy = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondEmailPersonPredicateCopy = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondAddressPersonPredicateCopy = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondLanguagePersonPredicateCopy = new FindCommandPersonPredicate(
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                firstPredicateKeywordList,
                secondPredicateKeywordList,
                firstPredicateKeywordList);
        FindCommandPersonPredicate secondTagPersonPredicateCopy = new FindCommandPersonPredicate(
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
