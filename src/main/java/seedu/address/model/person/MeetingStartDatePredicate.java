package seedu.address.model.person;

import java.time.LocalDate;
import java.util.function.Predicate;

public class MeetingStartDatePredicate implements Predicate<MeetingWithPerson> {
/**
 * Tests that a {@code Person} lives in the given region
 */
    private LocalDate startDate;

    /**
     * Constructs a new predicate that tests for the target region <p>
     * Returned predicate will evaluate to {@code True} if meeting starts with meetingStart date. False Otherwise.
     * @param startDate target region that predicate will return {@code True} for
     */
    public MeetingStartDatePredicate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public boolean test(MeetingWithPerson m) {
        return m.getStart().toLocalDate().equals(startDate);
    }

}
