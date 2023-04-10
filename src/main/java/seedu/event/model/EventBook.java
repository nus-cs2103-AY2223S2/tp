package seedu.event.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.event.model.event.Event;
import seedu.event.model.event.UniqueEventList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class EventBook implements ReadOnlyEventBook {

    private final UniqueEventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        events = new UniqueEventList();
    }

    public EventBook() {}

    /**
     * Creates an EventBook using the Events in the {@code toBeCopied}
     */
    public EventBook(ReadOnlyEventBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code EventBook} with {@code newData}.
     */
    public void resetData(ReadOnlyEventBook newData) {
        requireNonNull(newData);

        setEvents(newData.getEventList());
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the event book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the event book.
     * The event must not already exist in the event book.
     */
    public void addEvent(Event p) {
        events.add(p);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the event book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the event book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code EventBook}.
     * {@code key} must exist in the event book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Marks {@code target} from this {@code EventBook}.
     * {@code target} must exist in the event book.
     */
    public void markEvent(Event target) {
        events.mark(target);
    }

    /**
     * Unmarks {@code target} from this {@code EventBook}.
     * {@code target} must exist in the event book.
     */
    public void unmarkEvent(Event target) {
        events.unmark(target);
    }

    public void linkContact(Event event, Event linkedEvent) {
        events.linkContact(event, linkedEvent);
    }

    //// util methods

    @Override
    public String toString() {
        return events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventBook // instanceof handles nulls
                && events.equals(((EventBook) other).events));
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
}
