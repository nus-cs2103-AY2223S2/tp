package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;

/**
 * Undos a previous modifying command that resulted in change.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undid: ";
    public static final String MESSAGE_FAILURE = "Unable to undo last command";

    private final HistoryUtil history = HistoryUtil.getInstance();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<String> description = history.undo();
        if (!description.isPresent()) {
            return new CommandResult(MESSAGE_FAILURE);
        }

        return new CommandResult(MESSAGE_SUCCESS + description.get());
    };

    @Override
    public boolean equals(Object other) {
        return other == this; // All undo are different.
    }
}
