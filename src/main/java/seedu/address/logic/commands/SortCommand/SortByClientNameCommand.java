package seedu.address.logic.commands.SortCommand;

import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class SortByClientNameCommand extends SortCommand {
    private boolean inOrder;

    public static final String COMMAND_WORD = "sortClientName";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the client by their name in lexicographical order.\n"
            + "Parameters: Integer value, 0 means reverse order and any other value means in order\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_Sort_By_Client_Name_Success  = "Sort By Client Name";


    public SortByClientNameCommand(boolean inOrder) {
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
            toSortList.sort(Comparator.comparing(Client::toString));
        } else {
            toSortList.sort(Comparator.comparing(Client::toString).reversed());
        }
        model.sort(toSortList);
        return new CommandResult(generateSuccessMessage(inOrder));
    }

    private String generateSuccessMessage(boolean inOrder) {
        if (inOrder) {
            return MESSAGE_Sort_By_Client_Name_Success + "in ascending order";
        }
        return MESSAGE_Sort_By_Client_Name_Success + "in descending order";
    }

    @Override
    public boolean getInorder() {
        return inOrder;
    }
}
