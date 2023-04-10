package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.model.event.UniqueEventList.EMPTY_UNIQUE_EVENTS_LIST;
import static seedu.internship.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ReadOnlyEventCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.ReadOnlyUserPrefs;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;
import seedu.internship.testutil.InternshipBuilder;
import seedu.internship.testutil.TypicalInternships;


public class AddCommandTest {

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternshipAdded modelStub = new ModelStubAcceptingInternshipAdded();
        Internship validInternship = new InternshipBuilder().build();
        CommandResult commandResult = new AddCommand(validInternship).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validInternship), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternship), modelStub.internshipsAdded);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().build();
        AddCommand addCommand = new AddCommand(validInternship);
        ModelStub modelStub = new ModelStubWithInternship(validInternship);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_INTERNSHIP, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Internship ml2 = TypicalInternships.ML2;
        Internship se3 = TypicalInternships.SE3;
        AddCommand addML2Command = new AddCommand(ml2);
        AddCommand addSE3Command = new AddCommand(se3);

        // same object -> returns true
        assertTrue(addML2Command.equals(addML2Command));

        // same values -> returns true
        AddCommand addML2CommandCopy = new AddCommand(ml2);
        assertTrue(addML2Command.equals(addML2CommandCopy));


        // different types -> returns false
        assertFalse(addML2Command.equals(1));


        // null -> returns false
        assertFalse(addML2Command.equals(null));

        // different internship -> returns false
        assertFalse(addML2Command.equals(addSE3Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
            throw new AssertionError("This method should not be called.");
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSelectedInternship() {
            return false;
        }

        @Override
        public Internship getSelectedInternship() {
            return null;
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithInternship extends ModelStub {
        private final Internship internship;

        ModelStubWithInternship(Internship internship) {
            requireNonNull(internship);
            this.internship = internship;
        }

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return this.internship.isSameInternship(internship);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingInternshipAdded extends ModelStub {
        final ArrayList<Internship> internshipsAdded = new ArrayList<>();

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return internshipsAdded.stream().anyMatch(internship::isSameInternship);
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
        public ObservableList<Event> getFilteredEventList() {
            return EMPTY_UNIQUE_EVENTS_LIST;
        }

        @Override
        public void addInternship(Internship internship) {
            requireNonNull(internship);
            internshipsAdded.add(internship);
        }

        @Override
        public ReadOnlyInternshipCatalogue getInternshipCatalogue() {
            return new InternshipCatalogue();
        }
    }

}
