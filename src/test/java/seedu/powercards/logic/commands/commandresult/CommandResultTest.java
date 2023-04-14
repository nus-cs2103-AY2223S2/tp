package seedu.powercards.logic.commands.commandresult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test
    public void constructor_noArguments_commandResultWithDefaultValues() {
        CommandResult commandResult = new CommandResult("feedback");

        assertEquals("feedback", commandResult.getFeedbackToUser());
        assertFalse(commandResult.isShowHelp());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isStartReview());
        assertFalse(commandResult.isEndReview());
        assertFalse(commandResult.isSelectDeck());
        assertFalse(commandResult.isUnselectDeck());
        assertFalse(commandResult.isFindCards());
        assertFalse(commandResult.isFindDecks());
        assertFalse(commandResult.isShowCards());
        assertFalse(commandResult.isShowDecks());
    }

    @Test
    public void constructor_allArguments_commandResultWithSpecifiedValues() {
        CommandResult commandResult = new CommandResult(
                "feedback", true, true, true, true, true, true, true, true, true, true);

        assertEquals("feedback", commandResult.getFeedbackToUser());
        assertTrue(commandResult.isShowHelp());
        assertTrue(commandResult.isExit());
        assertTrue(commandResult.isStartReview());
        assertTrue(commandResult.isEndReview());
        assertTrue(commandResult.isSelectDeck());
        assertTrue(commandResult.isUnselectDeck());
        assertTrue(commandResult.isFindCards());
        assertTrue(commandResult.isFindDecks());
        assertTrue(commandResult.isShowCards());
        assertTrue(commandResult.isShowDecks());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertEquals(commandResult, new CommandResult("feedback"));
        assertEquals(commandResult, new CommandResult("feedback",
                        false, false, false, false, false, false, false, false, false, false));

        // same object -> returns true
        assertEquals(commandResult, commandResult);

        // null -> returns false
        assertNotEquals(null, commandResult);

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertNotEquals(commandResult, new CommandResult("different"));

        // different showHelp value -> returns false
        assertNotEquals(commandResult, new CommandResult(
                "feedback", true, false, false, false, false, false, false, false, false, false));

        // different exit value -> returns false
        assertNotEquals(commandResult, new CommandResult("feedback",
                false, true, false, false, false, false, false, false, false, false));

        // different startReview value -> return false
        assertNotEquals(commandResult, new CommandResult("feedback",
                false, false, true, false, false, false, false, false, false, false));

        // different endReview value -> return false
        assertNotEquals(commandResult, new CommandResult("feedback",
                false, false, false, true, false, false, false, false, false, false));

        // different selectDeck value -> return false
        assertNotEquals(commandResult, new CommandResult("feedback",
                false, false, false, false, true, false, false, false, false, false));

        // different unSelectDeck value -> return false
        assertNotEquals(commandResult, new CommandResult("feedback",
                false, false, false, false, false, true, false, false, false, false));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(
                "feedback", true, false, false, false, false, false, false, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(
                "feedback", false, true, false, false, false, false, false, false, false, false).hashCode());

        // different startReview value -> return different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, true, false, false, false, false, false, false, false).hashCode());

        // different endReview value -> return different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, true, false, false, false, false, false, false).hashCode());

        // different selectDeck value -> return different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false, true, false, false, false, false, false).hashCode());

        // different unSelectDeck value -> return different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                false, false, false, false, false, true, false, false, false, false).hashCode());

    }
}
