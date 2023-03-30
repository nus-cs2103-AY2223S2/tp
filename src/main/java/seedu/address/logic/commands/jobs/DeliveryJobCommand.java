package seedu.address.logic.commands.jobs;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandGroup;

/**
 * Defines PersonCommand.
 */
abstract class DeliveryJobCommand extends Command {
    @Override
    public CommandGroup getGroup() {
        return CommandGroup.JOB;
    }
}
