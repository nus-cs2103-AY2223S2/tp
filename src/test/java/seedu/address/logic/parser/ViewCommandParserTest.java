package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.ContactIndex;


class ViewCommandParserTest {
    private static final String FAILURE_MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.USAGE);
    private ViewCommandParser viewCommandParser = new ViewCommandParser();

    @Test
    public void parse_emptyFields_success() {
        assertParseSuccess(viewCommandParser, "", new ViewCommand(null, null));
    }

    @Test
    public void parse_nameFieldOnly_success() {
        assertParseSuccess(viewCommandParser, CommandTestUtil.NAME_DESC_ALEX,
                new ViewCommand("Alex Quentin", null));

        assertParseSuccess(viewCommandParser, CommandTestUtil.NAME_DESC_BEN,
                new ViewCommand("Benjamin DeMeer", null));
    }

    @Test
    public void parse_invalidFieldOnly_failure() {

        assertParseFailure(viewCommandParser, CommandTestUtil.PHONE_DESC_ALEX,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.PHONE_DESC_BEN,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.TELEGRAM_DESC_ALEX,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.TELEGRAM_DESC_BEN,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.VALID_GROUP_1_DESC,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.VALID_GROUP_2_DESC,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.VALID_MODULE_1_DESC,
                FAILURE_MESSAGE);

        assertParseFailure(viewCommandParser, CommandTestUtil.VALID_MODULE_2_DESC,
                FAILURE_MESSAGE);
    }

    @Test
    public void parse_indexOnly_success() {
        for (int i = 1; i < 100; i++) {
            ContactIndex index = new ContactIndex(i);
            assertParseSuccess(viewCommandParser,
                    String.valueOf(i),
                    new ViewCommand(null, index));
        }
    }

}
