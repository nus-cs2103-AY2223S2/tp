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

import trackr.logic.commands.AddCommand;
import trackr.logic.commands.AddTaskCommand;
import trackr.logic.commands.ClearCommand;
import trackr.logic.commands.DeleteCommand;
import trackr.logic.commands.DeleteTaskCommand;
import trackr.logic.commands.EditCommand;
import trackr.logic.commands.EditCommand.EditPersonDescriptor;
import trackr.logic.commands.ExitCommand;
import trackr.logic.commands.FindCommand;
import trackr.logic.commands.FindTaskCommand;
import trackr.logic.commands.HelpCommand;
import trackr.logic.commands.ListCommand;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.person.NameContainsKeywordsPredicate;
import trackr.model.person.Person;
import trackr.model.task.Task;
import trackr.model.task.TaskNameContainsKeywordsPredicate;
import trackr.testutil.EditPersonDescriptorBuilder;
import trackr.testutil.PersonBuilder;
import trackr.testutil.PersonUtil;
import trackr.testutil.TaskBuilder;
import trackr.testutil.TaskUtil;

public class TrackrParserTest {

    private final TrackrParser parser = new TrackrParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
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
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_OBJECT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_OBJECT), command);
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
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_OBJECT.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_OBJECT, descriptor), command);
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
    public void parseCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTaskCommand(new TaskNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTaskShortcut() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD_SHORTCUT
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTaskCommand(new TaskNameContainsKeywordsPredicate(keywords)), command);
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
