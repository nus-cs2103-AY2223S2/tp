package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_GRAVITY;
import static seedu.address.logic.commands.CommandTestUtil.QUESTION_DESC_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HARD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEDIUM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.cardcommands.AddCommand;
import seedu.address.logic.commands.cardcommands.AddCommand.AddCardDescriptor;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Question;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddCardDescriptorBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        AddCardDescriptor expectedCardDescriptor = new AddCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS)
                .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTag(VALID_TAG_MEDIUM).build(); // No deck specified

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + QUESTION_DESC_PHOTOSYNTHESIS
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_MEDIUM, new AddCommand(expectedCardDescriptor));

        // multiple question - last question accepted
        assertParseSuccess(parser, QUESTION_DESC_GRAVITY + QUESTION_DESC_PHOTOSYNTHESIS
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_MEDIUM, new AddCommand(expectedCardDescriptor));

        // multiple answer - last answer accepted
        assertParseSuccess(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_GRAVITY
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_MEDIUM, new AddCommand(expectedCardDescriptor));

        // multiple tags - all accepted
        AddCardDescriptor expectedCardMultipleTags = new AddCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS)
                .withTag(VALID_TAG_MEDIUM).build();
        assertParseSuccess(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS
                + TAG_DESC_MEDIUM, new AddCommand(expectedCardMultipleTags));
    }


    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        AddCardDescriptor expectedCard = new AddCardDescriptorBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS)
                .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build(); // tags are optional
        assertParseSuccess(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS,
                new AddCommand(expectedCard));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing question prefix
        assertParseFailure(parser, VALID_QUESTION_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS,
                expectedMessage);

        // missing answer prefix

        assertParseFailure(parser, QUESTION_DESC_PHOTOSYNTHESIS + VALID_ANSWER_PHOTOSYNTHESIS,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_QUESTION_PHOTOSYNTHESIS + VALID_ANSWER_PHOTOSYNTHESIS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + ANSWER_DESC_GRAVITY
                + TAG_DESC_HARD, Question.MESSAGE_CONSTRAINTS);

        // invalid answer
        assertParseFailure(parser, QUESTION_DESC_PHOTOSYNTHESIS + INVALID_ANSWER_DESC
                + TAG_DESC_HARD, Answer.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, QUESTION_DESC_PHOTOSYNTHESIS + ANSWER_DESC_PHOTOSYNTHESIS
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + QUESTION_DESC_PHOTOSYNTHESIS
                        + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_HARD + TAG_DESC_HARD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
