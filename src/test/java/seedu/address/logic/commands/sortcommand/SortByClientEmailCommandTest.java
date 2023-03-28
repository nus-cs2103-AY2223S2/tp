package seedu.address.logic.commands.sortcommand;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.SortedList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

public class SortByClientEmailCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // ==================== Valid Input Test Case ==============================================================
    @Test
    public void execute_validInputNotInOrder_success() {
        boolean inOrder = false;
        SortByClientEmailCommand sortCommand = new SortByClientEmailCommand(false);
        String expectedMessage = SortByClientEmailCommand.MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS
                + "in descending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        SortedList<Client> lastShownList = new SortedList<>(expectedModel.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }
        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::getEmail));
        } else {
            toSortList.sort(Comparator.comparing(Client::getEmail).reversed());
        }
        expectedModel.sort(toSortList);

        assertCommandSuccess(sortCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInputInOrder_success() {
        boolean inOrder = true;
        SortByClientEmailCommand sortCommand = new SortByClientEmailCommand(true);
        String expectedMessage = SortByClientEmailCommand.MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS
                + "in ascending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        SortedList<Client> lastShownList = new SortedList<>(expectedModel.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }
        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::getEmail));
        } else {
            toSortList.sort(Comparator.comparing(Client::getEmail).reversed());
        }
        expectedModel.sort(toSortList);

        assertCommandSuccess(sortCommand, originalModel, expectedMessage, expectedModel);
    }
}
