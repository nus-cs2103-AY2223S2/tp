package seedu.sudohr.logic.commands.leave;
import static java.util.Objects.requireNonNull;
import static seedu.sudohr.model.Model.PREDICATE_SHOW_ALL_NON_EMPTY_LEAVES;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.model.Model;

/**
 * Lists all leaves with at least one person on leave in SudoHr to the user.
 */
public class ListLeaveCommand extends Command {
    public static final String COMMAND_WORD = "llve";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all leaves in SudoHR.";

    public static final String MESSAGE_SUCCESS = "Listed all leaves";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLeaveList(PREDICATE_SHOW_ALL_NON_EMPTY_LEAVES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
