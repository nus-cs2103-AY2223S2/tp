package seedu.wife.logic.commands.foodcommands;

import static seedu.wife.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortByExpiryCommandTest.
 */
public class SortByExpiryCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWife(), new UserPrefs());
        expectedModel = new ModelManager(model.getWife(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new SortByExpiryCommand(), model, SortByExpiryCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
