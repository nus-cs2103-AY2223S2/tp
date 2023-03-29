package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exam.CreateExamCommand;
import seedu.address.logic.parser.exam.CreateExamCommandParser;


public class CreateExamCommandParserTest {
    private static final String VALID_START_TIME = "2027-04-30T12:00";
    private static final String VALID_END_TIME = "2027-04-30T:14:00";
    private static final String VALID_EXAM_NAME = "Math";
    private static final String VALID_STUDENT_NAME = "Amy";
    private CreateExamCommandParser parser = new CreateExamCommandParser();
    @Test
    public void parse_fieldsMissing_failure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        CreateExamCommand.MESSAGE_USAGE);
        //Missing end time
        String input1 = " name/" + VALID_STUDENT_NAME
                + " exam/" + VALID_EXAM_NAME
                + " start/" + VALID_START_TIME;
        assertParseFailure(parser, input1, expected);

        //Missing start time
        String input2 = " name/" + VALID_STUDENT_NAME
                + " exam/" + VALID_EXAM_NAME
                + " end/" + VALID_END_TIME;
        assertParseFailure(parser, input2, expected);

        //Missing student name
        String input3 = " exam/" + VALID_EXAM_NAME
                + " start/" + VALID_START_TIME
                + " end/" + VALID_END_TIME;
        assertParseFailure(parser, input3, expected);

        //Missing exam name
        String input4 = " name/" + VALID_STUDENT_NAME
                + " start/" + VALID_START_TIME
                + " end/" + VALID_END_TIME;
        assertParseFailure(parser, input4, expected);
    }
}
