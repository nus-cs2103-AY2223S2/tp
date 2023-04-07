package tfifteenfour.clipboard.logic.commands;

import tfifteenfour.clipboard.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting CLIpboard as requested ...";

    public ExitCommand() {
        super(false);
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(this, MESSAGE_EXIT_ACKNOWLEDGEMENT, willModifyState);
    }

}
