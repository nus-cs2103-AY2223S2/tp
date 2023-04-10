package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;


/**
 * Views a client's detail to the FitBook.
 */
public class ViewDetailCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a client in the FitBook. "
            + "Parameters: " + "Index\n"
            + "Example: " + COMMAND_WORD + " 1";


    public static final String MESSAGE_SUCCESS = "Client viewed";
    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "This client index is invalid. ";

    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public ViewDetailCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS, clientToView),
                clientToView, false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewDetailCommand)) {
            return false;
        }

        // state check
        ViewDetailCommand e = (ViewDetailCommand) other;
        return index.equals(e.index);
    }

    private Predicate<Client> isTargetClient(Client targetClient) {
        return client -> client.isSameClient(targetClient);
    }
}
