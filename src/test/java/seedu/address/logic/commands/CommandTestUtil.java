package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MasterDeck;
import seedu.address.model.Model;
import seedu.address.model.card.Card;
import seedu.address.testutil.EditCardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_GRAVITY = "What is gravity";
    public static final String VALID_NAME_PHOTOSYNTHESIS = "What is photosynthesis";
    public static final String VALID_ANSWER_GRAVITY = "A force of attraction between objects due to their mass";
    public static final String VALID_ANSWER_PHOTOSYNTHESIS = "The process by which plants convert sunlight into energy";
    public static final String VALID_TAG_HARD = "Hard";
    public static final String VALID_TAG_MEDIUM = "Medium";
    public static final String VALID_DECK_SCIENCE = "Science";

    public static final String NAME_DESC_AMY = " " + PREFIX_QUESTION + VALID_QUESTION_GRAVITY;
    public static final String NAME_DESC_BOB = " " + PREFIX_QUESTION + VALID_NAME_PHOTOSYNTHESIS;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ANSWER + VALID_ANSWER_GRAVITY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ANSWER + VALID_ANSWER_PHOTOSYNTHESIS;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_MEDIUM;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HARD;

    public static final String INVALID_NAME_DESC = " " + PREFIX_QUESTION + "James&"; // '&' not allowed in names
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ANSWER; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCardDescriptor DESC_AMY;
    public static final EditCommand.EditCardDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_GRAVITY).withAnswer(VALID_ANSWER_GRAVITY)
                .withTags(VALID_TAG_MEDIUM).build();
        DESC_BOB = new EditCardDescriptorBuilder()
                .withQuestion(VALID_NAME_PHOTOSYNTHESIS).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS)
                .withTags(VALID_TAG_HARD, VALID_TAG_MEDIUM).build();
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
     * - the address book, filtered card list and selected card in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MasterDeck expectedAddressBook = new MasterDeck(actualModel.getMasterDeck());
        List<Card> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getMasterDeck());
        assertEquals(expectedFilteredList, actualModel.getFilteredCardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the card at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCardList().size());
        Card card = model.getFilteredCardList().get(targetIndex.getZeroBased());
        final String otherQuestion = card.getQuestion().question;
        model.updateFilteredCardList(c -> c.getQuestion().question.equals(otherQuestion));
        assertEquals(1, model.getFilteredCardList().size());
    }

}
