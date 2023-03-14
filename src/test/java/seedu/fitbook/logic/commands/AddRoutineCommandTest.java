package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyUserPrefs;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.testutil.routine.RoutineBuilder;

public class AddRoutineCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRoutineCommand(null));
    }

    @Test
    public void execute_routineAcceptedByFitBookModel_addRoutineSuccessful() throws Exception {
        FitBookModelStubAcceptingRoutineAdded modelStub = new FitBookModelStubAcceptingRoutineAdded();
        Routine validRoutine = new RoutineBuilder().build();

        CommandResult commandResult = new AddRoutineCommand(validRoutine).execute(modelStub);

        assertEquals(String.format(AddRoutineCommand.MESSAGE_SUCCESS, validRoutine), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRoutine), modelStub.routinesAdded);
    }

    @Test
    public void execute_duplicateRoutine_throwsCommandException() {
        Routine validRoutine = new RoutineBuilder().build();
        AddRoutineCommand addRoutineCommand = new AddRoutineCommand(validRoutine);
        FitBookModelStub modelStub = new FitBookModelStubWithRoutine(validRoutine);

        assertThrows(CommandException.class, AddRoutineCommand.MESSAGE_DUPLICATE_ROUTINE, () ->
                addRoutineCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Routine r1 = new RoutineBuilder().withRoutineName("cardio").withExercises("sample").build();
        Routine r2 = new RoutineBuilder().withRoutineName("cardio2").withExercises("sample").build();
        AddRoutineCommand addR1Command = new AddRoutineCommand(r1);
        AddRoutineCommand addR2Command = new AddRoutineCommand(r2);

        // same object -> returns true
        assertTrue(addR1Command.equals(addR1Command));

        // same values -> returns true
        AddRoutineCommand addR1CommandCopy = new AddRoutineCommand(r1);
        assertTrue(addR1Command.equals(addR1CommandCopy));

        // different types -> returns false
        assertFalse(addR1Command.equals(1));

        // null -> returns false
        assertFalse(addR1Command.equals(null));

        // different client -> returns false
        assertFalse(addR1Command.equals(addR2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class FitBookModelStub implements FitBookModel {
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
        public Path getFitBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFitBookFilePath(Path fitBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFitBook(ReadOnlyFitBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFitBook getFitBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRoutine(Routine target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRoutine(Routine target, Routine editedRoutine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Routine> getFilteredRoutineList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoutineList(Predicate<Routine> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine exerciseRoutine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFitBookExerciseRoutine getFitBookExerciseRoutine() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A FitBookModel stub that contains a single routine.
     */
    private class FitBookModelStubWithRoutine extends FitBookModelStub {
        private final Routine routine;

        FitBookModelStubWithRoutine(Routine routine) {
            requireNonNull(routine);
            this.routine = routine;
        }

        @Override
        public boolean hasRoutine(Routine routine) {
            requireNonNull(routine);
            return this.routine.isSameRoutine(routine);
        }
    }

    /**
     * A FitBookModel stub that always accept the routine being added.
     */
    private class FitBookModelStubAcceptingRoutineAdded extends FitBookModelStub {
        final ArrayList<Routine> routinesAdded = new ArrayList<>();

        @Override
        public boolean hasRoutine(Routine routine) {
            requireNonNull(routine);
            return routinesAdded.stream().anyMatch(routine::isSameRoutine);
        }

        @Override
        public void addRoutine(Routine routine) {
            requireNonNull(routine);
            routinesAdded.add(routine);
        }

        @Override
        public ReadOnlyFitBookExerciseRoutine getFitBookExerciseRoutine() {
            return new FitBookExerciseRoutine();
        }
    }

}
