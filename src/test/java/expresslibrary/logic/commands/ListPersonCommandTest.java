package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.logic.commands.CommandTestUtil.showPersonAtIndex;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * ListCommand.
 */
public class ListPersonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());
        expectedModel = new ModelManager(model.getExpressLibrary(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPersonCommand(), model, ListPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListPersonCommand(), model, ListPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
