package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static teambuilder.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static teambuilder.logic.commands.CommandTestUtil.VALID_ORDER;
import static teambuilder.logic.commands.CommandTestUtil.VALID_SORT_BY;
import static teambuilder.logic.parser.CommandParserTestUtil.assertParseFailure;
import static teambuilder.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import teambuilder.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        SortCommand expectedSortCommand = new SortCommand(VALID_ORDER, VALID_SORT_BY);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + VALID_ORDER + " " + VALID_SORT_BY,
                expectedSortCommand);

        // no whitespace
        assertParseSuccess(parser, VALID_ORDER + " " + VALID_SORT_BY,
                expectedSortCommand);

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // missing order
        assertParseFailure(parser, VALID_SORT_BY, expectedMessage);

        // missing sort by
        assertParseFailure(parser, VALID_ORDER, expectedMessage);
    }
}
