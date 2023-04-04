package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearByCommand;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.JobTitle;

/**
 * Tests {@code ClearByCommandParser} on its behaviours on taking normal and edge case parameters.
 */
public class ClearByCommandParserTest {

    private ClearByCommandParser parser = new ClearByCommandParser();

    @Test
    public void parse_companyName_returnsClearByCommand() {
        assertParseSuccess(parser, " n/com", new ClearByCommand(new CompanyName("com")));
    }

    @Test
    public void parse_jobTitle_returnsClearByCommand() {
        assertParseSuccess(parser, " j/Software Engineer",
                new ClearByCommand(new JobTitle("Software Engineer")));
    }

    @Test
    public void parse_status_returnsClearByCommand() {
        assertParseSuccess(parser, " s/PENDING", new ClearByCommand(InternshipStatus.PENDING));
    }

    @Test
    public void parse_outOfBound_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearByCommand.MESSAGE_NO_PARAMETER));
    }

    @Test
    public void parse_invalidParam_throwsParseException() {
        assertParseFailure(parser, " d/2023-06-05",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearByCommand.MESSAGE_INVALID_PARAMETER));
    }
}
