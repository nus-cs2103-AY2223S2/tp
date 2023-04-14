package seedu.powercards.logic.commands.reviewcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.tag.Tag;

public class SetReviewLimitCommandTest {

    @Test
    public void execute_numCardsPerReviewLessThanZero_commandSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        int initalSize = model.getReviewCardList().size();
        CommandResult expectedCommandResult = new CommandResult(SetReviewLimitCommand.MESSAGE_SUCCESS_NO_LIMIT);
        CommandResult actualCommandResult = new SetReviewLimitCommand(-1).execute(model);

        assertEquals(expectedCommandResult, actualCommandResult);
        assertTrue(model.getReviewCardList().size() == initalSize);
    }

    @Test
    public void execute_numCardsPerReviewGreaterThanZero_commandSuccess() throws CommandException {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.reviewDeck(INDEX_FIRST, List.of(new Tag.TagName[]{Tag.TagName.HARD}));
        int numCardsPerReview = 1;
        CommandResult expectedCommandResult =
                new CommandResult(String.format(SetReviewLimitCommand.MESSAGE_SUCCESS_SET_LIMIT, numCardsPerReview));
        CommandResult actualCommandResult = new SetReviewLimitCommand(numCardsPerReview).execute(model);

        assertEquals(expectedCommandResult, actualCommandResult);
        assertEquals(model.getReviewCardList().size(), numCardsPerReview);
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        SetReviewLimitCommand command = new SetReviewLimitCommand(10);
        assertTrue(command.equals(command));
    }

    @Test
    public void equals_nullCommand_returnsFalse() {
        SetReviewLimitCommand command = new SetReviewLimitCommand(10);
        assertFalse(command.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        SetReviewLimitCommand command = new SetReviewLimitCommand(10);
        assertFalse(command.equals(1));
    }

    @Test
    public void equals_differentNumCardsPerReview_returnsFalse() {
        SetReviewLimitCommand command1 = new SetReviewLimitCommand(10);
        SetReviewLimitCommand command2 = new SetReviewLimitCommand(5);
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_sameCommandDifferentObject_returnsTrue() {
        SetReviewLimitCommand command1 = new SetReviewLimitCommand(10);
        SetReviewLimitCommand command2 = new SetReviewLimitCommand(10);
        assertTrue(command1.equals(command2));
    }

}
