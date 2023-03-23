package arb.logic.commands.tag;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTagCommand.
 */
public class ListTagCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListTagCommand(), ListType.CLIENT, ListType.TAG, model,
                ListTagCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_currentListShownProject_success() {
        assertCommandSuccess(new ListTagCommand(), ListType.PROJECT, ListType.TAG, model,
                ListTagCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_currentListShownTag_success() {
        assertCommandSuccess(new ListTagCommand(), ListType.TAG, ListType.TAG, model,
                ListTagCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
