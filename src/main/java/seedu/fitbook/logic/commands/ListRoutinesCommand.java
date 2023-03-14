package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.model.FitBookModel.PREDICATE_SHOW_ALL_ROUTINES;

import seedu.fitbook.model.FitBookModel;

/**
 * Lists all routines in FitBook to the user.
 */
public class ListRoutinesCommand extends Command {

    public static final String COMMAND_WORD = "listRoutines";

    public static final String MESSAGE_SUCCESS = "Listed all Routines";


    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        model.updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

