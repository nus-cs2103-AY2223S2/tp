package seedu.address.logic.commands;

import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.UserPrefs;
/**
 * Contains integration tests (interaction with the ApplicationModel)
 * and unit tests for ListApplicationCommand.
 */
public class ListApplicationCommandTest {

    private ApplicationModel model;
    private ApplicationModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListApplicationCommand(), model,
                ListApplicationCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        assertCommandSuccess(new ListApplicationCommand(), model,
                ListApplicationCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
