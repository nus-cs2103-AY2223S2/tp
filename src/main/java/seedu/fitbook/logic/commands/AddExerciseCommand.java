package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.Routine;

/**
 * Adds an exercise to a specific Routine which is identified using it's displayed index from the FitBook.
 */
public class AddExerciseCommand extends Command {

    public static final String COMMAND_WORD = "addExercise";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add the exercise identified by the index number of the routine identified by the index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " ex/3 x 5 sprints";

    public static final String MESSAGE_ADD_EXERCISE_SUCCESS = "Added Exercise: %1$s in Routine: %2$s";

    private final Index targetRoutine;
    private final Exercise exerciseToAdd;

    /**
     * Creates an AddExerciseCommand to add the specified {@code Exercise}
     */
    public AddExerciseCommand(Index targetRoutine, Exercise exercise) {
        requireNonNull(exercise);
        this.targetRoutine = targetRoutine;
        this.exerciseToAdd = exercise;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        List<Routine> lastShownList = model.getFilteredRoutineList();

        if (targetRoutine.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
        }
        Routine routine = lastShownList.get(targetRoutine.getZeroBased());

        model.addExercise(routine, exerciseToAdd);

        return new CommandResult(String.format(MESSAGE_ADD_EXERCISE_SUCCESS, exerciseToAdd, routine));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExerciseCommand // instanceof handles nulls
                && targetRoutine.equals(((AddExerciseCommand) other).targetRoutine) // state check
                && exerciseToAdd.equals(((AddExerciseCommand) other).exerciseToAdd));
    }

}
