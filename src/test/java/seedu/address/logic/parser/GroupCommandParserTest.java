package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.model.tag.Tag;

public class GroupCommandParserTest {
    private GroupCommandParser parser = new GroupCommandParser();
    private String groupHall = "Hall";
    private String groupVar = "Varsity";
    private Tag tagHall = new Tag(groupHall);

    @Test
    public void parseNameFieldPresent_success() {
        //whitespace only preamble
        assertParseSuccess(parser, " n/hall", new GroupCommand(tagHall));

        //multiple names - last name accepted
        assertParseSuccess(parser, " n/Varsity n/hall", new GroupCommand(tagHall));
    }

    @Test
    public void parse_nameMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE);
        // non name
        assertParseFailure(parser, " ", expectedMessage);
    }
}
