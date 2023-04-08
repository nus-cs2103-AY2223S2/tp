package taa.logic.parser;

import org.junit.jupiter.api.Test;

import taa.model.student.Attendance;

public class InsertParticipationParserTest {
    private static final String MISSING_WEEK = " 1 pp/120";
    private static final String MISSING_PP = " 1 w/1";

    @Test
    public void parse() {
        InsertParticipationParser parser = new InsertParticipationParser();
        CommandParserTestUtil.assertParseFailure(parser, MISSING_WEEK, Attendance.WEEK_ERROR_MSG);
        CommandParserTestUtil.assertParseFailure(parser, MISSING_PP, Attendance.POINTS_ERROR_MSG);
    }
}
