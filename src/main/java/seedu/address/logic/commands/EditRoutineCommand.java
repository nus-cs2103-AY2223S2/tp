package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FitBookModel;
import seedu.address.model.routines.Exercise;
import seedu.address.model.routines.Routine;
import seedu.address.model.routines.RoutineName;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.model.FitBookModel.PREDICATE_SHOW_ALL_ROUTINES;

/**
 * Edits the details of an existing routine in the FitBook.
 */
public class EditRoutineCommand extends Command {

    public static final String COMMAND_WORD = "editRoutine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the exercises of the routine "
            + "by the index number used in the displayed routine list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EXERCISE + "EXERCISE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EXERCISE + "3x10 Dumbbell Curls "
            + PREFIX_EXERCISE + "5x11 Sit Ups";

    public static final String MESSAGE_EDIT_ROUTINE_SUCCESS = "Edited Routine: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROUTINE = "This routine already exists in the FitBook.";

    private final Index index;
    private final EditRoutineDescriptor editRoutineDescriptor;

    /**
     * @param index of the client in the filtered routine list to edit
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
    private static Routine createEditedRoutine(Routine routineToEdit, EditRoutineDescriptor editRoutineDescriptor) {
        assert routineToEdit != null;
        RoutineName updatedRoutineName = editRoutineDescriptor.getRoutineName().orElse(routineToEdit.getRoutineName());
        Set<Exercise> updatedExercise =
                editRoutineDescriptor.getExercises().orElse(routineToEdit.getExercises());
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
     * Stores the details to edit the client with. Each non-empty field value will replace the
     * corresponding field value of the client.
     */
    public static class EditRoutineDescriptor {
        private RoutineName routineName;
        private Set<Exercise> exercises;

        public EditRoutineDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRoutineDescriptor(EditRoutineDescriptor toCopy) {
            setRoutineName(toCopy.routineName);
            setExercises(toCopy.exercises);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(routineName, exercises);
        }

        public void setRoutineName(RoutineName routineName) {
            this.routineName = routineName;
        }

        public Optional<RoutineName> getRoutineName() {
            return Optional.ofNullable(routineName);
        }

        /**
         * Sets {@code exercises} to this object's {@code exercises}.
         * A defensive copy of {@code exercises} is used internally.
         */
        public void setExercises(Set<Exercise> exercises) {
            this.exercises = (exercises != null) ? new HashSet<>(exercises) : null;
        }

        /**
         * Returns an unmodifiable exercise set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code exercises} is null.
         */
        public Optional<Set<Exercise>> getExercises() {
            return (exercises != null) ? Optional.of(Collections.unmodifiableSet(exercises)) : Optional.empty();
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
                    && getExercises().equals(e.getExercises());
        }
    }
}
