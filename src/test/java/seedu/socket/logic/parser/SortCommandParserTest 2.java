package seedu.socket.logic.parser;

import static seedu.socket.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.socket.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.SortCommand;

class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_returnsSortCommand() {
        SortCommand expectedSortCommand = new SortCommand("name");
        assertParseSuccess(parser, "", expectedSortCommand);
    }

    @Test
    public void parse_name_returnsSortNameCommand() {
        //no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand("name");
        assertParseSuccess(parser, "name", expectedSortCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  name  ", expectedSortCommand);
    }

    @Test
    public void parse_phone_returnsSortPhoneCommand() {
        //no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand("phone");
        assertParseSuccess(parser, "phone", expectedSortCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  phone  ", expectedSortCommand);
    }

    @Test
    public void parse_email_returnsSortEmailCommand() {
        //no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand("email");
        assertParseSuccess(parser, "email", expectedSortCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  email  ", expectedSortCommand);
    }

    @Test
    public void parse_address_returnsSortAddressCommand() {
        //no leading and trailing whitespaces
        SortCommand expectedSortCommand = new SortCommand("address");
        assertParseSuccess(parser, "address", expectedSortCommand);

        //multiple whitespaces
        assertParseSuccess(parser, "  address  ", expectedSortCommand);
    }

    @Test
    public void parse_invalidCategory_throwsParseException() {
        assertParseFailure(parser, "sort invalid",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
