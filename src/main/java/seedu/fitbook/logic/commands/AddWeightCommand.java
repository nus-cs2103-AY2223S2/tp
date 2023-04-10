package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.commons.core.index.Index;
import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Date;
import seedu.fitbook.model.client.Weight;

/**
 * Adds a client to the FitBook.
 */
public class AddWeightCommand extends Command {

    public static final String COMMAND_WORD = "addWeight";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a weight to a client in the FitBook.\n"
            + "Parameters: i/INDEX w/WEIGHT d/DATE\n"
            + "Example: " + COMMAND_WORD + " 1 w/70 d/10-03-2010 19:00";

    public static final String MESSAGE_SUCCESS = "New weight added to client %1$s:" + " %2$skg " + "on %3$s";

    private final Index index;
    private final Weight weightToAdd;
    private final Date date;

    /**
     * Creates an AddWeightCommand to add the specified {@code Weight} to the client at the specified index.
     */
    public AddWeightCommand(Index index, Weight weightToAdd, Date date) {
        requireNonNull(index);
        requireNonNull(weightToAdd);
        requireNonNull(date);

        this.index = index;
        this.weightToAdd = weightToAdd;
        this.date = date;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);

        int clientIndex = index.getOneBased();

        if (clientIndex > model.getFilteredClientList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        ObservableList<Client> clientList = model.getFilteredClientList();
        Client clientToAddWeight = clientList.get(clientIndex - 1);

        for (Weight currWeight : clientToAddWeight.getWeightHistory().weights) {
            if (currWeight.date.equals(date)) {
                throw new CommandException(Messages.MESSAGE_DUPLICATE_DATE);
            }
        }

        clientToAddWeight.getWeightHistory().addWeight(date, weightToAdd.value);
        Weight lastWeight = clientToAddWeight.getWeightHistory().getLastEntry();
        clientToAddWeight.setWeight(lastWeight);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, index.getOneBased(), weightToAdd.value, date.toString()));
    }

    public Index getIndex() {
        return index;
    }

    public Weight getWeightToAdd() {
        return weightToAdd;
    }

    public Date getDate() {
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
