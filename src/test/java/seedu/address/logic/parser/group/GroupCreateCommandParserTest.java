package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS2103;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.model.group.Group;

class GroupCreateCommandParserTest {
    private GroupCreateCommandParser parser = new GroupCreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group expectedGroup = new Group(VALID_GROUP_CS2103);
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUP_DESC_CS2103,
                new GroupCreateCommand(expectedGroup));
    }

    @Test
    public void parse_missingField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCreateCommand.MESSAGE_USAGE);
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);
    }

}
