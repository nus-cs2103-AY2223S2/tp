package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISOEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddIsolatedEventCommand;

public class IsolatedEventCommandParserTest {
    private IsolatedEventCommandParser parser = new IsolatedEventCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIsolatedEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, PREFIX_ISOEVENT + "biking" + PREFIX_STARTDATE + "09/03/2023 14:00"
                + PREFIX_ENDDATE + "09/03/2023 15:00", expectedMessage);

        assertParseFailure(parser, "1" + PREFIX_ISOEVENT + PREFIX_STARTDATE + "09/03/2023 14:00"
                + PREFIX_ENDDATE + "09/03/2023 15:00", expectedMessage);

        assertParseFailure(parser, "1" + PREFIX_ISOEVENT + "biking" + PREFIX_STARTDATE + "09-03-2023 14:00"
                + PREFIX_ENDDATE + "09/03/2023 15:00", expectedMessage);
    }
}
