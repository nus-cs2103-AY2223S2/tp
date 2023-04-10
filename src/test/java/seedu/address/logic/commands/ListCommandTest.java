package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEntityAtIndex;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.Classification;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalReroll(), new UserPrefs());
        expectedModel = new ModelManager(model.getReroll(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEntityAtIndex(model, 0);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsMobs() {
        expectedModel.listMobs();
        assertCommandSuccess(new ListCommand(new Classification("mob")),
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsChars() {
        expectedModel.listCharacters();
        assertCommandSuccess(new ListCommand(new Classification("char")),
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilteredThenUnfiltered_showsAll() {
        model.listCharacters();
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsItems() {
        expectedModel.listItems();
        assertCommandSuccess(new ListCommand(new Classification("item")),
                model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
