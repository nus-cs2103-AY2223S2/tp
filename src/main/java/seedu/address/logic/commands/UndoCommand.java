package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.pet.DeadlineWithinThreeDaysPredicate;
import seedu.address.model.pet.Pet;

import java.util.List;
import java.util.function.Predicate;

/**
 * Lists all pets in the pet list to the user.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "previous command reverted";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "reverts previous command";




    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
