package seedu.address.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;

/**
 * Marks the current card under review as correct.
 */
public class MarkCorrectCommand extends Command {

    public static final String COMMAND_WORD = "'";

    public static final String MESSAGE_SUCCESS = "Nicely! Marked current card as correct.";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.markCorrect();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
