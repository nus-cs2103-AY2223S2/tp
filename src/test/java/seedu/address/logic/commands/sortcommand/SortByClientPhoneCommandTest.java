package seedu.address.logic.commands.sortcommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.ClientBuilder.DEFAULT_PHONE;
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
import seedu.address.model.client.Phone;
import seedu.address.testutil.ClientBuilder;

public class SortByClientPhoneCommandTest {
    //todo: when client list is empty
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validInputNotInOrder_success() {
        boolean inOrder = false;
        SortByClientPhoneCommand sortCommand = new SortByClientPhoneCommand(inOrder);
        String expectedMessage = SortByClientPhoneCommand.MESSAGE_SORT_BY_CLIENT_PHONE_SUCCESS
                + "in descending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        SortedList<Client> lastShownList = new SortedList<>(expectedModel.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }
        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::getPhone));
        } else {
            toSortList.sort(Comparator.comparing(Client::getPhone).reversed());
        }

        expectedModel.sort(toSortList);

        assertCommandSuccess(sortCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInputInOrder_success() {
        boolean inOrder = true;
        SortByClientPhoneCommand sortCommand = new SortByClientPhoneCommand(inOrder);
        String expectedMessage = SortByClientPhoneCommand.MESSAGE_SORT_BY_CLIENT_PHONE_SUCCESS
                + "in ascending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        SortedList<Client> lastShownList = new SortedList<>(expectedModel.getFilteredClientList());
        List<Client> toSortList = new ArrayList<>();
        for (Client c : lastShownList) {
            toSortList.add(c);
        }
        if (inOrder) {
            toSortList.sort(Comparator.comparing(Client::getPhone));
        } else {
            toSortList.sort(Comparator.comparing(Client::getPhone).reversed());
        }

        expectedModel.sort(toSortList);

        assertCommandSuccess(sortCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_samePhoneSortInOrder_success() {
        boolean inOrder = true;
        SortByClientPhoneCommand sortCommand = new SortByClientPhoneCommand(inOrder);
        String expectedMessage = SortByClientPhoneCommand.MESSAGE_SORT_BY_CLIENT_PHONE_SUCCESS
                + "in ascending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<String> namesList = new ArrayList<>();
        namesList.add("Alice");
        namesList.add("Bob");
        namesList.add("Charlie");
        namesList.add("David");
        namesList.add("Emma");
        namesList.add("Frank");
        namesList.add("Grace");
        int index = 0;
        for (Client c : originalModel.getFilteredClientList()) {
            Client editedClient = new ClientBuilder().withName(namesList.get(index)).build();
            originalModel.setClient(c, editedClient);
            index++;
        }
        Phone test = new Phone(DEFAULT_PHONE);
        for (Client c : originalModel.getFilteredClientList()) {
            assertEquals(c.getPhone(), test);
        }
        assertCommandSuccess(sortCommand, originalModel, expectedMessage, originalModel);
    }

    @Test
    public void execute_samePhoneSortReverseOrder_success() {
        boolean inOrder = false;
        SortByClientPhoneCommand sortCommand = new SortByClientPhoneCommand(inOrder);
        String expectedMessage = SortByClientPhoneCommand.MESSAGE_SORT_BY_CLIENT_PHONE_SUCCESS
                + "in descending order";
        ModelManager originalModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<String> namesList = new ArrayList<>();
        namesList.add("Alice");
        namesList.add("Bob");
        namesList.add("Charlie");
        namesList.add("David");
        namesList.add("Emma");
        namesList.add("Frank");
        namesList.add("Grace");
        int index = 0;
        for (Client c : originalModel.getFilteredClientList()) {
            Client editedClient = new ClientBuilder().withName(namesList.get(index)).build();
            originalModel.setClient(c, editedClient);
            index++;
        }
        Phone test = new Phone(DEFAULT_PHONE);
        for (Client c : originalModel.getFilteredClientList()) {
            assertEquals(c.getPhone(), test);
        }
        assertCommandSuccess(sortCommand, originalModel, expectedMessage, originalModel);
    }
    @Test
    public void emptyClientTest_throwsCommandException() {
        ModelManager emptyModel = new ModelManager();
        boolean inOrder = true;
        SortByClientPhoneCommand sortCommand = new SortByClientPhoneCommand(inOrder);
        String expectedMessage = Messages.MESSAGE_EMPTY_SORT;
        assertCommandFailure(sortCommand, emptyModel, expectedMessage);
    }

    @Test
    public void get_inOrder_test() {
        boolean inOrder = true;
        SortByClientPhoneCommand sortCommand = new SortByClientPhoneCommand(inOrder);
        assertEquals(sortCommand.getInorder(), inOrder);
    }

}
