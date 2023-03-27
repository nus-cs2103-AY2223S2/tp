package seedu.internship.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internship.model.event.exceptions.DuplicateEventException;
import seedu.internship.model.event.exceptions.EventNotFoundException;

/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * An event is considered unique by comparing using {@code Event#isSameEvent(Event)}.
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {
    public static final ObservableList<Event> EMPTY_UNIQUE_EVENTS_LIST = FXCollections.observableArrayList();

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds an event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
    }

    private List<Event> getClashEvents(Event clashEvent) {
        requireNonNull(clashEvent);
        return internalList.stream().filter(e -> !e.isDeadline()).filter(e -> clashEvent.isClash(e))
                .collect(Collectors.toList());
    }

    public HashMap<Event, List<Event>> getClashEventList() {
        HashMap<Event, List<Event>> hash = new HashMap<>();
        for(Event event : internalList) {
            List<Event> lst = getClashEvents(event);
            if (!lst.isEmpty() && !hash.containsKey(event)) {
                hash.put(event, lst);
            }
        }
        return hash;
    }

    /**
     * Replaces the event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the
     * list.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        if (!target.isSameEvent(editedEvent) && contains(editedEvent)) {
            throw new DuplicateEventException();
        }

        internalList.set(index, editedEvent);
    }

    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }



    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof UniqueEventList) {
            UniqueEventList otherList = (UniqueEventList) other;
            if (this == EMPTY_UNIQUE_EVENTS_LIST || otherList == EMPTY_UNIQUE_EVENTS_LIST) {
                return false;
            }
            return this.internalList.equals(otherList.internalList);
        }
        return false;


    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code event} contains only unique event.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
