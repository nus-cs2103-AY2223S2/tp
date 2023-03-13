package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.FitBookModel;

/**
 * Clears the address book.
 */
public class ClearRoutinesCommand extends Command {

    public static final String COMMAND_WORD = "clearRoutines";
    public static final String MESSAGE_SUCCESS = "Routines in FitBook has been cleared!";


    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.setFitBookExerciseRoutine(new FitBookExerciseRoutine());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
