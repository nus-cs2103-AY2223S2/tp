package seedu.address.logic.parser.group;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.MemberOfGroupPredicate;

class GroupFindCommandParserTest {

    private GroupFindCommandParser parser = new GroupFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> keywordList = Arrays.asList("Friends", "CS2103");
        // no leading and trailing whitespaces
        GroupFindCommand expectedGroupFindCommand =
                new GroupFindCommand(new GroupNameContainsKeywordsPredicate(keywordList),
                        new MemberOfGroupPredicate(keywordList));
        assertParseSuccess(parser, "Friends CS2103", expectedGroupFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Friends \n \t CS2103  \t", expectedGroupFindCommand);
    }

}
