package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;

import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;

/**
 * Undos a previous command that resulted in change.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undid last command";
    public static final String MESSAGE_FAILURE = "Unable to undo last command";

    private final HistoryUtil history = HistoryUtil.getInstance();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isSuccessful = history.undo();
        if (!isSuccessful) {
            return new CommandResult(MESSAGE_FAILURE);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    };

    @Override
    public boolean equals(Object other) {
        return other == this; // All undo are different.
    }
}
