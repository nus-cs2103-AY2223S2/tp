package seedu.address.logic.commands.deckcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ShowDecksCommand.
 */
public class ShowDecksCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        expectedModel.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        assertCommandSuccess(new ShowDecksCommand(), model, ShowDecksCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        expectedModel.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        assertCommandSuccess(new ShowDecksCommand(), model, ShowDecksCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
