package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;

import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.model.Model;

public class ViewCommand extends Command{

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "View food";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
