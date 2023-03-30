package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.mark.MarkAsWatchedCommand;
import seedu.address.testutil.TypicalLectures;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalVideos;

/**
 * Contains test for the parser of "mark" command
 */
public class MarkAsWatchedCommandParserTest {

    private MarkAsWatchedCommandParser parser = new MarkAsWatchedCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAsWatchedCommand() {
        assertParseSuccess(parser, "Vid 1 "
                        + CliSyntax.PREFIX_MODULE + " CS2040S "
                        + CliSyntax.PREFIX_LECTURE + " Week 1",
                new MarkAsWatchedCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalLectures.getCs2040sWeek1().getName(),
                        TypicalVideos.CONTENT_VIDEO.getName()));
    }
}
