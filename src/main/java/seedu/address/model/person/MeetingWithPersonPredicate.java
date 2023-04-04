package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Represents the start date and time to be used to check against
 * {@code Person} list of {@code Meetings}
 */

public class MeetingWithPersonPredicate implements Predicate<MeetingWithPerson> {
    /**
     * Tests that a {@code MeetingWithPerson} starts in given day
     */
    private Person p;


    /**
     * Constructs a new predicate that tests for the target region <p>
     * Returned predicate will evaluate to {@code True} if meeting starts with meetingStart date. False Otherwise.
     *
     * @param p target region that predicate will return {@code True} for
     */
    public MeetingWithPersonPredicate(Person p) {
        this.p= p;
    }


    public boolean test(MeetingWithPerson m) {

        return m.getPersonToMeet().equals(p);
    }

}
