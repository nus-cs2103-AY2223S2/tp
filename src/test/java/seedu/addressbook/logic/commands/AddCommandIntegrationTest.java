package seedu.addressbook.logic.commands;

import static seedu.addressbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.addressbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.addressbook.testutil.TypicalClients.getTypicalFitBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.addressbook.model.FitBookModel;
import seedu.addressbook.model.FitBookModelManager;
import seedu.addressbook.model.UserPrefs;
import seedu.addressbook.model.client.Client;
import seedu.addressbook.testutil.ClientBuilder;

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
