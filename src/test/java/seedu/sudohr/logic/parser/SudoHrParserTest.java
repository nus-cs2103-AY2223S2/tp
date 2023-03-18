package seedu.sudohr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sudohr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.sudohr.logic.commands.CommandTestUtil.EID_DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.AddCommand;
import seedu.sudohr.logic.commands.ClearCommand;
import seedu.sudohr.logic.commands.DeleteCommand;
import seedu.sudohr.logic.commands.EditCommand;
import seedu.sudohr.logic.commands.ExitCommand;
import seedu.sudohr.logic.commands.FindCommand;
import seedu.sudohr.logic.commands.HelpCommand;
import seedu.sudohr.logic.commands.ListCommand;
import seedu.sudohr.logic.parser.exceptions.ParseException;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.model.employee.NameContainsKeywordsPredicate;
import seedu.sudohr.testutil.EditEmployeeDescriptorBuilder;
import seedu.sudohr.testutil.EmployeeBuilder;
import seedu.sudohr.testutil.EmployeeUtil;

public class SudoHrParserTest {

    private final SudoHrParser parser = new SudoHrParser();

    @Test
    public void parseCommand_add() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EmployeeUtil.getAddCommand(employee));
        assertEquals(new AddCommand(employee), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + EID_DESC_BOB);
        assertEquals(new DeleteCommand(new Id(VALID_ID_BOB)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Employee employee = new EmployeeBuilder().build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(employee).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + EID_DESC_BOB + " " + EmployeeUtil.getEditEmployeeDescriptorDetails(descriptor));
        assertEquals(new EditCommand(new Id(VALID_ID_BOB), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
