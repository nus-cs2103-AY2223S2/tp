package arb.logic.commands.client;

import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.client.Client;
import arb.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddClientCommand}.
 */
public class AddClientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addClient(validClient);

        assertCommandSuccess(new AddClientCommand(validClient), model,
                String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getAddressBook().getClientList().get(0);
        assertCommandFailure(new AddClientCommand(clientInList), model, AddClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
