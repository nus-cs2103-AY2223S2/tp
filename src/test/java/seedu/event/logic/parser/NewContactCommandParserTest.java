package seedu.event.logic.parser;

import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.event.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.event.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.event.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.event.logic.commands.NewContactCommand;

class NewContactCommandParserTest {

    private NewContactCommandParser parser = new NewContactCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewContactCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB, expectedMessage);
    }

}
