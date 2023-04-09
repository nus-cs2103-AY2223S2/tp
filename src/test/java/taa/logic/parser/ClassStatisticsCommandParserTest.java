package taa.logic.parser;

import org.junit.jupiter.api.Test;
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
}
