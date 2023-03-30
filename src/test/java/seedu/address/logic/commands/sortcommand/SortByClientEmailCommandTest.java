package seedu.address.logic.commands.sortcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.ClientBuilder.DEFAULT_EMAIL;
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
import seedu.address.model.client.Email;
import seedu.address.testutil.ClientBuilder;

public class SortByClientEmailCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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

    @Test
    public void execute_sameEmailSortInOrder_success() {
        boolean inOrder = true;
        SortByClientEmailCommand sortCommand = new SortByClientEmailCommand(inOrder);
        String expectedMessage = SortByClientEmailCommand.MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS
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
        Email test = new Email(DEFAULT_EMAIL);
        for (Client c : originalModel.getFilteredClientList()) {
            assertEquals(c.getEmail(), test);
        }
        assertCommandSuccess(sortCommand, originalModel, expectedMessage, originalModel);
    }

    @Test
    public void execute_sameEmailSortReverseOrder_success() {
        boolean inOrder = false;
        SortByClientEmailCommand sortCommand = new SortByClientEmailCommand(inOrder);
        String expectedMessage = SortByClientEmailCommand.MESSAGE_SORT_BY_CLIENT_EMAIL_SUCCESS
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
        Email test = new Email(DEFAULT_EMAIL);
        for (Client c : originalModel.getFilteredClientList()) {
            assertEquals(c.getEmail(), test);
        }
        assertCommandSuccess(sortCommand, originalModel, expectedMessage, originalModel);
    }


    @Test
    public void emptyClientTest_throwsCommandException() {
        ModelManager emptyModel = new ModelManager();
        boolean inOrder = true;
        SortByClientEmailCommand sortCommand = new SortByClientEmailCommand(true);
        String expectedMessage = Messages.MESSAGE_EMPTY_SORT;
        assertCommandFailure(sortCommand, emptyModel, expectedMessage);
    }

    @Test
    public void get_inOrder_test() {
        boolean inOrder = true;
        SortByClientEmailCommand sortCommand = new SortByClientEmailCommand(inOrder);
        assertEquals(sortCommand.getInorder(), inOrder);
    }

}
