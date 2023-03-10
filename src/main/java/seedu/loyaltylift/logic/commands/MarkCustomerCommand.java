package seedu.loyaltylift.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.model.Model;

public class MarkCustomerCommand extends Command {

    public static final String COMMAND_WORD = "markc";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
    }
}
