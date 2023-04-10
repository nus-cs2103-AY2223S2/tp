package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_TAG_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_TAG_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PERSON_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_TAG_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_TAG_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnTagEventCommand;
import seedu.address.model.person.fields.Name;

class UnTagEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnTagEventCommand.MESSAGE_USAGE);

    private final UnTagEventCommandParser parser = new UnTagEventCommandParser();

    @Test
    void parse_missingParts_failure() {
        // no person
        assertParseFailure(parser, EVENT_TAG_DESC_1, MESSAGE_INVALID_FORMAT);

        // no event
        assertParseFailure(parser, PERSON_TAG_DESC_1, MESSAGE_INVALID_FORMAT);

        // neither
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_hasPreamble_failure() {
        assertParseFailure(parser, String.format("1 %s%s", PERSON_TAG_DESC_1, EVENT_TAG_DESC_1),
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidValue_failure() {
        assertParseFailure(parser, PERSON_TAG_DESC_1 + INVALID_EVENT_TAG_DESC,
                MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, INVALID_PERSON_TAG_DESC + EVENT_TAG_DESC_1, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values with only first captured
        assertParseFailure(parser, INVALID_PERSON_TAG_DESC + INVALID_EVENT_TAG_DESC,
                MESSAGE_INVALID_INDEX);
    }

    @Test
    void parse_allFields_success() {
        UnTagEventCommand expectedCommand = new UnTagEventCommand(Index.fromZeroBased(0), new Name(VALID_NAME_AMY));
        assertParseSuccess(parser, PERSON_TAG_DESC_1 + EVENT_TAG_DESC_1, expectedCommand);
    }

    @Test
    void parse_duplicateFields_acceptsLast() {
        UnTagEventCommand expectedCommand = new UnTagEventCommand(Index.fromZeroBased(0), new Name(VALID_NAME_AMY));
        assertParseSuccess(parser,
                PERSON_TAG_DESC_2 + PERSON_TAG_DESC_1 + EVENT_TAG_DESC_2 + EVENT_TAG_DESC_1,
                expectedCommand);
    }
}
