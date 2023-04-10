package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.application.comparator.CompanyNameComparator;
import seedu.address.model.application.comparator.InterviewDateComparator;
import seedu.address.model.application.comparator.JobTitleComparator;
import seedu.address.model.application.comparator.StatusComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new CompanyNameComparator());
        assertParseSuccess(parser, " n/", expectedSortCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n \t ", expectedSortCommand);

        // with other prefixes
        expectedSortCommand =
                new SortCommand(new JobTitleComparator());
        assertParseSuccess(parser, " j/", expectedSortCommand);

        expectedSortCommand =
                new SortCommand(new StatusComparator());
        assertParseSuccess(parser, " s/", expectedSortCommand);

        expectedSortCommand =
                new SortCommand(new InterviewDateComparator());
        assertParseSuccess(parser, " d/", expectedSortCommand);

    }

}
