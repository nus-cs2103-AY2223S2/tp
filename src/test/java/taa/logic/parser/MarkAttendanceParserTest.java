package taa.logic.parser;

import org.junit.jupiter.api.Test;

import taa.model.student.Attendance;

public class MarkAttendanceParserTest {
    private static final String MISSING_WEEK = " 1";

    @Test
    public void parse() {
        MarkAttendanceParser parser = new MarkAttendanceParser();
        CommandParserTestUtil.assertParseFailure(parser, MISSING_WEEK, Attendance.WEEK_ERROR_MSG);
    }
}
