package taa.logic.parser;

import org.junit.jupiter.api.Test;
import taa.commons.core.Messages;
import taa.logic.commands.ClassStatisticsCommand;
import taa.logic.commands.CommandTestUtil;
import taa.logic.commands.enums.ChartType;

public class ClassStatisticsCommandParserTest {
    private final ClassStatisticsCommandParser commandParser = new ClassStatisticsCommandParser();

    @Test
    public void parse_attendance_success() {
        CommandParserTestUtil.assertParseSuccess(commandParser,
            CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.CLASS_STAT_DESC_ATTENDANCE,
            new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE));
    }

    @Test
    public void parse_grades_withAssignmentName_success() {
        CommandParserTestUtil.assertParseSuccess(commandParser,
            CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.CLASS_STAT_DESC_GRADES
                + CommandTestUtil.ASSIGNMENT_NAME_DESC_TEST1,
            new ClassStatisticsCommand(ChartType.CLASS_GRADES, CommandTestUtil.VALID_ASSIGNMENT_NAME_TEST1));
    }

    @Test
    public void parse_noStatType_fails() {
        // no prefixes at all
        CommandParserTestUtil.assertParseFailure(commandParser,
            CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.PREAMBLE_NON_EMPTY,
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ClassStatisticsCommand.MESSAGE_USAGE));

        // just missing statType prefix
        CommandParserTestUtil.assertParseFailure(commandParser,
            CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.ASSIGNMENT_NAME_DESC_TEST1,
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ClassStatisticsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStatType_fails() {
        CommandParserTestUtil.assertParseFailure(commandParser,
            CommandTestUtil.INVALID_CLASS_STAT_ASSIGNMENT,
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ClassStatisticsCommand.MESSAGE_UNKNOWN_FIELD));
    }
}
