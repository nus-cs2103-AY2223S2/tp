package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE_NUMBER;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.fitbook.model.FitBookModel.PREDICATE_SHOW_ALL_ROUTINES;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.commons.util.CollectionUtil;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;
import seedu.fitbook.model.routines.RoutineName;

/**
 * Edits the details of an existing routine in the FitBook.
 */
public class EditRoutineCommand extends Command {

    public static final String COMMAND_WORD = "editRoutine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the exercises of the routine "
            + "by the index number of the routine and exercise used in the displayed routine list. "
            + "Also edits the routine name by the index number of the routine used in the displayed routine list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EXERCISE_NUMBER + "EXERCISE_NUMBER (must be a positive integer) "
            + PREFIX_EXERCISE + "EXERCISE\n"
            + "Example 1: " + COMMAND_WORD + " 1 "
            + PREFIX_EXERCISE_NUMBER + "1 "
            + PREFIX_EXERCISE + "3x10 Dumbbell Curls "
            + PREFIX_EXERCISE + "5x11 Sit Ups\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ROUTINE + "ROUTINE_NAME\n"
            + "Example 2: " + COMMAND_WORD + " 1 "
            + PREFIX_ROUTINE + "HIIT";

    public static final String MESSAGE_EDIT_ROUTINE_SUCCESS = "Edited Routine: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROUTINE = "This routine already exists in the FitBook.";

    private final Index index;
    private final EditRoutineDescriptor editRoutineDescriptor;

    /**
     * @param index of the routine in the filtered routine list to edit
     * @param editRoutineDescriptor details to edit the routine with
     */
    public EditRoutineCommand(Index index, EditRoutineDescriptor editRoutineDescriptor) {
        requireNonNull(index);
        requireNonNull(editRoutineDescriptor);

        this.index = index;
        this.editRoutineDescriptor = new EditRoutineDescriptor(editRoutineDescriptor);
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        List<Routine> lastShownList = model.getFilteredRoutineList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
        }

        Routine routineToEdit = lastShownList.get(index.getZeroBased());
        Routine editedRoutine = createEditedRoutine(routineToEdit, editRoutineDescriptor);

        if (!routineToEdit.isSameRoutine(editedRoutine) && model.hasRoutine(editedRoutine)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROUTINE);
        }

        model.setRoutine(routineToEdit, editedRoutine);
        model.updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        return new CommandResult(String.format(MESSAGE_EDIT_ROUTINE_SUCCESS, editedRoutine));
    }

    /**
     * Creates and returns a {@code Routine} with the details of {@code routineToEdit}
     * edited with {@code editRoutineDescriptor}.
     */
    private static Routine createEditedRoutine(Routine routineToEdit, EditRoutineDescriptor editRoutineDescriptor)
            throws CommandException {
        assert routineToEdit != null;
        List<Exercise> updatedExercise;
        RoutineName updatedRoutineName = editRoutineDescriptor.getRoutineName().orElse(routineToEdit.getRoutineName());

        if (editRoutineDescriptor.getExercise().isPresent() && editRoutineDescriptor.getExerciseIndex().isPresent()) {
            int changeIndex = editRoutineDescriptor.getExerciseIndex().get().getZeroBased();
            Exercise changeExercise = editRoutineDescriptor.getExercise().get();

            if (changeIndex >= routineToEdit.getExercises().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
            }

            routineToEdit.getExercises().remove(changeIndex);
            routineToEdit.getExercises().add(changeIndex, changeExercise);
        }

        updatedExercise = new ArrayList<>(routineToEdit.getExercises());
        return new Routine(updatedRoutineName, updatedExercise);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRoutineCommand)) {
            return false;
        }

        // state check
        EditRoutineCommand e = (EditRoutineCommand) other;
        return index.equals(e.index)
                && editRoutineDescriptor.equals(e.editRoutineDescriptor);
    }

    /**
     * Stores the details to edit the routine with. Each non-empty field value will replace the
     * corresponding field value of the routine.
     */
    public static class EditRoutineDescriptor {
        private RoutineName routineName;
        private Index exerciseIndex;
        private Exercise exerciseChange;

        public EditRoutineDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code exercises} is used internally.
         */
        public EditRoutineDescriptor(EditRoutineDescriptor toCopy) {
            setRoutineName(toCopy.routineName);
            setExercise(toCopy.exerciseChange);
            setExerciseIndex(toCopy.exerciseIndex);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(routineName, exerciseIndex, exerciseChange);
        }

        public void setExerciseIndexNull() {
            this.exerciseIndex = null;
        }

        public void setExerciseNull() {
            this.exerciseChange = null;
        }

        public void setRoutineNameNull() {
            this.routineName = null;
        }

        public void setRoutineName(RoutineName routineName) {
            this.routineName = routineName;
        }

        public Optional<RoutineName> getRoutineName() {
            return Optional.ofNullable(routineName);
        }

        public void setExercise(Exercise exerciseChange) {
            this.exerciseChange = exerciseChange;
        }

        public Optional<Exercise> getExercise() {
            return Optional.ofNullable(exerciseChange);
        }

        public void setExerciseIndex(Index exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public Optional<Index> getExerciseIndex() {
            return Optional.ofNullable(exerciseIndex);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRoutineDescriptor)) {
                return false;
            }

            // state check
            EditRoutineDescriptor e = (EditRoutineDescriptor) other;

            return getRoutineName().equals(e.getRoutineName())
                    && getExerciseIndex().equals(e.getExerciseIndex())
                    && getExercise().equals(e.getExercise());
        }
    }
}
