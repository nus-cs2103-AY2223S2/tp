package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

public class BetweenDatePredicateTest {
    @Test
    public void equals() {
        BetweenDatePredicate firstPredicate = new BetweenDatePredicate(
                new InterviewDate("2023-06-07 12:00 PM"), new InterviewDate("2023-06-08 12:00 PM"));
        BetweenDatePredicate secondPredicate = new BetweenDatePredicate(
                new InterviewDate("2023-04-07 01:00 PM"), new InterviewDate("2023-04-15 12:00 PM"));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BetweenDatePredicate firstPredicateCopy = new BetweenDatePredicate(
                new InterviewDate("2023-06-07 12:00 PM"), new InterviewDate("2023-06-08 12:00 PM"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different interview date -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewDateIsBetweenPredicate_returnsTrue() {
        // Interview date is within the predicate
        BetweenDatePredicate predicate = new BetweenDatePredicate(
                new InterviewDate("2023-03-16 01:00 PM"), new InterviewDate("2023-03-27 12:00 PM"));
        assertTrue(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-03-16 09:00 PM")).build()));

        // Interview date is one hour before the end date time
        assertTrue(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-03-27 11:00 AM")).build()));

        // Interview date is the same as end date time
        assertTrue(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-03-27 12:00 PM")).build()));
    }

    @Test
    public void test_interviewDateIsNotBetweenPredicate_returnsFalse() {
        // Interview date is few days before the predicate
        BetweenDatePredicate predicate = new BetweenDatePredicate(
                new InterviewDate("2023-03-16 01:00 PM"), new InterviewDate("2023-03-27 12:00 PM"));
        assertFalse(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-03-15 08:00 PM")).build()));

        // Interview date is few hours before the predicate
        assertFalse(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-03-16 08:00 AM")).build()));

        // Interview date is few hours after the predicate
        assertFalse(predicate.test(new InternshipBuilder()
                .withInterviewDate(new InterviewDate("2023-03-27 01:00 PM")).build()));
    }

    @Test
    public void test_interviewDateIsNull_returnsFalse() {
        BetweenDatePredicate predicate = new BetweenDatePredicate(
                new InterviewDate("2023-03-16 01:00 PM"), new InterviewDate("2023-03-27 12:00 PM"));
        assertFalse(predicate.test(new InternshipBuilder().withInterviewDate(null).build()));
    }
}
