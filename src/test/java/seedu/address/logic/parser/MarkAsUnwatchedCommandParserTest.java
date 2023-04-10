package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.mark.MarkAsUnwatchedCommand;
import seedu.address.logic.commands.mark.MarkMultipleAsUnwatchedCommand;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Contains tests for the parser of "unmark" command
 */
public class MarkAsUnwatchedCommandParserTest {

    private MarkAsUnwatchedCommandParser parser = new MarkAsUnwatchedCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAsUnwatchedCommand() {
        assertParseSuccess(parser, "Vid 2 /mod CS2040S /lec Week 1",
                new MarkAsUnwatchedCommand(
                        TypicalVideos.ANALYSIS_VIDEO.getName(),
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName()));
    }

    @Test
    public void parse_validArgs_returnsMarkMultipleAsUnwatchedCommand() {
        VideoName[] videoNames = {TypicalVideos.CONTENT_VIDEO.getName(), TypicalVideos.INTRO_VIDEO.getName()};
        assertParseSuccess(parser, "Vid 1, Vid 3 /mod ST2334 /lec Topic 1",
                new MarkMultipleAsUnwatchedCommand(
                        videoNames,
                        TypicalModules.getSt2334().getCode(),
                        TypicalLectures.getSt2334Topic1().getName()));
    }
}
