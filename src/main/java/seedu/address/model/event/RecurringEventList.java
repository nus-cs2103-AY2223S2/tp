package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.event.exceptions.EventConflictException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.time.TimeMask;

/**
 * Represents the list of {@code RecurringEvent} that each {@code Person} has.
 */
public class RecurringEventList {
    private final TreeSet<RecurringEvent> recurringEvents = new TreeSet<>();

    private final TimeMask recurringMask = new TimeMask();

    public TreeSet<RecurringEvent> getRecurringEvents() {
        return recurringEvents;
    }


    /**
     * Gets the total number of event in the recurringEvents
     * @return the size of the recurringEvents
     */
    public int getSize() {
        return recurringEvents.size();
    }

    /**
     * Insert the recurring event object into the recurring event list
     * @param newEvent to be inserted
     */
    public void insert(RecurringEvent newEvent) {
        this.recurringEvents.add(newEvent);
    }

    /**
     * Check if the isolated event object is in the isolated event list.
     * @param recurringEvent of which event to be added
     * @return
     */
    public RecurringEvent checkClashingRecurringEvent(RecurringEvent recurringEvent) {
        Iterator<RecurringEvent> it = recurringEvents.iterator();
        RecurringEvent currEvent;

        int count = 0;
        while (it.hasNext()) {
            currEvent = it.next();

            if (recurringEvent.compareTo(currEvent) == 0) {
                return currEvent;
            }
            count++;
        }
        return null;
    }

    /**
     * Check if a recurring event exist within the recurring event list
     * @param event to be checked if exist
     * @return true if there exist a same event and false if the event does exist
     *      in the event list
     */
    public boolean contain(RecurringEvent event) {
        return recurringEvents.contains(event);
    }

    /**
     * Get the recurring event in the recurring event list with the event's index.
     * @param index of the event.
     * @return recurringEventObject
     */
    public RecurringEvent getRecurringEvent(int index) {
        Iterator<RecurringEvent> it = recurringEvents.iterator();
        RecurringEvent recurringEvent = null;
        int counter = 0;
        while (it.hasNext()) {
            recurringEvent = it.next();
            if (counter == index) {
                break;
            }
            counter++;
        }
        return recurringEvent;
    }

    /**
     * This function cross-check with the isolated event list to check for any conflicts
     * @param recurringEvent is the event to be added
     * @param isolatedEventList is the event list to be checked with
     * @throws EventConflictException if there is a conflicted event
     */
    public static void listConflictedEventWithIsolated(
            RecurringEvent recurringEvent, IsolatedEventList isolatedEventList) throws EventConflictException {

        int index = 1;
        for (IsolatedEvent ie : isolatedEventList.getIsolatedEvents()) {
            LocalDateTime startPeriod = ie.getStartDate();
            LocalDateTime endPeriod = ie.getEndDate();

            long count = recurringEvent.numberOfDaysBetween(startPeriod, endPeriod, recurringEvent.getDayOfWeek());

            if (count == -1) {
                continue;
            }

            LocalDateTime recurringEventDate = startPeriod.plusDays(count);

            LocalDateTime dummyEventStartDate =
                    LocalDateTime.of(recurringEventDate.toLocalDate(), recurringEvent.getStartTime());

            LocalDateTime dummyEventEndDate =
                    LocalDateTime.of(recurringEventDate.toLocalDate(), recurringEvent.getEndTime());

            boolean isEventBefore = false;
            boolean isEventAfter = false;

            if (!dummyEventStartDate.isAfter(startPeriod) && !dummyEventEndDate.isAfter(startPeriod)) {
                isEventBefore = true;
            }

            if (!dummyEventStartDate.isBefore(endPeriod) && !dummyEventEndDate.isBefore(endPeriod)) {
                isEventAfter = true;
            }

            if (!(isEventBefore || isEventAfter)) {
                throw new EventConflictException("Isolated Event List:\n" + index + " " + ie);
            }

        }
    }

    /**
     * Checks if the newly edited recurring event clashes with any preexisting recurring events
     * @param person that the recurring event below
     * @param newlyEditedRecurringEvent the new event to replace the original event
     * @param original recurring event to be replaced
     * @throws EventConflictException
     */
    public static void checkForClashesInRecurringEvent(Person person, RecurringEvent newlyEditedRecurringEvent,
                                                       RecurringEvent original) throws EventConflictException {
        RecurringEventList recurringEventList = person.getRecurringEventList();

        for (int i = 0; i < recurringEventList.getSize(); i++) {

            RecurringEvent curRecurringEvent = recurringEventList.getRecurringEvent(i);

            if (curRecurringEvent.equals(original)) {
                continue;
            }

            if (curRecurringEvent.getDayOfWeek().equals(newlyEditedRecurringEvent.getDayOfWeek())) {
                boolean isEventInFront = curRecurringEvent.getStartTime().isBefore(newlyEditedRecurringEvent
                        .getStartTime())
                        && !curRecurringEvent.getEndTime().isAfter(newlyEditedRecurringEvent.getStartTime());

                boolean isEventBack = curRecurringEvent.getEndTime().isAfter(newlyEditedRecurringEvent.getEndTime())
                        && !curRecurringEvent.getStartTime().isBefore(newlyEditedRecurringEvent.getEndTime());

                if (!isEventInFront && !isEventBack) {
                    throw new EventConflictException(curRecurringEvent.toString());
                }
            }

        }

    }

    /**
     * Add all the recurring events into the person's recurring event list and update the recurring event list's time
     * mask.
     * @param recurringEvents
     */
    public void addAll(Set<RecurringEvent> recurringEvents) {
        this.recurringEvents.addAll(recurringEvents);
        for (RecurringEvent recurringEvent: recurringEvents) {
            recurringMask.modifyOccupancy(recurringEvent, true);
        }
    }

    public ArrayList<RecurringEvent> getList() {
        return new ArrayList<>(this.recurringEvents);
    }
    public Set<RecurringEvent> getSet() {
        return new TreeSet<>(this.recurringEvents);
    }

    /**
     * Prints out a list of all event that occur within the given time period
     * @param startPeriod stand for the starting date of the time period
     * @param endPeriod stands for the ending date of the time period
     * @return a string of all events that occured within the time period
     */
    public String listBetweenOccurrence(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        StringBuilder output = new StringBuilder();
        for (RecurringEvent re : recurringEvents) {
            if (re.occursBetween(startPeriod, endPeriod)) {
                output.append(re).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Delete the recurring event from the person's recurring event list and update the time mask of the recurring
     * event list.
     * @param event
     */
    public void deleteRecurringEvent(RecurringEvent event) {
        recurringEvents.remove(event);
        recurringMask.modifyOccupancy(event, false);
    }

    /**
     * Edit recurring event parameters in the recurring event list
     * @param originalEvent to be edited
     * @param editedRecurringEvent to be edited to
     */
    public void edit(RecurringEvent originalEvent, RecurringEvent editedRecurringEvent) {
        if (!recurringEvents.contains(originalEvent)) {
            throw new EventNotFoundException();
        }
        recurringEvents.remove(originalEvent);
        recurringEvents.add(editedRecurringEvent);

        recurringMask.modifyOccupancy(originalEvent, false);
        recurringMask.modifyOccupancy(editedRecurringEvent, true);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Recurring Events\n");
        int count = 1;
        for (RecurringEvent re : recurringEvents) {
            output.append(count).append(". ").append(re.toString()).append("\n");
            count++;
        }
        return output.toString();
    }


    public TimeMask getRecurringMask() {
        return recurringMask;
    }


}
