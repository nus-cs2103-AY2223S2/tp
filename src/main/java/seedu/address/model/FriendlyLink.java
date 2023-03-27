package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.pair.Pair;
import seedu.address.model.pair.UniquePairList;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.ElderlyNotFoundException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.VolunteerNotFoundException;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Region;


/**
 * Wraps all data at the friendly-link level
 * Duplicates are not allowed (by .isSamePerson, .isSamePair comparison)
 */
public class FriendlyLink implements ReadOnlyFriendlyLink {
    private final UniquePersonList<Elderly> elderly;
    private final UniquePersonList<Volunteer> volunteers;
    private final UniquePairList pairs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        pairs = new UniquePairList();
        elderly = new UniquePersonList<>();
        volunteers = new UniquePersonList<>();
    }

    public FriendlyLink() {}

    /**
     * Creates an FriendlyLink using the data in the {@code toBeCopied}
     *
     * @param toBeCopied FriendlyLink which data is to be copied.
     */
    public FriendlyLink(ReadOnlyFriendlyLink toBeCopied) {
        this();
        requireNonNull(toBeCopied);
        resetFriendlyLinkData(toBeCopied);
    }

    // Application-wide code

    /**
     * Resets the existing data of this {@code FriendlyLink} with {@code newData}.
     *
     * @param newData Replacement data.
     */
    public void resetFriendlyLinkData(ReadOnlyFriendlyLink newData) {
        requireNonNull(newData);
        setAllElderly(newData.getElderlyList());
        setVolunteers(newData.getVolunteerList());
        setPairs(newData.getPairList());
    }


    /**
     * Replaces the contents of the pair list with {@code pairs}.
     * {@code pairs} must not contain duplicate pairs.
     *
     * @param pairs List of pairs.
     */
    public void setPairs(List<Pair> pairs) {
        this.pairs.setPairs((pairs));
    }

    /**
     * Replaces the contents of the elderly list with {@code elderly}.
     * {@code elderly} must not contain duplicate elderly.
     *
     * @param elderly List of elderly.
     */
    public void setAllElderly(List<Elderly> elderly) {
        this.elderly.setPersons(elderly);
    }


    /**
     * Replaces the contents of the volunteer list with {@code volunteers}.
     * {@code volunteers} must not contain duplicate volunteers.
     *
     * @param volunteers List of volunteers.
     */
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers.setPersons(volunteers);
    }

    /**
     * Resets the existing elderly data of this {@code FriendlyLink} with {@code newData}.
     *
     * @param newData Replacement elderly data.
     */
    public void resetElderlyData(ReadOnlyElderly newData) {
        requireNonNull(newData);
        setAllElderly(newData.getElderlyList());
    }

    /**
     * Resets the existing volunteer data of this {@code FriendlyLink} with {@code newData}.
     *
     * @param newData Replacement volunteer data.
     */
    public void resetVolunteerData(ReadOnlyVolunteer newData) {
        requireNonNull(newData);
        setVolunteers(newData.getVolunteerList());
    }

    //// person-level operations

    /**
     * Returns true if an elderly with the same {@code nric} exists in the friendlyLink cache.
     */
    public boolean hasElderly(Nric nric) {
        requireNonNull(nric);
        return elderly.contains(nric);
    }

    /**
     * Returns true if an elderly with the same identity as {@code elderly} exists in the friendlyLink cache.
     *
     * @param e Elderly to be checked.
     * @return True if {@code e} exists, false otherwise.
     */
    public boolean hasElderly(Elderly e) {
        requireNonNull(e);
        return elderly.contains(e);
    }

    /**
     * Returns true if a volunteer with the same {@code nric} exists in the friendlyLink cache.
     */
    public boolean hasVolunteer(Nric nric) {
        requireNonNull(nric);
        return volunteers.contains(nric);
    }

    /**
     * Returns true if a volunteer with the same identity as {@code volunteer} exists in the friendlyLink cache.
     *
     * @param volunteer Volunteer to be checked.
     * @return True if {@code volunteer} exists, false otherwise.
     */
    public boolean hasVolunteer(Volunteer volunteer) {
        requireNonNull(volunteer);
        return volunteers.contains(volunteer);
    }

    /**
     * Adds an elderly to the friendlyLink cache.
     * The elderly must not already exist in the friendlyLink cache.
     *
     * @param e Elderly to be added.
     */
    public void addElderly(Elderly e) {
        elderly.add(e);
    }

    /**
     * Adds a volunteer to the friendlyLink cache.
     * The volunteer must not already exist in the friendlyLink cache.
     *
     * @param volunteer Volunteer to be added.
     */
    public void addVolunteer(Volunteer volunteer) {
        volunteers.add(volunteer);
    }

    /**
     * Retrieves the elderly with the given nric.
     * The elderly with such a nric must exist in FriendlyLink.
     *
     * @param nric Nric of the elderly.
     * @return The elderly with the name.
     */
    public Elderly getElderly(Nric nric) {
        requireNonNull(nric);
        try {
            return elderly.get(nric);
        } catch (PersonNotFoundException e) {
            throw new ElderlyNotFoundException();
        }
    }

    /**
     * Retrieves the volunteer with the given nric.
     * The volunteer with such a nric must exist in FriendlyLink.
     *
     * @param nric Nric of the volunteer.
     * @return The volunteer with the name.
     */
    public Volunteer getVolunteer(Nric nric) {
        requireNonNull(nric);
        try {
            return volunteers.get(nric);
        } catch (PersonNotFoundException e) {
            throw new VolunteerNotFoundException();
        }
    }

    /**
     * Replaces the given elderly {@code target} in the list with {@code editedElderly}.
     * {@code target} must exist in the friendlyLink cache.
     * The elderly identity of {@code editedElderly} must not be the same as
     * another existing elderly in the friendlyLink cache.
     *
     * @param target Elderly to edit.
     * @param editedElderly Replacement elderly.
     */
    public void setElderly(Elderly target, Elderly editedElderly) {
        requireNonNull(editedElderly);
        elderly.setPerson(target, editedElderly);
    }

    /**
     * Replaces the given volunteer {@code target} in the list with {@code editedVolunteer}.
     * {@code target} must exist in the friendlyLink cache.
     * The volunteer identity of {@code editedVolunteer} must not be the same as
     * another existing volunteer in the friendlyLink cache.
     *
     * @param target Volunteer to edit.
     * @param editedVolunteer Replacement volunteer.
     */
    public void setVolunteer(Volunteer target, Volunteer editedVolunteer) {
        requireNonNull(editedVolunteer);
        volunteers.setPerson(target, editedVolunteer);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the elderly list.
     *
     * @param key Elderly to remove.
     */
    public void removeElderly(Elderly key) {
        elderly.remove(key);
    }

    /**
     * Removes {@code key} from {@code FriendlyLink}.
     * {@code key} must exist in the volunteers list.
     *
     * @param key Volunteer to remove.
     */
    public void removeVolunteer(Volunteer key) {
        volunteers.remove(key);
    }

    //// pair-level operations

    /**
     * Returns true if a pair with the same identity as {@code pair} exists in FriendlyLink.
     *
     * @param pair Pair to check for in FriendlyLink.
     * @return True if pair exists in FriendlyLink, false otherwise.
     */
    public boolean hasPair(Pair pair) {
        requireNonNull(pair);
        return pairs.contains(pair);
    }

    /**
     * Adds a {@code pair} to {@code FriendlyLink}.
     * The pair must not already exist in {@code FriendlyLink}.
     *
     * @param pair Pair to add into FriendlyLink.
     */
    public void addPair(Pair pair) {
        this.addPair(pair.getElderly().getNric(), pair.getVolunteer().getNric());
    }

    /**
     * Adds a pair consisting of elderly with {@code elderlyNric} and volunteer with {@code volunteerNric}
     * to {@code FriendlyLink}.
     * The pair must not already exist in {@code FriendlyLink}.
     *
     * @param elderlyNric Nric of elderly.
     * @param volunteerNric Nric of volunteer.
     */
    public boolean addPair(Nric elderlyNric, Nric volunteerNric) {
        Elderly elderly = getElderly(elderlyNric);
        Volunteer volunteer = getVolunteer(volunteerNric);

        Region elderlyRegion = elderly.getRegion();
        Region volunteerRegion = volunteer.getRegion();

        Set<AvailableDate> elderlyAvailableDates = elderly.getAvailableDates();
        Set<AvailableDate> volunteerAvailableDates = volunteer.getAvailableDates();

        // check if region match. If does not match, issue a warning in feedback
        boolean issueWarning;
        if (!elderlyRegion.isMatch(volunteerRegion)) {
            issueWarning = true;
        } else {
            issueWarning = false;
        }

        // Checks if the availableDates are compatible between the elderly and volunteer
        if (AvailableDate.isAvailableDatesIntersecting(elderlyAvailableDates, volunteerAvailableDates)) {
            pairs.add(new Pair(elderly, volunteer));
            return issueWarning;
        }

        throw new IllegalArgumentException("The elderly cannot be paired with the volunteer "
                + "due to a clash in availability");
    }

    /**
     * Replaces the given pair {@code target} in the list with {@code editedPair}.
     * {@code target} must exist in FriendlyLink.
     * The pair identity of {@code editedPair} must not be the same as another existing pair in FriendlyLink.
     *
     * @param target Pair to edit.
     * @param editedPair Pair with edited information.
     */
    public void setPair(Pair target, Pair editedPair) {
        requireNonNull(editedPair);
        pairs.setPair(target, editedPair);
    }

    /**
     * Removes {@code target} from {@code FriendlyLink}.
     * {@code target} must exist in the pair list.
     *
     * @param target Pair to remove.
     */
    public void removePair(Pair target) {
        pairs.remove(target);
    }

    /**
     * Removes pair consisting of elderly with {@code elderlyNric} and volunteer with {@code volunteerNric}
     * from {@code FriendlyLink}.
     * The pair must exist in the pair list.
     *
     * @param elderlyNric Nric of elderly.
     * @param volunteerNric Nric of volunteer.
     */
    public void removePair(Nric elderlyNric, Nric volunteerNric) {
        pairs.remove(new Pair(getElderly(elderlyNric), getVolunteer(volunteerNric)));
    }

    //// util methods
    @Override
    public String toString() {
        return elderly.asUnmodifiableObservableList().size() + " elderly"
                + volunteers.asUnmodifiableObservableList().size() + " volunteers"
                + pairs.asUnmodifiableObservableList().size() + " pairs";
    }

    @Override
    public ObservableList<Elderly> getElderlyList() {
        return elderly.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Volunteer> getVolunteerList() {
        return volunteers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pair> getPairList() {
        return pairs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendlyLink // instanceof handles nulls
                && elderly.equals(((FriendlyLink) other).elderly)
                && volunteers.equals(((FriendlyLink) other).volunteers)
                && pairs.equals(((FriendlyLink) other).pairs));
    }

    @Override
    public int hashCode() {
        return elderly.hashCode()
                + volunteers.hashCode()
                + pairs.hashCode();
    }
}
