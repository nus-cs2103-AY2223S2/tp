package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HARD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEDIUM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.card.Card;
import seedu.address.testutil.CardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Card expectedCard = new CardBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS)
                .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_MEDIUM).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_PHOTOSYNTHESIS
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_MEDIUM, new AddCommand(expectedCard));

        // multiple question - last question accepted
        assertParseSuccess(parser, QUESTION_DESC_GRAVITY + QUESTION_DESC_PHOTOSYNTHESIS
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_MEDIUM, new AddCommand(expectedCard));

        // multiple answer - last answer accepted
        assertParseSuccess(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_GRAVITY
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_MEDIUM, new AddCommand(expectedCard));

        // multiple tags - all accepted
        Card expectedCardMultipleTags = new CardBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS)
                .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_MEDIUM, VALID_TAG_HARD).build();
        assertParseSuccess(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS
                + TAG_DESC_MEDIUM + TAG_DESC_HARD, new AddCommand(expectedCardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Card expectedCard = new CardBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS)
                .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertParseSuccess(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS,
                new AddCommand(expectedCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_QUESTION_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, QUESTION_DESC_PHOTOSYNTHESIS + VALID_ANSWER_PHOTOSYNTHESIS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_PHOTOSYNTHESIS + VALID_ANSWER_PHOTOSYNTHESIS,
                expectedMessage);
    }
}
