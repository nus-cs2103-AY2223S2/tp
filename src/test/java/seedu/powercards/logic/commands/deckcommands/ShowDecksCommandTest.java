package seedu.powercards.logic.commands.deckcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;

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
    public void execute_deckListIsNotFiltered_showsSameDeckList() {
        CommandResult expectedCommandResult = new CommandResult(
                ShowDecksCommand.MESSAGE_SUCCESS, false, false, false, false, false, false, false, false, false, true);
        expectedModel.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        assertCommandSuccess(new ShowDecksCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deckListIsFiltered_showsEverything() {
        CommandResult expectedCommandResult = new CommandResult(
                ShowDecksCommand.MESSAGE_SUCCESS, false, false, false, false, false, false, false, false, false, true);
        expectedModel.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        assertCommandSuccess(new ShowDecksCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    void equals() {
        ShowDecksCommand command = new ShowDecksCommand();
        assertEquals(command, command); // same instance -> returns true
        assertEquals(command, new ShowDecksCommand()); // same class -> returns true
        assertNotEquals(command, null); // null -> returns false
    }
}
