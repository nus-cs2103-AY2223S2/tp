package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Predicate that tests if a meeting is scheduled with a specific person
 */
public class MeetingWithPersonPredicate implements Predicate<MeetingWithPerson> {
    /**
     * Tests that a {@code MeetingWithPerson} is a meeting with a given person
     */
    private Person p;


    /**
     * Constructs a new predicate that tests for the target person <p>
     * Returned predicate will evaluate to {@code True} if meeting is with person p. False Otherwise.
     *
     * @param p target person that predicate will return {@code True} for
     */
    public MeetingWithPersonPredicate(Person p) {
        this.p = p;
    }


    public boolean test(MeetingWithPerson m) {
        return m.getPersonToMeet().equals(p);
    }

}
