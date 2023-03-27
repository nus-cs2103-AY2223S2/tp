package seedu.event.logic.commands;

import static seedu.event.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.event.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.event.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.event.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.UserPrefs;
import seedu.event.testutil.TypicalContacts;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEventBook(),
                TypicalContacts.getTypicalContactList(), new UserPrefs());
        expectedModel = new ModelManager(model.getEventBook(),
                TypicalContacts.getTypicalContactList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
