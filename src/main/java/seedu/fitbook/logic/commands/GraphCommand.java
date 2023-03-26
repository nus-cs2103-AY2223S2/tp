package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Weight;

import java.time.LocalDate;
import java.util.List;

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

    private final int targetIndex;

    /**
     * Creates an AddWeightCommand to add the specified {@code Weight} to the client at the specified index.
     */
    public GraphCommand(int targetIndex) {
        assert(targetIndex > 0);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);

        if (targetIndex > model.getFilteredClientList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_INDEX);
        }
        ObservableList<Client> clientList = model.getFilteredClientList();
        Client clientToShowGraph = clientList.get(targetIndex - 1);

        List<Weight> graphWeightList = clientToShowGraph.getWeightList();

        refineGraphWeightList(graphWeightList);

        //iterate through the list to plot the graph in UI

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex));
    }

    public Integer getIndex() {
        return targetIndex;
    }

    public void refineGraphWeightList(List<Weight> weightList) {
        for (int i = 0; i < weightList.size(); i++) {
            Weight weight = weightList.get(i);
            LocalDate weightDate = LocalDate.parse(weight.getDate().toString());
            LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
            if (weightDate.isBefore(oneMonthAgo)) {
                //handle exception if there is no weight left in the graph list
                weightList.remove(i);
            }
        }
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
