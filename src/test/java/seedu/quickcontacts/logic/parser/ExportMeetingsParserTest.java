package seedu.quickcontacts.logic.parser;

import static seedu.quickcontacts.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.quickcontacts.logic.parser.CliSyntax.PREFIX_START;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.quickcontacts.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.quickcontacts.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.logic.commands.ExportMeetingsCommand;
import seedu.quickcontacts.model.meeting.DateTime;

public class ExportMeetingsParserTest {
    private static final String SAMPLE_START_DATE = "01/01/2022";
    private static final String SAMPLE_END_DATE = "01/02/2022";
    private final ExportMeetingsParser parser = new ExportMeetingsParser();

    @Test
    public void parse_success() {
        String input = " " + PREFIX_MEETING_TITLE + INDEX_FIRST_MEETING.getOneBased();
        assertParseSuccess(parser, input, new ExportMeetingsCommand(List.of(INDEX_FIRST_MEETING), null, null));
    }

    @Test
    public void parse_noFields_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportMeetingsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_withStart_success() {
        assertParseSuccess(parser, " " + PREFIX_START + SAMPLE_START_DATE, new ExportMeetingsCommand(new ArrayList<>(),
                new DateTime(SAMPLE_START_DATE), null));
    }

    @Test
    public void parse_withEnd_success() {
        assertParseSuccess(parser, " " + PREFIX_START + SAMPLE_END_DATE, new ExportMeetingsCommand(new ArrayList<>(),
                null, new DateTime(SAMPLE_END_DATE)));
    }

}
