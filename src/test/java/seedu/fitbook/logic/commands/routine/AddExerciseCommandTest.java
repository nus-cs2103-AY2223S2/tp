package seedu.fitbook.logic.commands.routine;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.GuiSettings;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.AddExerciseCommand;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyUserPrefs;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;

public class AddExerciseCommandTest {

    @Test
    public void constructor_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExerciseCommand(null, null));
    }

    @Test
    public void equals() {
        Index index = Index.fromZeroBased(1);
        Index index2 = Index.fromZeroBased(2);
        Exercise exerciseToAdd = new Exercise("push ups");
        AddExerciseCommand addExerciseCommand = new AddExerciseCommand(index, exerciseToAdd);
        AddExerciseCommand addExercise2Command = new AddExerciseCommand(index2, exerciseToAdd);

        // same object -> returns true
        assertTrue(addExerciseCommand.equals(addExerciseCommand));

        // same values -> returns true
        AddExerciseCommand addExerciseCommandCopy = new AddExerciseCommand(index, exerciseToAdd);
        assertTrue(addExerciseCommand.equals(addExerciseCommandCopy));

        // different types -> returns false
        assertFalse(addExerciseCommand.equals(1));

        // null -> returns false
        assertFalse(addExerciseCommand.equals(null));

        // different routine -> returns false
        assertFalse(addExerciseCommand.equals(addExercise2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class FitBookExerciseRoutineModelStub implements FitBookModel {
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

        // Client part not used in this part.
        @Override
        public void addClient(Client client) {
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

        @Override
        public boolean hasRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRoutine(Routine target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoutine(Routine routine) {
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
        public void removeExercise(Routine routineToDelete, int zeroBased) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addExercise(Routine routine, Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A FitBookExerciseRoutineModel stub that contains a single routine.
     */
    private class FitBookExerciseRoutineModelStubWithExercise extends FitBookExerciseRoutineModelStub {
        private final Routine routine;

        FitBookExerciseRoutineModelStubWithExercise(Routine routine) {
            requireNonNull(routine);
            this.routine = routine;
        }

        @Override
        public boolean hasRoutine(Routine routine) {
            requireNonNull(routine);
            return this.routine.isSameRoutine(routine);
        }
        public void addExercise(Routine routine, Exercise exercise) {
            requireNonNull(routine);
            requireNonNull(exercise);
            routine.addExercise(exercise);
        }
    }
}
