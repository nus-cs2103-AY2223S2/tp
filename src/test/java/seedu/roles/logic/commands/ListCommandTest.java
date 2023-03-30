package seedu.roles.logic.commands;

import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.logic.commands.CommandTestUtil.showRoleAtIndex;
import static seedu.roles.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRoleBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getRoleBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showRoleAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
