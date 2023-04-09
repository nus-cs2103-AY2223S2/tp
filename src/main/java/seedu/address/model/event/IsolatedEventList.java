package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.event.exceptions.EventConflictException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Represents the list of {@code IsolatedEvent} that each {@code Person} has.
 */
public class IsolatedEventList {
    private final TreeSet<IsolatedEvent> isolatedEvents = new TreeSet<>();

    public TreeSet<IsolatedEvent> getIsolatedEvents() {
        return isolatedEvents;
    }

    /**
     * Gets the total number of event in the isolatedEcent
     * @return the size of the isolatedEvents
     */
    public int getSize() {
        return isolatedEvents.size();
    }

    /**
     * Insert the isolated event object into the isolated event list.
     * @param newEvent to be inserted.
     */
    public void insert(IsolatedEvent newEvent) {
        this.isolatedEvents.add(newEvent);
    }

    /**
     * Check if the isolated event list contains any isolated events that conflict with the given time.
     * @param startDate of which event to be added starts at.
     * @param endDate of which event to be added ends at.
     * @throws EventConflictException when a conflicting event is found
     */

    public void checkClashingIsolatedEvent(LocalDateTime startDate, LocalDateTime endDate)
            throws EventConflictException {
        Iterator<IsolatedEvent> it = isolatedEvents.iterator();
        IsolatedEvent currEvent;
        int index = 1;

        while (it.hasNext()) {
            currEvent = it.next();
            if (currEvent.occursBetween(startDate, endDate)) {
                throw new EventConflictException("Isolated Event: " + index + currEvent);
            }
            index++;
        }
    }

    /**
     * Check to see if the edited event time period clashes with the existing isolated events in the list.
     * @param event edited isolated event
     * @param index index of edited event
     * @throws EventConflictException to be thrown when it overlaps
     */
    public void checkOverlapping(IsolatedEvent event, int index) throws EventConflictException {
        Iterator<IsolatedEvent> it = isolatedEvents.iterator();
        LocalDateTime start = event.getStartDate();
        LocalDateTime end = event.getEndDate();
        IsolatedEvent currEvent;
        int counter = 0;

        while (it.hasNext()) {
            currEvent = it.next();
            if (counter == index) {
                continue;
            }
            if (start.isBefore(currEvent.getEndDate()) && currEvent.getStartDate().isBefore(end)) {
                throw new EventConflictException("Isolated Event List:\n" + index + ". " + currEvent);
            }
            counter++;
        }
    }

    public boolean contain(IsolatedEvent event) {
        return this.isolatedEvents.contains(event);
    }

    /**
     * Delete the isolated event from the isolated event list.
     * @param event to be deleted.
     */
    public void deleteIsolatedEvent(IsolatedEvent event) {
        isolatedEvents.remove(event);
    }

    /**
     * Get the isolated event in the isolated event list with the event's index.
     * @param index of the event.
     * @return IsolatedEventObject
     */
    public IsolatedEvent getIsolatedEvent(int index) {
        Iterator<IsolatedEvent> it = isolatedEvents.iterator();
        IsolatedEvent event = null;
        int counter = 0;
        while (it.hasNext()) {
            event = it.next();
            if (counter == index) {
                break;
            }
            counter++;
        }
        return event;
    }

    /**
     * Edit the current event in the isolated event list.
     * @param originalEvent to be edited.
     * @param editedEvent to be edited to.
     */
    public void edit(IsolatedEvent originalEvent, IsolatedEvent editedEvent) {
        if (!isolatedEvents.contains(originalEvent)) {
            throw new EventNotFoundException();
        }
        isolatedEvents.remove(originalEvent);
        isolatedEvents.add(editedEvent);
    }

    public void addAll(Set<IsolatedEvent> isolatedEvents) {
        this.isolatedEvents.addAll(isolatedEvents);
    }
    public ArrayList<IsolatedEvent> getList() {
        return new ArrayList<>(this.isolatedEvents);
    }
    public Set<IsolatedEvent> getSet() {
        return new TreeSet<>(this.isolatedEvents);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Isolated Events\n");
        int count = 1;
        for (IsolatedEvent ie : isolatedEvents) {
            output.append(count).append(". ").append(ie.toString()).append("\n");
            count++;
        }
        return output.toString();
    }
}
