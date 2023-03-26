package seedu.wife.logic.parser.foodcommandparser;

import static seedu.wife.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.wife.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.ListByTagCommand;

public class ListByTagCommandParserTest {
    private ListByTagCommandParser parser = new ListByTagCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListByTagCommand.MESSAGE_USAGE);

        // empty name
        assertParseFailure(parser, PREFIX_NAME + " ", expectedMessage);

        // valid and empty name
        assertParseFailure(parser, PREFIX_NAME + VALID_TAG_DAIRY + " " + PREFIX_NAME + " ", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "", expectedMessage);
    }

}
