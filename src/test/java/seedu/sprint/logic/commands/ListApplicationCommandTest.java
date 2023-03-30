package seedu.sprint.logic.commands;

//import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
//import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
//import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.ApplicationModel;
import seedu.sprint.model.ApplicationModelManager;
import seedu.sprint.model.UserPrefs;
/**
 * Contains integration tests (interaction with the ApplicationModel)
 * and unit tests for ListApplicationCommand.
 */
public class ListCommandTest {

    private ApplicationModel model;
    private ApplicationModel expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
    }


    /*
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListApplicationCommand(), model, commandHistory,
                ListApplicationCommand.MESSAGE_SUCCESS, expectedModel);
    }



    @Test
    public void execute_listIsFiltered_showsEverything() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        assertCommandSuccess(new ListApplicationCommand(), model, commandHistory,
                ListApplicationCommand.MESSAGE_SUCCESS, expectedModel);
    }
     */

}
