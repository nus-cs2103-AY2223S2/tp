package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showElderlyAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showVolunteerAtIndex;
import static seedu.address.testutil.TestUtil.getTypicalModelManager;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = getTypicalModelManager();
        expectedModel = new ModelManagerBuilder()
                .withFriendlyLink(model.getFriendlyLink())
                .build();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showElderlyAtIndex(model, INDEX_FIRST_PERSON);
        showVolunteerAtIndex(model, INDEX_FIRST_PERSON);
        // TODO: showPairAtIndex()
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
