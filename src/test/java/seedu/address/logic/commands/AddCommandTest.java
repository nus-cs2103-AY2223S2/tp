package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.testutil.OpeningBuilder;
import seedu.ultron.commons.core.GuiSettings;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.AddCommand;
import seedu.ultron.logic.commands.CommandResult;
import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.model.Model;
import seedu.ultron.model.ReadOnlyUltron;
import seedu.ultron.model.ReadOnlyUserPrefs;
import seedu.ultron.model.Ultron;
import seedu.ultron.model.opening.KeydateSort;
import seedu.ultron.model.opening.Opening;

public class AddCommandTest {

    @Test
    public void constructor_nullOpening_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_openingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOpeningAdded modelStub = new ModelStubAcceptingOpeningAdded();
        Opening validOpening = new OpeningBuilder().build();

        CommandResult commandResult = new AddCommand(validOpening).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validOpening), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOpening), modelStub.openingsAdded);
    }

    @Test
    public void execute_duplicateOpening_throwsCommandException() {
        Opening validOpening = new OpeningBuilder().build();
        AddCommand addCommand = new AddCommand(validOpening);
        ModelStub modelStub = new ModelStubWithOpening(validOpening);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_OPENING, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Opening frontendEngineer = new OpeningBuilder().withPosition("Frontend Engineer").build();
        Opening backendEngineer = new OpeningBuilder().withPosition("Backend Engineer").build();
        AddCommand addFrontendEngineerCommand = new AddCommand(frontendEngineer);
        AddCommand addBackendEngineerCommand = new AddCommand(backendEngineer);

        // same object -> returns true
        assertTrue(addFrontendEngineerCommand.equals(addFrontendEngineerCommand));

        // same values -> returns true
        AddCommand addFrontendEngineerCommandCopy = new AddCommand(frontendEngineer);
        assertTrue(addFrontendEngineerCommand.equals(addFrontendEngineerCommandCopy));

        // different types -> returns false
        assertFalse(addFrontendEngineerCommand.equals(1));

        // null -> returns false
        assertFalse(addFrontendEngineerCommand.equals(null));

        // different opening -> returns false
        assertFalse(addFrontendEngineerCommand.equals(addBackendEngineerCommand));
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
        public Path getUltronFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUltronFilePath(Path ultronFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOpening(Opening opening) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUltron(ReadOnlyUltron newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUltron getUltron() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOpening(Opening opening) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOpening(Opening target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOpening(Opening target, Opening editedOpening) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Opening> getFilteredOpeningList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOpeningList(Predicate<Opening> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredOpeningList(KeydateSort direction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Opening getSelectedOpening() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Index getSelectedIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSelectedIndex() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single opening.
     */
    private class ModelStubWithOpening extends ModelStub {
        private final Opening opening;

        ModelStubWithOpening(Opening opening) {
            requireNonNull(opening);
            this.opening = opening;
        }

        @Override
        public boolean hasOpening(Opening opening) {
            requireNonNull(opening);
            return this.opening.isSameOpening(opening);
        }
    }

    /**
     * A Model stub that always accept the opening being added.
     */
    private class ModelStubAcceptingOpeningAdded extends ModelStub {
        final ArrayList<Opening> openingsAdded = new ArrayList<>();

        @Override
        public boolean hasOpening(Opening opening) {
            requireNonNull(opening);
            return openingsAdded.stream().anyMatch(opening::isSameOpening);
        }

        @Override
        public void addOpening(Opening opening) {
            requireNonNull(opening);
            openingsAdded.add(opening);
        }

        @Override
        public ReadOnlyUltron getUltron() {
            return new Ultron();
        }
    }

}
