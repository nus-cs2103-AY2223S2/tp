package seedu.powercards.logic.commands.cardcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ShowDecksCommand.
 */
public class ShowCardsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
    }

    @Test
    public void execute_cardListIsNotFiltered_showsSameCardList() {
        expectedModel.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
        expectedModel.selectDeck(INDEX_FIRST);
        CommandResult expectedCommandResult = new CommandResult(
                ShowCardsCommand.MESSAGE_SUCCESS, false, false, false, false, false, false, false, false, true, false);
        model.selectDeck(INDEX_FIRST);
        assertCommandSuccess(new ShowCardsCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_cardListIsFiltered_showsEverything() {
        expectedModel.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
        expectedModel.selectDeck(INDEX_FIRST);
        CommandResult expectedCommandResult = new CommandResult(
                ShowCardsCommand.MESSAGE_SUCCESS, false, false, false, false, false, false, false, false, true, false);
        model.selectDeck(INDEX_FIRST);
        assertCommandSuccess(new ShowCardsCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.selectDeck(INDEX_FIRST);
        expectedModel.selectDeck(INDEX_FIRST);
        CommandResult commandResult = new ShowCardsCommand().execute(model);
        assertEquals(expectedModel.getFilteredCardList(), model.getFilteredCardList());
        assertEquals("Listed all cards in selected deck", commandResult.getFeedbackToUser());
    }

    @Test
    void equals() {
        ShowCardsCommand command = new ShowCardsCommand();
        assertEquals(command, command); // same instance -> returns true
        assertEquals(command, new ShowCardsCommand()); // same class -> returns true
        assertNotEquals(command, null); // null -> returns false
    }
}
