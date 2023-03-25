package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.course.exceptions.DuplicateSessionException;
import tfifteenfour.clipboard.model.course.exceptions.SessionNotFoundException;


public class UniqueSessionsList implements Iterable<Session> {

    private final ObservableList<Session> internalList = FXCollections.observableArrayList();
    private final ObservableList<Session> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Session as the given argument.
     */
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Adds a Session to the list.
     * The Session must not already exist in the list.
     */
    public void add(Session toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSessionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Session {@code target} in the list with {@code editedSession}.
     * {@code target} must exist in the list.
     * The Session identity of {@code editedSession} must not be the same as another existing Session in the list.
     */
    public void setSession(Session target, Session editedSession) {
        requireAllNonNull(target, editedSession);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new SessionNotFoundException();
        }

        if (!target.isSameSession(editedSession) && contains(editedSession)) {
            throw new DuplicateSessionException();
        }

        internalList.set(index, editedSession);
    }

    /**
     * Removes the equivalent Session from the list.
     * The Session must exist in the list.
     */
    public void remove(Session toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SessionNotFoundException();
        }
    }

    public void setSessions(UniqueSessionsList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Sessions}.
     * {@code Sessions} must not contain duplicate Sessions.
     */
    public void setSessions(List<Session> Sessions) {
        requireAllNonNull(Sessions);
        if (!SessionsAreUnique(Sessions)) {
            throw new DuplicateSessionException();
        }

        internalList.setAll(Sessions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Session> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as a modifiable {@code ObservableList}.
     */
    public ObservableList<Session> asModifiableObservableList() {
        return internalList;
    }

    @Override
    public Iterator<Session> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueSessionsList // instanceof handles nulls
                && internalList.equals(((UniqueSessionsList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Sessions} contains only unique Sessions.
     */
    private boolean SessionsAreUnique(List<Session> Sessions) {
        for (int i = 0; i < Sessions.size() - 1; i++) {
            for (int j = i + 1; j < Sessions.size(); j++) {
                if (Sessions.get(i).isSameSession(Sessions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
