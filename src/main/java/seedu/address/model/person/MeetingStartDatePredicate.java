package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class MeetingStartDatePredicate implements Predicate<MeetingWithPerson> {
/**
 * Tests that a {@code Person} lives in the given region
 */
    private LocalDateTime startDate;

    /**
     * Constructs a new predicate that tests for the target region <p>
     * Returned predicate will evaluate to {@code True} if meeting starts with meetingStart date. False Otherwise.
     * @param startDate target region that predicate will return {@code True} for
     */
    public MeetingStartDatePredicate(LocalDateTime startDate) {
        this.startDate = startDate;
    }


    public boolean test(Meeting m) {
        return m.getStart().equals(startDate);
    }

    @Override
    public boolean test(MeetingWithPerson meetingWithPerson) {
        return false;
    }
}
