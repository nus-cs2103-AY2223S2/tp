package teambuilder.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import teambuilder.commons.util.HistoryUtil;
import teambuilder.logic.commands.exceptions.CommandException;
import teambuilder.model.Model;

/**
 * Redoes a command that was undone.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redone: ";
    public static final String MESSAGE_FAILURE = "Unable to redo last command";

    private final HistoryUtil history = HistoryUtil.getInstance();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<String> description = history.redo();
        if (!description.isPresent()) {
            return new CommandResult(MESSAGE_FAILURE);
        }

        return new CommandResult(MESSAGE_SUCCESS + description.get());
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // All undo are different.
    }
}
