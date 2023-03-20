package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_BOTH_INVALID_NRIC;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ELDERLY_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VOLUNTEER_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_ELDERLY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_ELDERLY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_VOLUNTEER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_VOLUNTEER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.information.Nric.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePairCommand;
import seedu.address.model.person.information.Nric;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeletePairCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeletePairCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePairCommandParserTest {

    private static final String PREAMBLE_NON_WHITESPACE = "not whitespace";

    private final DeletePairCommandParser parser = new DeletePairCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePairCommand() {
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NRIC_ELDERLY_DESC_AMY + NRIC_VOLUNTEER_DESC_BOB,
                new DeletePairCommand(new Nric(VALID_NRIC_AMY), new Nric(VALID_NRIC_BOB)));
    }

    @Test
    public void parse_hasPreamble_failure() {
        assertParseFailure(parser,
                PREAMBLE_NON_WHITESPACE + NRIC_ELDERLY_DESC_AMY + NRIC_VOLUNTEER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePairCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParts_failure() {
        // no volunteer specified
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + NRIC_ELDERLY_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePairCommand.MESSAGE_USAGE));

        // no elderly specified
        assertParseFailure(parser,
                PREAMBLE_NON_WHITESPACE + NRIC_VOLUNTEER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePairCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNrics_failure() {
        // both invalid elderly nric and volunteer nric
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INVALID_ELDERLY_NRIC_DESC + INVALID_VOLUNTEER_NRIC_DESC,
                String.format(MESSAGE_BOTH_INVALID_NRIC, MESSAGE_CONSTRAINTS));

        // invalid elderly nric followed by valid volunteer nric
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INVALID_ELDERLY_NRIC_DESC + NRIC_VOLUNTEER_DESC_BOB,
                String.format(MESSAGE_INVALID_PERSON_NRIC, "elderly", MESSAGE_CONSTRAINTS));

        // valid elderly nric followed by invalid volunteer nric
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + NRIC_ELDERLY_DESC_AMY + INVALID_VOLUNTEER_NRIC_DESC,
                String.format(MESSAGE_INVALID_PERSON_NRIC, "volunteer", MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NRIC_ELDERLY_DESC_BOB + NRIC_ELDERLY_DESC_AMY
                        + NRIC_VOLUNTEER_DESC_AMY + NRIC_VOLUNTEER_DESC_BOB,
                new DeletePairCommand(new Nric(VALID_NRIC_AMY), new Nric(VALID_NRIC_BOB)));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NRIC_ELDERLY_DESC_AMY
                        + INVALID_VOLUNTEER_NRIC_DESC + NRIC_VOLUNTEER_DESC_BOB,
                new DeletePairCommand(new Nric(VALID_NRIC_AMY), new Nric(VALID_NRIC_BOB)));
    }
}
