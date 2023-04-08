package seedu.dengue.logic.parser;

import static seedu.dengue.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dengue.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.dengue.logic.commands.OverviewCommand;
import seedu.dengue.model.overview.AgeOverview;
import seedu.dengue.model.overview.PostalOverview;
import seedu.dengue.model.overview.VariantOverview;

public class OverviewCommandParserTest {
    private final OverviewCommandParser parser = new OverviewCommandParser();

    @Test
    public void parse_validPostalArgs_returnsOverviewCommand() {
        assertParseSuccess(parser, "p/", new OverviewCommand(new PostalOverview(), "POSTAL"));
    }

    @Test
    public void parse_validAgeArgs_returnsOverviewCommand() {
        assertParseSuccess(parser, "a/", new OverviewCommand(new AgeOverview(), "AGE"));
    }

    @Test
    public void parse_validVariantArgs_returnsOverviewCommand() {
        assertParseSuccess(parser, "v/", new OverviewCommand(new VariantOverview(), "VARIANT"));
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        assertParseFailure(parser, "n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OverviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validPostalArgsNeedsTrimming_returnsOverviewCommand() {
        assertParseSuccess(parser, "    p/    ", new OverviewCommand(new PostalOverview(), "POSTAL"));
    }

    @Test
    public void parse_invalidPostalArgsWithInput_throwsParseException() {
        assertParseFailure(parser, "p/S123456",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, OverviewCommand.MESSAGE_USAGE));
    }
}
