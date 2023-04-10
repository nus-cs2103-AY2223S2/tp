package seedu.powercards.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.powercards.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.cardcommands.EditCardCommand;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.Model;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.testutil.EditCardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_GRAVITY = "What is gravity";
    public static final String VALID_QUESTION_PHOTOSYNTHESIS = "What is photosynthesis";
    public static final String VALID_ANSWER_GRAVITY = "A force of attraction between objects due to their mass";
    public static final String VALID_ANSWER_PHOTOSYNTHESIS = "The process by which plants convert sunlight into energy";
    public static final String VALID_TAG_HARD = "Hard";
    public static final String VALID_TAG_MEDIUM = "Medium";
    public static final String VALID_DECK_SCIENCE = "Science";
    public static final String VALID_DECK_SOCIOLOGY = "Sociology";

    public static final String QUESTION_DESC_GRAVITY = " " + PREFIX_QUESTION + VALID_QUESTION_GRAVITY;
    public static final String QUESTION_DESC_PHOTOSYNTHESIS = " " + PREFIX_QUESTION + VALID_QUESTION_PHOTOSYNTHESIS;
    public static final String ANSWER_DESC_GRAVITY = " " + PREFIX_ANSWER + VALID_ANSWER_GRAVITY;
    public static final String ANSWER_DESC_PHOTOSYNTHESIS = " " + PREFIX_ANSWER + VALID_ANSWER_PHOTOSYNTHESIS;
    public static final String TAG_DESC_MEDIUM = " " + PREFIX_TAG + VALID_TAG_MEDIUM;
    public static final String TAG_DESC_HARD = " " + PREFIX_TAG + VALID_TAG_HARD;

    public static final String INVALID_QUESTION_DESC = " " + PREFIX_QUESTION; // empty string not allowed for question
    public static final String INVALID_ANSWER_DESC = " " + PREFIX_ANSWER; // empty string not allowed for answer
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hard*"; // '*' not allowed in tags

    public static final String DECK_DESC_SOCIOLOGY = " " + VALID_DECK_SOCIOLOGY;
    public static final String DECK_DESC_SCIENCE = " " + VALID_DECK_SCIENCE;

    public static final String INVALID_DECK_DESC = "";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCardCommand.EditCardDescriptor DESC_GRAVITY;
    public static final EditCardCommand.EditCardDescriptor DESC_PHOTOSYNTHESIS;

    static {
        DESC_GRAVITY = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_GRAVITY)
                .withAnswer(VALID_ANSWER_GRAVITY)
                .withTag(VALID_TAG_MEDIUM).build();
        DESC_PHOTOSYNTHESIS = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS)
                .withTag(VALID_TAG_HARD).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the master deck, filtered card list and selected card in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MasterDeck expectedMasterDeck = new MasterDeck(actualModel.getMasterDeck());
        List<Card> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMasterDeck, actualModel.getMasterDeck());
        assertEquals(expectedFilteredList, actualModel.getFilteredCardList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the card at the given {@code targetIndex} in the
     * {@code model}'s master deck.
     */
    public static void showCardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCardList().size());
        Card card = model.getFilteredCardList().get(targetIndex.getZeroBased());
        final String otherQuestion = card.getQuestion().question;
        model.updateFilteredCardList(c -> c.getQuestion().question.equals(otherQuestion));
        assertEquals(1, model.getFilteredCardList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the deck at the given {@code targetIndex} in the
     * {@code model}'s master deck.
     */
    public static void showDeckAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDeckList().size());
        Deck deck = model.getFilteredDeckList().get(targetIndex.getZeroBased());
        final String otherDeckName = deck.getDeckName();
        model.updateFilteredDeckList(d -> d.getDeckName().equals(otherDeckName));
        assertEquals(1, model.getFilteredDeckList().size());
    }

}
