package t154.CLIpboard.logic.commands;

import static t154.CLIpboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static t154.CLIpboard.logic.commands.CommandTestUtil.showStudentAtIndex;
import static t154.CLIpboard.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static t154.CLIpboard.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import t154.CLIpboard.model.Model;
import t154.CLIpboard.model.ModelManager;
import t154.CLIpboard.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
