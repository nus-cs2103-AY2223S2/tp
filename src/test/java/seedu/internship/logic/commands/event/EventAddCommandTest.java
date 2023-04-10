package seedu.internship.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.event.EventAddCommand.MESSAGE_EVENT_CLASH_WARNING;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalInternships.ML1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ReadOnlyEventCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.ReadOnlyUserPrefs;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;
import seedu.internship.testutil.EventBuilder;
import seedu.internship.testutil.TypicalEvents;

public class EventAddCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        EventAddCommandTest.ModelStubAcceptingEventAdded modelStub =
                new EventAddCommandTest.ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();
        CommandResult commandResult = new EventAddCommand(validEvent).execute(modelStub);

        assertEquals(String.format(EventAddCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessfulWithWarning() throws Exception {
        EventAddCommandTest.ModelStubAcceptingEventAdded modelStub =
                new EventAddCommandTest.ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().withStart("09/04/2023 1500").withEnd("09/04/2023 1900").build();
        Event clashEvent = new EventBuilder().withStart("09/04/2023 1700").withEnd("09/04/2023 2100").build();
        modelStub.addEvent(validEvent);
        CommandResult commandResult = new EventAddCommand(clashEvent).execute(modelStub);

        assertEquals(String.format(MESSAGE_EVENT_CLASH_WARNING, clashEvent), commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        EventAddCommand addCommand = new EventAddCommand(validEvent);
        EventAddCommandTest.ModelStub modelStub = new EventAddCommandTest.ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, EventAddCommand.MESSAGE_DUPLICATE_EVENT, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void execute_eventWithoutSelecting_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        EventAddCommand addCommand = new EventAddCommand(validEvent);
        EventAddCommandTest.ModelStub modelStub = new EventAddCommandTest.ModelStubWithEvent(validEvent);
        modelStub.clearSelectedInternship();
        assertThrows(CommandException.class, EventAddCommand.MESSAGE_NO_INTERNSHIP_SELECTED, () ->
                addCommand.execute(modelStub));
    }


    @Test
    public void execute_eventWithEndBeforeStart_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        Event invalidEvent = new EventBuilder()
                .withStart(validEvent.getEnd().getNumericDateTimeString())
                .withEnd(validEvent.getStart().getNumericDateTimeString())
                .build();
        EventAddCommand addCommand = new EventAddCommand(invalidEvent);
        EventAddCommandTest.ModelStub modelStub = new EventAddCommandTest.ModelStubWithEvent(invalidEvent);

        assertThrows(CommandException.class, EventAddCommand.MESSAGE_END_BEFORE_START, () ->
                addCommand.execute(modelStub));
    }


    @Test
    public void equals() {
        Event ml2 = TypicalEvents.EM11;
        Event se3 = TypicalEvents.EMD1;
        EventAddCommand addEm11Command = new EventAddCommand(ml2);
        EventAddCommand addEmd1Command = new EventAddCommand(se3);

        // same object -> returns true
        assertTrue(addEm11Command.equals(addEm11Command));

        // same values -> returns true
        EventAddCommand addEM11CommandCopy = new EventAddCommand(ml2);
        assertTrue(addEm11Command.equals(addEM11CommandCopy));


        // different types -> returns false
        assertFalse(addEm11Command.equals(1));


        // null -> returns false
        assertFalse(addEm11Command.equals(null));

        // different internship -> returns false
        assertFalse(addEm11Command.equals(addEmd1Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        final ArrayList<Event> eventsAdded = new ArrayList<>();
        private Internship selectedInternship = ML1;

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public Path getInternshipCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipCatalogueFilePath(Path internshipCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipCatalogue(ReadOnlyInternshipCatalogue newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipCatalogue getInternshipCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternship(Internship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternship(Internship target, Internship editedInternship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Internship> getFilteredInternshipList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipList(Predicate<Internship> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public Path getEventCatalogueFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventCatalogueFilePath(Path eventCatalogueFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventCatalogue(ReadOnlyEventCatalogue newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventCatalogue getEventCatalogue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
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
        public ObservableList<Event> getFilteredEventList() {
            return FXCollections.observableList(eventsAdded);
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSelectedInternship(Internship intern) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearSelectedInternship() {
            this.selectedInternship = null;
        }

        @Override
        public boolean hasSelectedInternship() {
            return this.selectedInternship != null;
        }

        //A Stub that Always returns ML1 Internship
        @Override
        public Internship getSelectedInternship() {
            return this.selectedInternship;
        }
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends EventAddCommandTest.ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventAdded extends EventAddCommandTest.ModelStub {

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void updateSelectedInternship(Internship intern){
            // Don't Do Anything
        }
        @Override
        public void updateFilteredEventList(Predicate<Event> ev) {
            // Don't Do Anything
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyEventCatalogue getEventCatalogue() {
            return new EventCatalogue();
        }
    }
}

