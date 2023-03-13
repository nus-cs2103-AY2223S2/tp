package seedu.address.logic.commands;

import seedu.address.model.Model;

public class HelpLabCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help lab";

    public static final String LAB_SYNTAX = "Labs Input Format:\n"
            + "touch recur [name] Labs [day] [time] [duration] [period] ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(LAB_SYNTAX);
    }
}
