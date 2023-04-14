package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import tfifteenfour.clipboard.model.UniqueList;
import tfifteenfour.clipboard.model.course.exceptions.DuplicateSessionException;
import tfifteenfour.clipboard.model.course.exceptions.SessionNotFoundException;

/**
 * A list of sessions that enforces uniqueness between its elements and does not allow nulls.
 * A session is considered unique by comparing using {@code Session#isSameSession(Session)}. As such,
 * adding and updating of sessions uses Session#isSameSession(Session) for equality so as to ensure that
 * the session being added or updated is unique in terms of identity in the UniqueSessionsList. However,
 * the removal of a session uses Session#equals(Object) so as to ensure that the session with exactly
 * the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Session#isSameSession(Session)
 */
public class UniqueSessionsList extends UniqueList<Session> {

    @Override
    public UniqueSessionsList copy() {
        UniqueSessionsList copy = new UniqueSessionsList();
        this.internalList.forEach(session -> copy.add(session));

        return copy;
    }

    /**
     * Returns true if the list contains an equivalent Session as the given argument.
     */
    @Override
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Adds a Session to the list.
     * The Session must not already exist in the list.
     */
    @Override
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
    @Override
    public void set(Session target, Session editedSession) {
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
    @Override
    protected boolean elementsAreUnique(List<Session> sessions) {
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
