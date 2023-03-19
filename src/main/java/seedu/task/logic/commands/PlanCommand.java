package seedu.task.logic.commands;

import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;

/**
 * Creates a work plan for users.
 */
public class PlanCommand extends Command {
    public static final String COMMAND_WORD = "plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": creates a work plan.\n"
            + "Example: " + COMMAND_WORD + " 5";

    public static final String PLAN_SUCCESS_MESSAGE = "Work Plan Created.";

    private final int workload;

    public PlanCommand(int workload) {
        this.workload = workload;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.plan(workload);
        return new CommandResult(PLAN_SUCCESS_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanCommand // instanceof handles nulls
                && (workload == (((PlanCommand) other).workload))); // state check
    }
}
