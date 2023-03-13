package seedu.fitbook.logic.commands.client;

import static seedu.fitbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fitbook.testutil.client.TypicalClients.getTypicalFitBook;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.AddCommand;
import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.FitBookModelManager;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.testutil.client.ClientBuilder;

/**
 * Contains integration tests (interaction with the FitBookModel) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private FitBookModel model;

    @BeforeEach
    public void setUp() {
        model = new FitBookModelManager(getTypicalFitBook(), getTypicalFitBookExerciseRoutine(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        FitBookModel expectedFitBookModel =
                new FitBookModelManager(model.getFitBook(), model.getFitBookExerciseRoutine(), new UserPrefs());
        expectedFitBookModel.addClient(validClient);

        CommandTestUtil.assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedFitBookModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getFitBook().getClientList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
