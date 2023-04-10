package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.recommendation.Recommendation;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyEduMate {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the recommendations list.
     * This list will not contain any duplicate recommendations.
     */
    ObservableList<Recommendation> getRecommendationList();

    /**
     * Returns a view of the user object.
     * This will always be non-null.
     */
    User getUser();
    /**
     * Returns an unmodifiable view of the meet ups lists.
     */
    ObservableList<MeetUp> getMeetUpList();

    Participants getParticipantList();

}
