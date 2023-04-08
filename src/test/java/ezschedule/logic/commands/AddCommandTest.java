package ezschedule.logic.commands;

import static ezschedule.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.GuiSettings;
import ezschedule.logic.commands.exceptions.CommandException;
import ezschedule.model.Model;
import ezschedule.model.ReadOnlyScheduler;
import ezschedule.model.ReadOnlyUserPrefs;
import ezschedule.model.Scheduler;
import ezschedule.model.event.Event;
import ezschedule.testutil.EventBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddCommand addCommand = new AddCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EVENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_eventExistAtTime_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        Event validOverlapEvent = new EventBuilder().withStartTime("09:00").withEndTime("11:00").build();
        AddCommand addCommand = new AddCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validOverlapEvent);

        assertThrows(CommandException.class, AddCommand.MESSAGE_EVENT_EXIST_AT_TIME, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void commandWord_returnSuccessful() {
        Event validEvent = new EventBuilder().build();
        AddCommand addCommand = new AddCommand(validEvent);
        assertEquals(AddCommand.COMMAND_WORD, addCommand.commandWord());
    }

    @Test
    public void equals() {
        Event a = new EventBuilder().withName("Event A").build();
        Event b = new EventBuilder().withName("Event B").build();
        AddCommand addEventACommand = new AddCommand(a);
        AddCommand addEventBCommand = new AddCommand(b);

        // same object -> returns true
        assertTrue(addEventACommand.equals(addEventACommand));

        // same values -> returns true
        AddCommand addEventACommandCopy = new AddCommand(a);
        assertTrue(addEventACommand.equals(addEventACommandCopy));

        // different types -> returns false
        assertFalse(addEventACommand.equals(1));

        // null -> returns false
        assertFalse(addEventACommand.equals(null));

        // different event -> returns false
        assertFalse(addEventACommand.equals(addEventBCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getSchedulerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSchedulerFilePath(Path schedulerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyScheduler getScheduler() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScheduler(ReadOnlyScheduler newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEventAtTime(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Command> recentCommands() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Event> recentEvent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecentEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearRecent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getUpcomingEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFindEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateUpcomingEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFindEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.equals(event);
        }

        @Override
        public boolean hasEventAtTime(Event event) {
            requireNonNull(event);
            return this.event.isEventOverlap(event);
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();
        final ArrayList<Command> recentCommand = new ArrayList<>();
        final ArrayList<Event> recentEvent = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::equals);
        }

        @Override
        public boolean hasEventAtTime(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isEventOverlap);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ArrayList<Command> recentCommands() {
            return recentCommand;
        }

        @Override
        public ArrayList<Event> recentEvent() {
            return recentEvent;
        }

        @Override
        public void clearRecent() {
            recentCommand.clear();
            recentEvent.clear();
        }

        @Override
        public ReadOnlyScheduler getScheduler() {
            return new Scheduler();
        }
    }
}
