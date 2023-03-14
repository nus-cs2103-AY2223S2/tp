package seedu.fitbook.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fitbook.testutil.ClientBuilder;

public class AppointmentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsKeywordsPredicate firstPredicate = new AppointmentContainsKeywordsPredicate(
                firstPredicateKeywordList);
        AppointmentContainsKeywordsPredicate secondPredicate = new AppointmentContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentContainsKeywordsPredicate firstPredicateCopy = new AppointmentContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_appointmentContainsKeywords_returnsTrue() {
        // One keyword
        AppointmentContainsKeywordsPredicate predicate = new AppointmentContainsKeywordsPredicate(
                Collections.singletonList("12-12-2020"));
        assertTrue(predicate.test(new ClientBuilder().withAppointments("12-12-2020").build()));

        predicate = new AppointmentContainsKeywordsPredicate(Arrays.asList("12-12-2020", "11-12-2020"));
        assertTrue(predicate.test(new ClientBuilder().withAppointments("12-12-2020", "11-12-2020").build()));
    }

    @Test
    public void test_appointmentDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AppointmentContainsKeywordsPredicate predicate = new AppointmentContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withAppointments("12-12-2020").build()));

        // Non-matching keyword
        predicate = new AppointmentContainsKeywordsPredicate(Collections.singletonList("12-12-2020"));
        assertFalse(predicate.test(new ClientBuilder().withAppointments("10-12-2020", "11-12-2020").build()));
    }
}
