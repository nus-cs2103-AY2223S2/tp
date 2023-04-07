package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.session.exceptions.DuplicateSessionException;
import seedu.address.model.session.exceptions.SessionNotFoundException;

/**
 * A list of sessions that enforces uniqueness between its elements and does not allow nulls.
 * A session is considered unique by comparing using
 * {@code Session#isSameSession(Session)}. As such, adding and updating of
 * sessions uses Session#isSameSession(Session) for equality so as to ensure that the session being added or updated is
 * unique in terms of identity in the UniqueSessionList.
 * However, the removal of a session uses Session#equals(Object) so
 * as to ensure that the session with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Session#isSameSession(Session)
 */
public class UniqueSessionList implements Iterable<Session> {

    private final ObservableList<Session> internalList = FXCollections.observableArrayList();
    private final ObservableList<Session> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent session as the given argument.
     */
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Adds a session to the list.
     * The session must not already exist in the list.
     */
    public void add(Session toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSessionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the session {@code target} in the list with {@code editedSession}.
     * {@code target} must exist in the list.
     * The session identity of {@code editedSession} must not be the same as another existing session in the list.
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

        internalList.add(index, editedSession);
        internalList.remove(index + 1);
    }

    /**
     * Removes the equivalent session from the list.
     * The session must exist in the list.
     */
    public void remove(Session toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SessionNotFoundException();
        }
    }

    public void setSessions(UniqueSessionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code sessions}.
     * {@code sessions} must not contain duplicate sessions.
     */
    public void setSessions(List<Session> sessions) {
        requireAllNonNull(sessions);
        if (!sessionsAreUnique(sessions)) {
            throw new DuplicateSessionException();
        }

        internalList.setAll(sessions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Session> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Session> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.session.UniqueSessionList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.session.UniqueSessionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code sessions} contains only unique sessions.
     */
    private boolean sessionsAreUnique(List<Session> sessions) {
        for (int i = 0; i < sessions.size() - 1; i++) {
            for (int j = i + 1; j < sessions.size(); j++) {
                if (sessions.get(i).isSameSession(sessions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
