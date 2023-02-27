package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalFitBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.FitBookModel;
import seedu.address.model.FitBookModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the FitBookModel) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private FitBookModel model;

    @BeforeEach
    public void setUp() {
        model = new FitBookModelManager(getTypicalFitBook(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        FitBookModel expectedFitBookModel = new FitBookModelManager(model.getFitBook(), new UserPrefs());
        expectedFitBookModel.addClient(validClient);

        assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedFitBookModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getFitBook().getClientList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
