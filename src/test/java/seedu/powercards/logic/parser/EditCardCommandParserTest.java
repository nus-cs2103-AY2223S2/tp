package seedu.powercards.logic.parser;

import static seedu.powercards.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.powercards.logic.commands.CommandTestUtil.ANSWER_DESC_GRAVITY;
import static seedu.powercards.logic.commands.CommandTestUtil.ANSWER_DESC_PHOTOSYNTHESIS;
import static seedu.powercards.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.powercards.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.powercards.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.powercards.logic.commands.CommandTestUtil.QUESTION_DESC_GRAVITY;
import static seedu.powercards.logic.commands.CommandTestUtil.TAG_DESC_HARD;
import static seedu.powercards.logic.commands.CommandTestUtil.TAG_DESC_MEDIUM;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_ANSWER_GRAVITY;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_QUESTION_GRAVITY;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.powercards.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.powercards.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.cardcommands.EditCardCommand;
import seedu.powercards.logic.commands.cardcommands.EditCardCommand.EditCardDescriptor;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Question;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.testutil.EditCardDescriptorBuilder;

public class EditCardCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE);

    private EditCardCommandParser parser = new EditCardCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_GRAVITY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCardCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUESTION_DESC_GRAVITY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUESTION_DESC_GRAVITY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_QUESTION_DESC, Question.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_GRAVITY + QUESTION_DESC_GRAVITY
                + TAG_DESC_HARD + TAG_DESC_MEDIUM;

        EditCardCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_GRAVITY)
                .withAnswer(VALID_ANSWER_GRAVITY)
                .withTag(VALID_TAG_MEDIUM).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_GRAVITY;
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withAnswer(VALID_ANSWER_GRAVITY).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + QUESTION_DESC_GRAVITY;
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_GRAVITY).build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ANSWER_DESC_GRAVITY;
        descriptor = new EditCardDescriptorBuilder().withAnswer(VALID_ANSWER_GRAVITY).build();
        expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_MEDIUM;
        descriptor = new EditCardDescriptorBuilder().withTag(VALID_TAG_MEDIUM).build();
        expectedCommand = new EditCardCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_GRAVITY
                + TAG_DESC_MEDIUM + ANSWER_DESC_GRAVITY + TAG_DESC_MEDIUM
                + ANSWER_DESC_PHOTOSYNTHESIS + TAG_DESC_HARD;

        EditCardCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
                .withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTag(VALID_TAG_HARD)
                .build();
        EditCardCommand expectedCommand = new EditCardCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
