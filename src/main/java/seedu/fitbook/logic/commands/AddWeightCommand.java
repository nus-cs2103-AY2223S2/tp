package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Weight;

/**
 * Adds a client to the FitBook.
 */
public class AddWeightCommand extends Command {

    public static final String COMMAND_WORD = "addWeight";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a weight to a client in the FitBook.\n"
            + "Parameters: i/INDEX w/WEIGHT d/DATE\n"
            + "Example: " + COMMAND_WORD + " i/1 w/70 d/2023-03-10";

    public static final String MESSAGE_SUCCESS = "New weight added to client %1$s: %2$s" + "kg "+ "on %3$s";

    private final int targetIndex;
    private final Weight weightToAdd;
    private final String date;

    /**
     * Creates an AddWeightCommand to add the specified {@code Weight} to the client at the specified index.
     */
    public AddWeightCommand(int targetIndex, Weight weightToAdd, String date) {
        requireNonNull(weightToAdd);
        assert(targetIndex > 0);
        this.targetIndex = targetIndex;
        this.weightToAdd = weightToAdd;
        this.date = date;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);

        if (targetIndex > model.getFilteredClientList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_INDEX);
        }
        ObservableList<Client> clientList = model.getFilteredClientList();
        Client clientToAddWeight = clientList.get(targetIndex - 1);

        clientToAddWeight.getWeightHistory().addWeight(date, weightToAdd.value);
        Pair<String, String> lastEntry = clientToAddWeight.getWeightHistory().getLastEntry();
        String lastWeightValue = lastEntry.getValue();
        Weight lastWeight = new Weight(lastWeightValue);
        clientToAddWeight.setWeight(lastWeight);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex, weightToAdd, date));
    }

    public Integer getIndex() {
        return targetIndex;
    }

    public Weight getWeightToAdd() {
        return weightToAdd;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddWeightCommand)) {
            return false;
        }

        // state check
        AddWeightCommand e = (AddWeightCommand) other;

        return getIndex().equals(e.getIndex())
                && getWeightToAdd().equals(e.getWeightToAdd())
                && getDate().equals(e.getDate());
    }
}
