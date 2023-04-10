package seedu.task.logic.commands;

import seedu.task.commons.core.Messages;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.task.Effort;

/**
 * Creates a work plan for users.
 */
public class PlanCommand extends Command {
    public static final String PLAN_SUCCESS_MESSAGE = "Work Plan Created.";

    private final long workload;

    /**
     * Creates a new {@code PlanCommand} with user preferred effort level.
     */
    public PlanCommand(Effort effort) {
        this.workload = effort.getEffort();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (workload < 0) {
            assert false; // checks should have been done in ScheduleCommand
            throw new CommandException(Messages.MESSAGE_INVALID_EFFORT);
        }
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
