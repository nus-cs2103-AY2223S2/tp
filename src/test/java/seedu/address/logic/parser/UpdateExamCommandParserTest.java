package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exam.UpdateExamCommand;
import seedu.address.logic.parser.exam.UpdateExamCommandParser;

class UpdateExamCommandParserTest {
    private static final String VALID_START_TIME = "2027-04-30T12:00";
    private static final String VALID_END_TIME = "2027-04-30T14:00";
    private UpdateExamCommandParser parser = new UpdateExamCommandParser();
    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        UpdateExamCommand.MESSAGE_USAGE);
        //Missing name
        String input1 = " index/1"
                + " exam/Math"
                + " start/" + VALID_START_TIME
                + " end/" + VALID_END_TIME;
        assertParseFailure(parser, input1, expected);

        //Missing index
        String input2 = " name/Amy"
                + " exam/Math"
                + " start/" + VALID_START_TIME
                + " end/" + VALID_END_TIME;
        assertParseFailure(parser, input2, expected);
    }

    @Test
    public void parse_allOptionalFieldsMissing_failure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        UpdateExamCommand.MESSAGE_USAGE);
        String input1 = " name/Amy"
                + " index/1";
        assertParseFailure(parser, input1, expected);

    }
}
