package seedu.loyaltylift.logic.commands;

import seedu.loyaltylift.model.Model;

/**
 * Sets the reward points of a customer
 */
public class SetPointsCommand extends Command {

    public static final String COMMAND_WORD = "setpoints";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("This is a setpoints command");
    }
}
