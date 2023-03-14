package trackr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_SUPPLIER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.AddSupplierCommand;
import trackr.logic.commands.AddTaskCommand;
import trackr.logic.commands.ClearCommand;
import trackr.logic.commands.DeleteSupplierCommand;
import trackr.logic.commands.EditSupplierCommand;
import trackr.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import trackr.logic.commands.ExitCommand;
import trackr.logic.commands.FindSupplierCommand;
import trackr.logic.commands.HelpCommand;
import trackr.logic.commands.ListCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.supplier.NameContainsKeywordsPredicate;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;
import trackr.testutil.EditSupplierDescriptorBuilder;
import trackr.testutil.SupplierBuilder;
import trackr.testutil.SupplierUtil;
import trackr.testutil.TaskBuilder;
import trackr.testutil.TaskUtil;

public class TrackrParserTest {

    private final TrackrParser parser = new TrackrParser();

    @Test
    public void parseCommand_addSupplier() throws Exception {
        Supplier person = new SupplierBuilder().build();
        AddSupplierCommand command = (AddSupplierCommand) parser.parseCommand(SupplierUtil.getAddCommand(person));
        assertEquals(new AddSupplierCommand(person), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_addTaskShortcut() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                TaskUtil.getAddTaskCommandShortcut(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteSupplier() throws Exception {
        DeleteSupplierCommand command = (DeleteSupplierCommand) parser.parseCommand(
                DeleteSupplierCommand.COMMAND_WORD + " " + INDEX_FIRST_SUPPLIER.getOneBased());
        assertEquals(new DeleteSupplierCommand(INDEX_FIRST_SUPPLIER), command);
    }

    @Test
    public void parseCommand_editSupplier() throws Exception {
        Supplier person = new SupplierBuilder().build();
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(person).build();
        EditSupplierCommand command = (EditSupplierCommand) parser.parseCommand(EditSupplierCommand.COMMAND_WORD + " "
                + INDEX_FIRST_SUPPLIER.getOneBased() + " " + SupplierUtil.getEditSupplierDescriptorDetails(descriptor));
        assertEquals(new EditSupplierCommand(INDEX_FIRST_SUPPLIER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findSupplier() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindSupplierCommand command = (FindSupplierCommand) parser.parseCommand(
                FindSupplierCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindSupplierCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
