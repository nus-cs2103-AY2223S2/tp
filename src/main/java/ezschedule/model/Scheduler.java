package ezschedule.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import ezschedule.model.event.Event;
import ezschedule.model.event.UniqueEventList;
import ezschedule.model.event.UpcomingEventPredicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Wraps all data at the scheduler level
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class Scheduler implements ReadOnlyScheduler {
    private static final int DISPLAY_UPCOMING_COUNT = 1;
    private static final UpcomingEventPredicate predicate = new UpcomingEventPredicate(DISPLAY_UPCOMING_COUNT);

    private final UniqueEventList events;

    private FilteredList<Event> upcomingEvents;

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

    /**
     * Construct an instance of Scheduler object.
     * Listeners are attached in here.
     */
    public Scheduler() {
        upcomingEvents = new FilteredList<>(getEventList());

        // Attach a listener to auto-sort events in chronological order
        events.addListChangeListener(c -> {
            while (c.next()) {
                if (!c.wasPermutated()) {
                    events.sortByChronologicalOrder();
                }
            }
        });

        events.addListChangeListener(c -> {
            while (c.next()) { /* Do nothing */ }
            upcomingEvents.setPredicate(predicate);
        });

    }

    /**
     * Creates a Scheduler using the Events in the {@code toBeCopied}
     */
    public Scheduler(ReadOnlyScheduler toBeCopied) {
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
     * Resets the existing data of this {@code Scheduler} with {@code newData}.
     */
    public void resetData(ReadOnlyScheduler newData) {
        requireNonNull(newData);
        setEvents(newData.getEventList());
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the scheduler.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Returns true if another event exists at the given date and time in the Scheduler.
     */
    public boolean hasEventAtTime(Event event) {
        requireNonNull(event);
        return events.existsAtTime(event);
    }

    /**
     * Adds an event to the scheduler.
     * The event must not already exist in the scheduler.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the scheduler.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the scheduler.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code Scheduler}.
     * {@code key} must exist in the scheduler
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Returns the list of upcoming {@code Event}
     *
     * @return
     */
    public FilteredList<Event> getUpcomingEvents() {
        return upcomingEvents;
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
                || (other instanceof Scheduler // instanceof handles nulls
                && events.equals(((Scheduler) other).events));
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
}
