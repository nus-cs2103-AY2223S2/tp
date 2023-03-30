package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.quickcontacts.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.MarkAsNotDoneCommand;

public class MarkAsNotDoneParserTest {
    private final MarkAsNotDoneParser parser = new MarkAsNotDoneParser();

    @Test
    public void parse_success() {
        String input = " " + PREFIX_MEETING_TITLE + INDEX_FIRST_MEETING.getOneBased();
        assertParseSuccess(parser, input, new MarkAsNotDoneCommand(List.of(INDEX_FIRST_MEETING)));
    }

    @Test
    public void parse_noFields_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAsNotDoneCommand.MESSAGE_USAGE));
    }
}
