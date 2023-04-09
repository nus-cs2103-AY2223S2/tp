package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.Participants;
import seedu.address.model.meetup.UniqueMeetUpList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.User;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.recommendation.UniqueRecommendationList;
import seedu.address.model.util.SampleDataUtil;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EduMate implements ReadOnlyEduMate {

    private static final Logger logger = LogsCenter.getLogger(EduMate.class);

    protected Participants participants;
    private final UniquePersonList persons;
    private final UniqueMeetUpList meets;
    private User user;
    private final UniqueRecommendationList recommendations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        user = SampleDataUtil.getSampleUser();
        meets = new UniqueMeetUpList();
        participants = new Participants();
        recommendations = new UniqueRecommendationList();
    }

    public EduMate() {}

    /**
     * Creates an EduMate using the Persons in the {@code toBeCopied}
     */
    public EduMate(ReadOnlyEduMate toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with an empty list of persons.
     */
    public void resetPersons() {
        this.persons.setPersons(new ArrayList<>());
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations.setRecommendations(recommendations);
    }

    /**
     * Replaces the contents of the person list with an empty list of recommendations.
     */
    public void resetRecommendations() {
        this.recommendations.setRecommendations(new ArrayList<>());
    }

    /**
     * Sets the user of the Address Book.
     */
    public void setUser(User user) {
        requireNonNull(user);
        this.user = user;
    }

    /**
     * Resets the existing data of this {@code EduMate} with {@code newData}.
     */
    public void resetData(ReadOnlyEduMate newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setRecommendations(newData.getRecommendationList());
        setUser(newData.getUser());
        setParticipants(newData.getParticipantList());
        setMeetUps(newData.getMeetUpList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code EduMate}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// recommendation-level operations

    /**
     * Returns true if a recommendation with the same identity as
     * {@code recommendation} exists in the address book.
     */
    public boolean hasRecommendation(Recommendation person) {
        requireNonNull(person);
        return recommendations.contains(person);
    }

    /**
     * Adds a recommendation to the address book.
     * The recommendation must not already exist in the address book.
     */
    public void addRecommendation(Recommendation p) {
        recommendations.add(p);
    }

    /**
     * Replaces the given recommendation {@code target} in the list with {@code editedRecommendation}.
     * {@code target} must exist in the address book.
     * The recommendation identity of {@code editedRecommendation} must not
     * be the same as another existing person in the address book.
     */
    public void setRecommendation(Recommendation target, Recommendation editedRecommendation) {
        requireNonNull(editedRecommendation);

        recommendations.setRecommendation(target, editedRecommendation);
    }

    /**
     * Removes {@code key} from this {@code EduMate}.
     * {@code key} must exist in the address book.
     */
    public void removeRecommendation(Recommendation key) {
        recommendations.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        persons.asUnmodifiableObservableList().forEach(person -> {
            sb.append(person);
            sb.append("\n");
        });
        return sb.toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Recommendation> getRecommendationList() {
        return recommendations.asUnmodifiableObservableList();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EduMate)) {
            return false;
        }

        EduMate otherEduMate = (EduMate) other;

        logger.info("Persons equality: "
                + persons.equals(otherEduMate.persons));
        logger.info("User equality: "
                + user.equals(otherEduMate.user));
        logger.info("Meets equality: "
                + meets.equals(otherEduMate.meets));

        return persons.equals(otherEduMate.persons)
                && user.equals(otherEduMate.user)
                && meets.equals(otherEduMate.meets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, user);
    }

    //// meetup methods
    /**
     * Replaces the contents of meet up list with {@code meetUps}.
     * @param meetUps List of meet ups.
     */
    public void setMeetUps(List<MeetUp> meetUps) {
        this.meets.setMeetUps(meetUps);
    }

    /**
     * Replaces the contents of the meet ups list with an empty list of meet ups.
     */
    public void resetMeetUps() {
        this.meets.setMeetUps(new ArrayList<>());
    }

    @Override
    public ObservableList<MeetUp> getMeetUpList() {
        return meets.asUnmodifiableObservableList();
    }

    /**
     * Returns true if a meet up with the same identity as
     * {@code meetUp} exists in the address book.
     */
    public boolean hasMeetUp(MeetUp meetUp) {
        requireNonNull(meetUp);
        return meets.contains(meetUp);
    }

    public void addMeetUp(MeetUp meetUp) {
        meets.add(meetUp);
    }

    //// participants methods

    public Participants getParticipantList() {
        return participants;
    }

    /**
     * Replaces the contents of participants with {@code participants}.
     * @param participants New participants.
     */
    public void setParticipants(Participants participants) {
        this.participants = participants;
    }

    /**
     * Updates participants list of latest meet command.
     */
    public void editParticipants(Person target, Person editedPerson) {
        if (participants.containsParticipant(target)) {
            List<Person> personList = participants.getParticipants();
            personList.removeIf(person -> person.isSamePerson(target));
            personList.add(editedPerson);
            participants.setParticipants(personList);
        }
    }

    public void removeMeetUp(MeetUp meetUp) {
        meets.remove(meetUp);
    }

    public void removeEmptyMeetUps() {
        meets.removeEmptyMeetUps();
    }
}
