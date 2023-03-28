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

public class SortByClientPhoneCommand extends SortCommand{
    private boolean inOrder;

    public static final String COMMAND_WORD = "sortClientPhone";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the client by their phone number.\n"
            + "Parameters: Integer value, 0 means reverse order and any other value means in order\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_Sort_By_Client_Phone_Success  = "Sort By Client Phone Number";

    public SortByClientPhoneCommand(boolean inOrder) {this.inOrder = inOrder;}

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
            toSortList.sort(Comparator.comparing(Client::getPhone));
        } else {
            toSortList.sort(Comparator.comparing(Client::getPhone).reversed());
        }

        model.sort(toSortList);
        return new CommandResult(generateSuccessMessage(inOrder));
    }

    private String generateSuccessMessage(boolean inOrder) {
        if (inOrder) {
            return MESSAGE_Sort_By_Client_Phone_Success + "in ascending order";
        }
        return MESSAGE_Sort_By_Client_Phone_Success + "in descending order";
    }

    @Override
    public boolean getInorder() {
        return inOrder;
    }
}
