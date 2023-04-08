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
     * Check if the isolated event object is in the isolated event list.
     * @param startDate of which event to be added starts at.
     * @param endDate of which event to be added ends at.
     * @return
     */
    public IsolatedEvent checkClashingIsolatedEvent(LocalDateTime startDate, LocalDateTime endDate)
            throws EventConflictException {
        Iterator<IsolatedEvent> it = isolatedEvents.iterator();
        IsolatedEvent currEvent;
        int counter = 0;

        while (it.hasNext()) {
            currEvent = it.next();

            if (startDate.isBefore(currEvent.getEndDate()) && currEvent.getStartDate().isBefore(endDate)) {
                int index = counter + 1;
                throw new EventConflictException("Isolated Event List:\n" + index + ". " + currEvent);
            }
            counter++;
        }
        return null;
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

    /**
     * This function cross-check with the recurring event list to check for any conflicts
     * @param isolatedEvent is the event to be added
     * @param recurringEventList is the event list to be checked with
     * @throws EventConflictException if there is a conflicted event
     */
    public static void listConflictedEventWithRecurring(
            IsolatedEvent isolatedEvent, RecurringEventList recurringEventList) throws EventConflictException {

        LocalDateTime startPeriod = isolatedEvent.getStartDate();
        LocalDateTime endPeriod = isolatedEvent.getEndDate();

        int index = 1;
        for (RecurringEvent re : recurringEventList.getRecurringEvents()) {
            long count = re.numberOfDaysBetween(startPeriod, endPeriod, re.getDayOfWeek());

            if (count == -1) {
                continue;
            }

            LocalDateTime recurringEventDate = startPeriod.plusDays(count);
            LocalDateTime dummyEventStartDate = LocalDateTime.of(recurringEventDate.toLocalDate(), re.getStartTime());
            LocalDateTime dummyEventEndDate = LocalDateTime.of(recurringEventDate.toLocalDate(), re.getEndTime());

            boolean isEventBefore = false;
            boolean isEventAfter = false;

            if (!dummyEventStartDate.isAfter(startPeriod) && !dummyEventEndDate.isAfter(startPeriod)) {
                isEventBefore = true;
            }

            if (!dummyEventStartDate.isBefore(endPeriod) && !dummyEventEndDate.isBefore(endPeriod)) {
                isEventAfter = true;
            }

            if (!(isEventBefore || isEventAfter)) {
                throw new EventConflictException("Recurring Event List:\n" + index + " " + re);
            }
            index++;
        }
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
