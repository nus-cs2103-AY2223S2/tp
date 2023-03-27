package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Date;
import seedu.fitbook.model.client.WeightHistory;

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

        int clientIndex = targetIndex.getOneBased();

        if (clientIndex > model.getFilteredClientList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        ObservableList<Client> clientList = model.getFilteredClientList();
        Client clientToShowGraph = clientList.get(clientIndex - 1);

        WeightHistory graphWeightHistory = clientToShowGraph.getWeightHistory();
        refineGraphWeightHistory(graphWeightHistory);

        //a list of weight values for the y-axis
        List<Integer> graphYAxis = new ArrayList<>();
        for (int i = 0; i < graphWeightHistory.getListSize(); i++) {
            Integer weightValue = Integer.parseInt(graphWeightHistory.getWeightValue(i));
            graphYAxis.add(weightValue);
        }

        //a list of date values for the x-axis
        List<Date> graphXAxis = new ArrayList<>();
        for (int i = 0; i < graphWeightHistory.getListSize(); i++) {
            Date dateValue = graphWeightHistory.getDateValue(i);
            graphXAxis.add(dateValue);
        }

        //iterate through the list to plot the graph in UI

        return new CommandResult(String.format(MESSAGE_SUCCESS, clientIndex));
    }

    public Index getIndex() {
        return targetIndex;
    }

    /**
     * Refines the {@code weightHistory} of the client.
     */
    public void refineGraphWeightHistory(WeightHistory weightHistory) {
        weightHistory.sortByDate();
        weightHistory.removeOldWeights();
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
