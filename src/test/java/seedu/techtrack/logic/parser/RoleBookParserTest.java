package seedu.techtrack.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.techtrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.techtrack.testutil.Assert.assertThrows;
import static seedu.techtrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.techtrack.logic.commands.AddCommand;
import seedu.techtrack.logic.commands.ClearCommand;
import seedu.techtrack.logic.commands.DeadlineCommand;
import seedu.techtrack.logic.commands.DeleteCommand;
import seedu.techtrack.logic.commands.EditCommand;
import seedu.techtrack.logic.commands.EditCommand.EditRoleDescriptor;
import seedu.techtrack.logic.commands.ExitCommand;
import seedu.techtrack.logic.commands.HelpCommand;
import seedu.techtrack.logic.commands.ListCommand;
import seedu.techtrack.logic.commands.NameCommand;
import seedu.techtrack.logic.commands.SalaryCommand;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.model.role.NameContainsKeywordsPredicate;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.testutil.EditRoleDescriptorBuilder;
import seedu.techtrack.testutil.RoleBuilder;
import seedu.techtrack.testutil.RoleUtil;

public class RoleBookParserTest {

    private final RoleBookParser parser = new RoleBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Role role = new RoleBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(RoleUtil.getAddCommand(role));
        assertEquals(new AddCommand(role), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Role role = new RoleBuilder().build();
        EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder(role).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + RoleUtil.getEditRoleDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        NameCommand command = (NameCommand) parser.parseCommand(
                NameCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new NameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_salary() throws Exception {
        assertTrue(parser.parseCommand(SalaryCommand.COMMAND_WORD + " asc") instanceof SalaryCommand);
        assertTrue(parser.parseCommand(SalaryCommand.COMMAND_WORD + " desc") instanceof SalaryCommand);
    }

    @Test
    public void parseCommand_deadline() throws Exception {
        assertTrue(parser.parseCommand(DeadlineCommand.COMMAND_WORD + " asc") instanceof DeadlineCommand);
        assertTrue(parser.parseCommand(DeadlineCommand.COMMAND_WORD + " desc") instanceof DeadlineCommand);
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
