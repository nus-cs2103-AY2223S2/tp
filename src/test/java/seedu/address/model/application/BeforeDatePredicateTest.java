package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class BeforeDatePredicateTest {
    @Test
    public void equals() {
        BeforeDatePredicate firstPredicate = new BeforeDatePredicate(new InterviewDate("2023-06-07 12:00 PM"));
        BeforeDatePredicate secondPredicate = new BeforeDatePredicate(new InterviewDate("2023-06-07 01:00 PM"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BeforeDatePredicate firstPredicateCopy =
                new BeforeDatePredicate(new InterviewDate("2023-06-07 12:00 PM"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different interview date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewDateIsBeforePredicate_returnsTrue() {
        // Interview date is few days before the predicate
        BeforeDatePredicate predicate =
                new BeforeDatePredicate(new InterviewDate("2023-04-16 01:00 PM"));
        assertTrue(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-04-13 01:00 PM")).build()));

        // Interview date is just one hour before the predicate
        assertTrue(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-04-16 12:00 PM")).build()));

        // Interview date is the same as predicate
        assertTrue(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-04-16 01:00 PM")).build()));
    }

    @Test
    public void test_interviewDateIsAfterPredicate_returnsFalse() {
        // Interview date is few days after the predicate
        BeforeDatePredicate predicate =
                new BeforeDatePredicate(new InterviewDate("2023-04-01 01:00 PM"));
        assertFalse(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-04-15 08:00 PM")).build()));

        // Interview date is few hours after the predicate
        assertFalse(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-04-01 08:00 PM")).build()));
    }

    @Test
    public void test_interviewDateIsNull_returnsFalse() {
        BeforeDatePredicate predicate =
                new BeforeDatePredicate(new InterviewDate("2023-04-01 01:00 PM"));
        assertFalse(predicate.test(new InternshipBuilder().withInterviewDate(null).build()));
    }
}
