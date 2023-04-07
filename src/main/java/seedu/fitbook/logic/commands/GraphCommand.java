package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;

/**
 * Adds a client to the FitBook.
 */
public class GraphCommand extends Command {

    public static final String COMMAND_WORD = "graph";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Plots a graph for the client's weight history.\n"
            + "Parameters: i/INDEX\n"
            + "Example: " + COMMAND_WORD + " i/1";

    public static final String MESSAGE_SUCCESS = "Graph shown for client %1$s";
    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "This client is fail to display ";

    private final Index targetIndex;

    /**
     * Creates a GraphCommand using the {@code targetIndex} of the client.
     */
    public GraphCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_SUCCESS, clientToView),
                clientToView, false, false, false, false, true);
    }

    public Index getIndex() {
        return targetIndex;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GraphCommand)) {
            return false;
        }

        // state check
        GraphCommand e = (GraphCommand) other;

        return getIndex().equals(e.getIndex());
    }
}
