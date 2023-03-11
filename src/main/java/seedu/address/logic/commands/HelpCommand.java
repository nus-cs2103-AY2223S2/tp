package seedu.address.logic.commands;

import seedu.address.model.Model;

import java.util.List;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final List<String> COMMAND_WORD = List.of(new String[]{"help", "h"});

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
