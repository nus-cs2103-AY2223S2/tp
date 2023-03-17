package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalFriendlyLink;

import org.junit.jupiter.api.Test;

import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class SummariseCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    public void execute_emptyModel() {
        model = new ModelManager(new FriendlyLink(), new UserPrefs());
        expectedModel = new ModelManager(new FriendlyLink(), new UserPrefs());
        String expectedMessage = String.format(SummariseCommand.MESSAGE_SUCCESS,
                String.format(SummariseCommand.ELDERLY_STATISTICS, 0),
                String.format(SummariseCommand.VOLUNTEER_STATISTICS, 0),
                String.format(SummariseCommand.PAIR_STATISTICS, 0)
                );
        assertCommandSuccess(new SummariseCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_typicalModel() {
        model = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalFriendlyLink(), new UserPrefs());
        String expectedMessage = String.format(SummariseCommand.MESSAGE_SUCCESS,
                String.format(SummariseCommand.ELDERLY_STATISTICS, 7),
                String.format(SummariseCommand.VOLUNTEER_STATISTICS, 7),
                String.format(SummariseCommand.PAIR_STATISTICS, 3)
        );
        assertCommandSuccess(new SummariseCommand(), model, expectedMessage, expectedModel);

    }
}
