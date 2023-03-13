package seedu.address.logic.parser.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.commands.group.GroupDeleteCommand;
import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.logic.commands.group.GroupListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.MemberOfGroupPredicate;

class GroupCommandParserTest {
    private final GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parseCommand_groupList() throws Exception {
        GroupListCommand command = (GroupListCommand) parser.parse(GroupListCommand.SUB_COMMAND_WORD);
        assertEquals(new GroupListCommand(), command);
    }

    @Test
    public void parseCommand_groupCreate() throws Exception {
        GroupCreateCommand command = (GroupCreateCommand) parser.parse(GroupCreateCommand.SUB_COMMAND_WORD
                + " g/2103");
        assertEquals(new GroupCreateCommand(new Group("2103")), command);
    }

    @Test
    public void parseCommand_groupFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        GroupFindCommand command = (GroupFindCommand) parser.parse(GroupFindCommand.SUB_COMMAND_WORD
                + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new GroupFindCommand(new GroupNameContainsKeywordsPredicate(keywords),
                new MemberOfGroupPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_groupDelete() throws Exception {
        GroupDeleteCommand command = (GroupDeleteCommand) parser.parse(
                GroupDeleteCommand.SUB_COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new GroupDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_unknownSubCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parse("unknownCommand"));
    }
}
