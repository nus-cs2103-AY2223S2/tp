package seedu.quickcontacts.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.quickcontacts.testutil.TypicalPersons.ALICE;
import static seedu.quickcontacts.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.testutil.MeetingBuilder;

public class MeetingContainsNamesPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MeetingContainsNamesPredicate firstPredicate = new MeetingContainsNamesPredicate(firstPredicateKeywordList);
        MeetingContainsNamesPredicate secondPredicate = new MeetingContainsNamesPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        MeetingContainsNamesPredicate firstPredicateCopy = new MeetingContainsNamesPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_meetingContainsNames_returnsTrue() {
        // One keyword
        MeetingContainsNamesPredicate predicate = new MeetingContainsNamesPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees(ALICE).build()));

        // Multiple keywords
        predicate = new MeetingContainsNamesPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees(ALICE, BOB).build()));

        // Only one matching keyword
        predicate = new MeetingContainsNamesPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees(ALICE, BOB).build()));

        // Mixed-case keywords
        predicate = new MeetingContainsNamesPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees(ALICE, BOB).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MeetingContainsNamesPredicate predicate = new MeetingContainsNamesPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withAttendees(ALICE).build()));

        // Non-matching keyword
        predicate = new MeetingContainsNamesPredicate(List.of("Carol"));
        assertFalse(predicate.test(new MeetingBuilder().withAttendees(ALICE, BOB).build()));
    }
}
