package codoc.logic.commands;

import static codoc.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static codoc.testutil.TypicalPersons.getTypicalCodoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCodoc(), new UserPrefs());
        expectedModel = new ModelManager(model.getCodoc(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showPersonAtIndex(model, INDEX_FIRST_PERSON);
        CommandTestUtil.assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
