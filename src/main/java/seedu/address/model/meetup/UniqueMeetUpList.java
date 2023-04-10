package seedu.address.model.meetup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meetup.exceptions.DuplicateMeetUpException;
import seedu.address.model.meetup.exceptions.MeetUpNotFoundException;


/**
 * A list of meet ups that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueMeetUpList implements Iterable<MeetUp> {

    private final ObservableList<MeetUp> internalList = FXCollections.observableArrayList();
    private final ObservableList<MeetUp> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent MeetUp as the given argument.
     */
    public boolean contains(MeetUp toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(other -> other.isSameMeetUp(toCheck));
    }

    /**
     * Adds a MeetUp to the list.
     * The MeetUp must not already exist in the list.
     */
    public void add(MeetUp toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetUpException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the MeetUp {@code target} in the list with {@code editedMeetUp}.
     * {@code target} must exist in the list.
     * The MeetUp identity of {@code editedMeetUp}
     * must not be the same as another existing MeetUp in the list.
     */
    public void setMeetUp(MeetUp target, MeetUp editedMeetUp) {
        requireAllNonNull(target, editedMeetUp);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetUpNotFoundException();
        }

        if (!target.isSameMeetUp(editedMeetUp) && contains(editedMeetUp)) {
            throw new DuplicateMeetUpException();
        }

        internalList.set(index, editedMeetUp);
    }

    /**
     * Removes the equivalent MeetUp from the list.
     * The MeetUp must exist in the list.
     */
    public void remove(MeetUp toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetUpNotFoundException();
        }
    }

    /**
     * Removes meet ups with no more participants.
     */
    public void removeEmptyMeetUps() {
        Iterator<MeetUp> iterator = internalList.iterator();
        while (iterator.hasNext()) {
            MeetUp meetUp = iterator.next();
            if (meetUp.getParticipants().getParticipants().isEmpty()) {
                iterator.remove();
            }
        }
    }

    /**
     * Replaces the MeetUp with a new one.
     */
    public void setMeetUps(UniqueMeetUpList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetUps}.
     * {@code meetUps} must not contain duplicate MeetUps.
     */
    public void setMeetUps(List<MeetUp> meetUps) {
        requireAllNonNull(meetUps);
        if (!meetUpsAreUnique(meetUps)) {
            throw new DuplicateMeetUpException();
        }
        internalList.setAll(meetUps);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<MeetUp> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<MeetUp> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMeetUpList // instanceof handles nulls
                && internalList.equals(((UniqueMeetUpList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetUps} contains only unique recommendations.
     */
    private boolean meetUpsAreUnique(List<MeetUp> meetUps) {
        for (int i = 0; i < meetUps.size() - 1; i++) {
            for (int j = i + 1; j < meetUps.size(); j++) {
                if (meetUps.get(i).isSameMeetUp(meetUps.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
