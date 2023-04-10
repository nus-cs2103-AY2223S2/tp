package seedu.address.logic.commands.sortcommand;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Command to Sort Client By Email
 */
public class SortByClientEmailCommand extends SortCommand {

    public static final String COMMAND_WORD = "sortClientEmail";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the client by their email in lexicographical order.\n"
            + "Parameters: INTEGER (0 represents descending order, any other value represents ascending order)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS = "Sort By Client Email ";
    private boolean inOrder;

    public SortByClientEmailCommand(boolean inOrder) {
        this.inOrder = inOrder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        SortedList<Client> lastShownList = new SortedList<>(model.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }

        if (toSortList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_SORT);
        }

        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::getEmail));
        } else {
            toSortList.sort(Comparator.comparing(Client::getEmail).reversed());
        }
        model.sort(toSortList);
        return new CommandResult(generateSuccessMessage(inOrder));
    }

    private String generateSuccessMessage(boolean inOrder) {
        if (inOrder) {
            return MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS + "in ascending order";
        }
        return MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS + "in descending order";
    }

    @Override
    public boolean getInorder() {
        return inOrder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortByClientEmailCommand // instanceof handles nulls
                && inOrder == ((SortByClientEmailCommand) other).inOrder); // state check
    }
}
