package seedu.internship.logic.commands;

import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternshipCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest1 {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipCatalogue(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipCatalogue(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
