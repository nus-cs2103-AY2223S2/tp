package seedu.address.logic.parser.group;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.group.GroupListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class GroupCommandParserTest {
    private final GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parseCommand_groupList() throws Exception {
        GroupListCommand command = (GroupListCommand) parser.parse(GroupListCommand.SUB_COMMAND_WORD);
        assertEquals(new GroupListCommand(), command);
    }

    @Test
    public void parseCommand_groupCreate() throws Exception {

    }

    @Test
    public void parseCommand_groupAddPerson() throws Exception {

    }

    @Test
    public void parseCommand_groupRemovePerson() throws Exception {

    }

    @Test
    public void parseCommand_groupFind() throws Exception {

    }

    @Test
    public void parseCommand_groupDelete() throws Exception {

    }

    @Test
    public void parseCommand_unknownSubCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parse("unknownCommand"));
    }
}