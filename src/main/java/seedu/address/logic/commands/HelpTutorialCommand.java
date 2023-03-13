package seedu.address.logic.commands;

import seedu.address.model.Model;

public class HelpTutorialCommand extends HelpCommand {

    public static final String COMMAND_WORD = "help tutorial";

    public static final String TUTORIAL_SYNTAX = "Tutorial Input Format:\n"
            + "touch recur [name] Tutorials [day] [time] [duration] [period] ";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(TUTORIAL_SYNTAX);
    }
}
