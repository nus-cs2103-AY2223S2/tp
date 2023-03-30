package seedu.modtrek.logic.commands;

import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());
        expectedModel = new ModelManager(model.getDegreeProgression(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewCommand(false), model, ViewCommand.MESSAGE_MODULES_SUCCESS, expectedModel);
    }
}
