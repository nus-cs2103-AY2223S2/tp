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
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ClearByCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ClearByCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
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
