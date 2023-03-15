package trackr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.AddSupplierCommand;
import trackr.logic.commands.AddTaskCommand;
import trackr.logic.commands.ClearSupplierCommand;
import trackr.logic.commands.ClearTaskCommand;
import trackr.logic.commands.DeleteSupplierCommand;
import trackr.logic.commands.DeleteTaskCommand;
import trackr.logic.commands.EditSupplierCommand;
import trackr.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import trackr.logic.commands.EditTaskCommand;
import trackr.logic.commands.ExitCommand;
import trackr.logic.commands.FindSupplierCommand;
import trackr.logic.commands.FindTaskCommand;
import trackr.logic.commands.HelpCommand;
import trackr.logic.commands.ListCommand;
import trackr.logic.commands.ListTaskCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.supplier.NameContainsKeywordsPredicate;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDescriptor;
import trackr.testutil.EditSupplierDescriptorBuilder;
import trackr.testutil.SupplierBuilder;
import trackr.testutil.SupplierUtil;
import trackr.testutil.TaskBuilder;
import trackr.testutil.TaskDescriptorBuilder;
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
        assertTrue(parser.parseCommand(ClearSupplierCommand.COMMAND_WORD) instanceof ClearSupplierCommand);
        assertTrue(parser.parseCommand(ClearSupplierCommand.COMMAND_WORD + " 3") instanceof ClearSupplierCommand);
    }

    @Test
    public void parseCommand_clearTask() throws Exception {
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD) instanceof ClearTaskCommand);
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD + " 3") instanceof ClearTaskCommand);
    }

    @Test
    public void parseCommand_clearTaskShortcut() throws Exception {
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD) instanceof ClearTaskCommand);
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD + " 3") instanceof ClearTaskCommand);
    }

    @Test
    public void parseCommand_deleteSupplier() throws Exception {
        DeleteSupplierCommand command = (DeleteSupplierCommand) parser.parseCommand(
                DeleteSupplierCommand.COMMAND_WORD + " " + INDEX_FIRST_OBJECT.getOneBased());
        assertEquals(new DeleteSupplierCommand(INDEX_FIRST_OBJECT), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_OBJECT.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_OBJECT), command);
    }

    @Test
    public void parseCommand_deleteTaskShortcut() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD_SHORTCUT + " " + INDEX_FIRST_OBJECT.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_OBJECT), command);
    }

    @Test
    public void parseCommand_editSupplier() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        EditSupplierDescriptor descriptor = new EditSupplierDescriptorBuilder(supplier).build();
        EditSupplierCommand command = (EditSupplierCommand) parser.parseCommand(EditSupplierCommand.COMMAND_WORD + " "
                + INDEX_FIRST_OBJECT.getOneBased() + " " + SupplierUtil.getEditSupplierDescriptorDetails(descriptor));
        assertEquals(new EditSupplierCommand(INDEX_FIRST_OBJECT, descriptor), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        Task task = new TaskBuilder().build();
        TaskDescriptor descriptor = new TaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(
                EditTaskCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_OBJECT.getOneBased()
                        + " " + TaskUtil.getTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST_OBJECT, descriptor), command);
    }

    @Test
    public void parseCommand_editTaskShortcut() throws Exception {
        Task task = new TaskBuilder().build();
        TaskDescriptor descriptor = new TaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(
                EditTaskCommand.COMMAND_WORD_SHORTCUT + " "
                        + INDEX_FIRST_OBJECT.getOneBased()
                        + " " + TaskUtil.getTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST_OBJECT, descriptor), command);
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
    public void parseCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setTaskNameKeywords(keywords);
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " " + TaskUtil.getTaskPredicateDetails(predicate));
        assertEquals(new FindTaskCommand(predicate), command);
    }

    @Test
    public void parseCommand_findTaskShortcut() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setTaskNameKeywords(keywords);
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD_SHORTCUT
                        + " "
                        + TaskUtil.getTaskPredicateDetails(predicate));
        assertEquals(new FindTaskCommand(predicate), command);
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
    public void parseCommand_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 3") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_listTaskShortCut() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD_SHORTCUT) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD_SHORTCUT + " 3") instanceof ListTaskCommand);
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
