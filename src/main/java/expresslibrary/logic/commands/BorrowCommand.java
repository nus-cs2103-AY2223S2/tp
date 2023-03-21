package expresslibrary.logic.commands;

import expresslibrary.model.Model;

public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from borrow");
    }

}
