package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOpeningAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OPENING;
import static seedu.address.testutil.TypicalOpenings.getTypicalUltron;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.ultron.logic.commands.ListCommand;
import seedu.ultron.model.Model;
import seedu.ultron.model.ModelManager;
import seedu.ultron.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUltron(), new UserPrefs());
        expectedModel = new ModelManager(model.getUltron(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showOpeningAtIndex(model, INDEX_FIRST_OPENING);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
