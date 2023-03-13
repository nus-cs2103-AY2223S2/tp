
package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Dummy command added for the tutorial
 */
public class HelloCommand extends Command {

    public static final String COMMAND_WORD = "hello";

    public static final String SHOWING_HELLO_MESSAGE = "Welcome to FAid! How may I help you today?";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELLO_MESSAGE, false, false);
    }
}
