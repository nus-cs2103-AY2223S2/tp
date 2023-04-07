package seedu.internship.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.UniqueEventList;

/**
 * Wraps all data at the event-catalogue level
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class EventCatalogue implements ReadOnlyEventCatalogue {
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

    public EventCatalogue() {}

    /**
     * Creates an EventCatalogue using the events in the {@code toBeCopied}
     */
    public EventCatalogue(ReadOnlyEventCatalogue toBeCopied) {
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
     * Resets the existing data of this {@code EventCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyEventCatalogue newData) {
        requireNonNull(newData);
        setEvents(newData.getEventList());
    }

    //// event-level operations

    /**
     * Returns true if a event is the same as another {@code event} exists in the event catalogue.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return this.events.contains(event);
    }


    /**
     * Adds a event to the event Catalogue.
     * The event must not already exist in the event Catalogue.
     */
    public void addEvent(Event p) {
        events.add(p);
    }


    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the event Catalogue.
     * The  {@code editedEvent} must not be the same as another existing event in the event Catalogue.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code event Catalogue}.
     * {@code key} must exist in the event Catalogue.
     */
    public void removeEvent(Event key) {
        this.events.remove(key);
    }


    public HashMap<LocalDate, List<Event>> findClashEvents() {
        return this.events.getClashEventHash();
    }

    //// util methods

    @Override
    public String toString() {
        return events.asUnmodifiableObservableList().size() + " events";
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventCatalogue // instanceof handles nulls
                && events.equals(((EventCatalogue) other).events));
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
}
