package seedu.address.logic.commands.sortcommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

/**
 * Input Partition:
 * 1. Only input Command itself
 * 2. Input 0
 * 3. input 1
 * 4. input decimal
 * 5. input fraction
 * 6. input null
 * 7. input string
 * 8. input very big number
 */
public class SortByClientNameCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // ==================== Valid Input Test Case ==============================================================
    @Test
    public void execute_validInputNotInOrder_success() {
        boolean inOrder = false;
        SortByClientNameCommand sortCommand = new SortByClientNameCommand(inOrder);
        String expectedMessage = SortByClientNameCommand.MESSAGE_SORT_BY_CLIENT_NAME_SUCCESS
                + "in descending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        SortedList<Client> lastShownList = new SortedList<>(expectedModel.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }
        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::toString));
        } else {
            toSortList.sort(Comparator.comparing(Client::toString).reversed());
        }
        expectedModel.sort(toSortList);

        assertCommandSuccess(sortCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInputInOrder_success() {
        boolean inOrder = true;
        SortByClientNameCommand sortCommand = new SortByClientNameCommand(inOrder);
        String expectedMessage = SortByClientNameCommand.MESSAGE_SORT_BY_CLIENT_NAME_SUCCESS
                + "in ascending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        SortedList<Client> lastShownList = new SortedList<>(expectedModel.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }
        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::toString));
        } else {
            toSortList.sort(Comparator.comparing(Client::toString).reversed());
        }
        expectedModel.sort(toSortList);

        assertCommandSuccess(sortCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void emptyClientTest_throwsCommandException() {
        ModelManager emptyModel = new ModelManager();
        boolean inOrder = true;
        SortByClientNameCommand sortCommand = new SortByClientNameCommand(true);
        String expectedMessage = Messages.MESSAGE_EMPTY_SORT;
        assertCommandFailure(sortCommand, emptyModel, expectedMessage);
    }

    @Test
    public void get_inOrder_test() {
        boolean inOrder = true;
        SortByClientNameCommand sortCommand = new SortByClientNameCommand(inOrder);
        assertEquals(sortCommand.getInorder(), inOrder);
    }


}
