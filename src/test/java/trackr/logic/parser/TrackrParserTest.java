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

import trackr.logic.commands.ExitCommand;
import trackr.logic.commands.HelpCommand;
import trackr.logic.commands.order.AddOrderCommand;
import trackr.logic.commands.order.ClearOrderCommand;
import trackr.logic.commands.order.DeleteOrderCommand;
import trackr.logic.commands.order.ListOrderCommand;
import trackr.logic.commands.supplier.AddSupplierCommand;
import trackr.logic.commands.supplier.ClearSupplierCommand;
import trackr.logic.commands.supplier.DeleteSupplierCommand;
import trackr.logic.commands.supplier.EditSupplierCommand;
import trackr.logic.commands.supplier.FindSupplierCommand;
import trackr.logic.commands.supplier.ListSupplierCommand;
import trackr.logic.commands.task.AddTaskCommand;
import trackr.logic.commands.task.ClearTaskCommand;
import trackr.logic.commands.task.DeleteTaskCommand;
import trackr.logic.commands.task.EditTaskCommand;
import trackr.logic.commands.task.FindTaskCommand;
import trackr.logic.commands.task.ListTaskCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.order.Order;
import trackr.model.person.PersonDescriptor;
import trackr.model.person.PersonNameContainsKeywordsPredicate;
import trackr.model.person.Supplier;
import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDescriptor;
import trackr.testutil.OrderBuilder;
import trackr.testutil.OrderUtil;
import trackr.testutil.PersonDescriptorBuilder;
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

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }
    //@@author

    @Test
    public void parseCommand_addOrder() throws Exception {
        Order order = new OrderBuilder().build();
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(
                OrderUtil.getAddOrderCommand(order));
        assertEquals(new AddOrderCommand(order), command);
    }

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void parseCommand_addTaskShortcut() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                TaskUtil.getAddTaskCommandShortcut(task));
        assertEquals(new AddTaskCommand(task), command);
    }
    //@@author

    @Test
    public void parseCommand_addOrderShortcut() throws Exception {
        Order order = new OrderBuilder().build();
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(
                OrderUtil.getAddOrderCommandShortcut(order));
        assertEquals(new AddOrderCommand(order), command);
    }

    //@@author liumc-sg-reused
    @Test
    public void parseCommand_clearSupplier() throws Exception {
        assertTrue(parser.parseCommand(ClearSupplierCommand.COMMAND_WORD) instanceof ClearSupplierCommand);
        assertTrue(parser.parseCommand(ClearSupplierCommand.COMMAND_WORD + " 3") instanceof ClearSupplierCommand);
    }

    @Test
    public void parseCommand_clearSupplierShortcut() throws Exception {
        assertTrue(parser.parseCommand(ClearSupplierCommand.COMMAND_WORD_SHORTCUT) instanceof ClearSupplierCommand);
        assertTrue(parser.parseCommand(
                ClearSupplierCommand.COMMAND_WORD_SHORTCUT + " 3") instanceof ClearSupplierCommand);
    }

    @Test
    public void parseCommand_clearOrder() throws Exception {
        assertTrue(parser.parseCommand(ClearOrderCommand.COMMAND_WORD) instanceof ClearOrderCommand);
        assertTrue(parser.parseCommand(ClearOrderCommand.COMMAND_WORD + " 3") instanceof ClearOrderCommand);
    }

    @Test
    public void parseCommand_clearOrderShortcut() throws Exception {
        assertTrue(parser.parseCommand(ClearOrderCommand.COMMAND_WORD_SHORTCUT) instanceof ClearOrderCommand);
        assertTrue(parser.parseCommand(
                ClearOrderCommand.COMMAND_WORD_SHORTCUT + " 3") instanceof ClearOrderCommand);
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
    //@author

    //@@author chongweiguan-reused
    @Test
    public void parseCommand_deleteOrder() throws Exception {
        DeleteOrderCommand command = (DeleteOrderCommand) parser.parseCommand(
                DeleteOrderCommand.COMMAND_WORD + " " + INDEX_FIRST_OBJECT.getOneBased());
        assertEquals(new DeleteOrderCommand(INDEX_FIRST_OBJECT), command);
    }

    @Test
    public void parseCommand_deleteOrderShortcut() throws Exception {
        DeleteOrderCommand command = (DeleteOrderCommand) parser.parseCommand(
                DeleteOrderCommand.COMMAND_WORD_SHORTCUT + " " + INDEX_FIRST_OBJECT.getOneBased());
        assertEquals(new DeleteOrderCommand(INDEX_FIRST_OBJECT), command);
    }
    //@@author

    //@@author liumc-sg-reused
    @Test
    public void parseCommand_editSupplier() throws Exception {
        Supplier supplier = new SupplierBuilder().build();
        PersonDescriptor descriptor = new PersonDescriptorBuilder(supplier).build();
        EditSupplierCommand command = (EditSupplierCommand) parser.parseCommand(EditSupplierCommand.COMMAND_WORD + " "
                + INDEX_FIRST_OBJECT.getOneBased() + " " + SupplierUtil.getEditSupplierDescriptorDetails(descriptor));
        assertEquals(new EditSupplierCommand(INDEX_FIRST_OBJECT, descriptor), command);
    }
    //@@author

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
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
    //@@author

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    //@@author liumc-sg-reused
    @Test
    public void parseCommand_findSupplier() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindSupplierCommand command = (FindSupplierCommand) parser.parseCommand(
                FindSupplierCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindSupplierCommand(new PersonNameContainsKeywordsPredicate(keywords)), command);
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
    //@@author

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    //@@author liumc-sg-reused
    @Test
    public void parseCommand_listSupplier() throws Exception {
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD) instanceof ListSupplierCommand);
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD + " 3") instanceof ListSupplierCommand);
    }

    @Test
    public void parseCommand_listSupplierShortcut() throws Exception {
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD_SHORTCUT) instanceof ListSupplierCommand);
        assertTrue(parser.parseCommand(
                ListSupplierCommand.COMMAND_WORD_SHORTCUT + " 3") instanceof ListSupplierCommand);
    }

    @Test
    public void parseCommand_listOrder() throws Exception {
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD) instanceof ListOrderCommand);
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD + " 3") instanceof ListOrderCommand);
    }

    @Test
    public void parseCommand_listOrderShortcut() throws Exception {
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD_SHORTCUT) instanceof ListOrderCommand);
        assertTrue(parser.parseCommand(
                ListOrderCommand.COMMAND_WORD_SHORTCUT + " 3") instanceof ListOrderCommand);
    }
    //@@author

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
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
    //@@author

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
