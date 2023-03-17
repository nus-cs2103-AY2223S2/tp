package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PHOTOSYNTHESIS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        //        Card expectedCard = new CardBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        //
        //        // whitespace only preamble
        //        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
        //                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedCard));
        //
        //        // multiple names - last name accepted
        //        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB
        //                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedCard));
        //
        //        // multiple addresses - last address accepted
        //        assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_AMY
        //                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedCard));
        //
        //        // multiple tags - all accepted
        //        Card expectedCardMultipleTags = new CardBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
        //                .build();
        //        assertParseSuccess(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedCardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        //        Card expectedCard = new CardBuilder(AMY).withTags().build();
        //        assertParseSuccess(parser, NAME_DESC_AMY + ADDRESS_DESC_AMY,
        //                new AddCommand(expectedCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_PHOTOSYNTHESIS + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ANSWER_PHOTOSYNTHESIS,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_PHOTOSYNTHESIS + VALID_ANSWER_PHOTOSYNTHESIS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        //        assertParseFailure(parser, INVALID_NAME_DESC + ADDRESS_DESC_BOB
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Question.MESSAGE_CONSTRAINTS);
        //
        //        // invalid address
        //        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ADDRESS_DESC
        //                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Answer.MESSAGE_CONSTRAINTS);
        //
        //        // invalid tag
        //        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB
        //                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
        //
        //        // two invalid values, only first invalid value reported
        //        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ADDRESS_DESC,
        //                Question.MESSAGE_CONSTRAINTS);
        //
        //        // non-empty preamble
        //        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
        //                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
        //                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
