package arb.logic.commands.client;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.model.Model.CLIENT_NAME_COMPARATOR;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalClients.getTypicalClients;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.client.Client;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortClientCommand.
 */
public class SortClientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_sort_success() {
        SortClientCommand command = new SortClientCommand();
        expectedModel.updateSortedClientList(CLIENT_NAME_COMPARATOR);
        assertCommandSuccess(command, ListType.CLIENT, ListType.CLIENT, model, SortClientCommand.MESSAGE_SUCCESS,
                expectedModel);
        List<Client> expectedList = getTypicalClients();
        Collections.sort(expectedList, CLIENT_NAME_COMPARATOR);
        assertEquals(expectedList, model.getSortedClientList());
    }

}
