package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.SortCommand;
import seedu.dengue.logic.comparators.PersonNameComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortCommand() {
        assertParseSuccess(parser, "n/", new SortCommand(new PersonNameComparator(), "NAME"));
    }

    @Test
    public void parse_validArgs_needsTrimming_returnsSortCommand() {
        assertParseSuccess(parser, "    n/      ", new SortCommand(new PersonNameComparator(), "NAME"));
    }

    @Test
    public void parse_validArgs_needsLowercase_returnsSortCommand() {
        assertParseSuccess(parser, "N/", new SortCommand(new PersonNameComparator(), "NAME"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "p/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
