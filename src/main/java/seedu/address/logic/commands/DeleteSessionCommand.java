package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Session;

/**
 * Deletes a session from the address book.
 */
public class DeleteSessionCommand extends Command {
    public static final String COMMAND_WORD = "delete-session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an existing session to the address book. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Session removed: %1$s";
    public static final String MESSAGE_SESSION_NOT_FOUND = "This session does not exist in the address book";

    public final Index index;

    /**
     * Creates a DeleteSessionCommand to remove the specified {@code Session}
     * @param index
     */
    public DeleteSessionCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Session> lastShownList = model.getFilteredSessionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session toRemove = lastShownList.get(index.getZeroBased());

        if (!model.hasSession(toRemove)) {
            throw new CommandException(MESSAGE_SESSION_NOT_FOUND);
        }
        model.removeSession(toRemove);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove));
    }
}
