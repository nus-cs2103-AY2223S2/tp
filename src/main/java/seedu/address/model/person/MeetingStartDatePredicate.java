package seedu.address.model.person;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Predicate that tests if a meeting with a person starts on a specific date
 */
public class MeetingStartDatePredicate implements Predicate<MeetingWithPerson> {
    /**
     * Tests that a {@code MeetingWithPerson} starts in given day
     */
    private LocalDate startDate;

    /**
     * Constructs a new predicate that tests for the target datE <p>
     * Returned predicate will evaluate to {@code True} if meeting starts with meetingStart date. False Otherwise.
     * @param startDate target date that predicate will return {@code True} for
     */
    public MeetingStartDatePredicate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public boolean test(MeetingWithPerson m) {
        return m.getStart().toLocalDate().equals(startDate);
    }

}
