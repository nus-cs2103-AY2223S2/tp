package seedu.address.logic.commands.cardcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
    void equals() {
        ShowCardsCommand command = new ShowCardsCommand();
        assertEquals(command, command); // same instance -> returns true
        assertEquals(command, new ShowCardsCommand()); // same class -> returns true
        assertNotEquals(command, null); // null -> returns false
    }
}
