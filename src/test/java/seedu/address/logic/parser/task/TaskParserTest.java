package seedu.address.logic.parser.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.task.note.AddNoteCommand;
import seedu.address.logic.commands.task.note.ClearNoteCommand;
import seedu.address.logic.commands.task.note.DeleteNoteCommand;
import seedu.address.logic.commands.task.note.ListNoteCommand;
import seedu.address.logic.commands.task.todo.AddTodoCommand;
import seedu.address.logic.commands.task.todo.ClearTodoCommand;
import seedu.address.logic.commands.task.todo.DeleteTodoCommand;
import seedu.address.logic.commands.task.todo.EditDeadlineCommand;
import seedu.address.logic.commands.task.todo.EditNoteContentCommand;
import seedu.address.logic.commands.task.todo.ListTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.ApplicationDeadline;
import seedu.address.model.task.ContentContainsKeywordsPredicate;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;
import seedu.address.model.task.NoteContent;
import seedu.address.model.task.TitleContainsKeywordsPredicate;
import seedu.address.testutil.InternshipTodoBuilder;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.NoteUtil;
import seedu.address.testutil.TodoUtil;

/**
 * A test class designed to examine the sub-parser {@code TaskParser}.
 */
public class TaskParserTest {

    private final TaskParser parser = new TaskParser();

    @Test
    public void parseTaskCommand_addTodo() throws Exception {
        InternshipTodo todo = new InternshipTodoBuilder().basicBuild();
        AddTodoCommand command = (AddTodoCommand) parser.parseTaskCommand(AddTodoCommand.COMMAND_WORD,
                " " + TodoUtil.getTodoDetails(todo));
        assertEquals(new AddTodoCommand(todo), command);
    }

    @Test
    public void parseTaskCommand_addNote() throws Exception {
        Note note = new NoteBuilder().basicBuild();
        AddNoteCommand command = (AddNoteCommand) parser.parseTaskCommand(AddNoteCommand.COMMAND_WORD,
                " " + NoteUtil.getNoteDetails(note));
        assertEquals(new AddNoteCommand(note), command);
    }

    @Test
    public void parseTaskCommand_clearTodo() throws Exception {
        assertTrue(parser.parseTaskCommand(ClearTodoCommand.COMMAND_WORD, "") instanceof ClearTodoCommand);
        assertTrue(parser.parseTaskCommand(ClearTodoCommand.COMMAND_WORD, " 3") instanceof ClearTodoCommand);
    }

    @Test
    public void parseTaskCommand_clearNote() throws Exception {
        assertTrue(parser.parseTaskCommand(ClearNoteCommand.COMMAND_WORD, "") instanceof ClearNoteCommand);
        assertTrue(parser.parseTaskCommand(ClearNoteCommand.COMMAND_WORD, " 3") instanceof ClearNoteCommand);
    }

    @Test
    public void parseTaskCommand_deleteTodo() throws Exception {
        DeleteTodoCommand command = (DeleteTodoCommand) parser.parseTaskCommand(
                DeleteTodoCommand.COMMAND_WORD, " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteTodoCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseTaskCommand_deleteNote() throws Exception {
        DeleteNoteCommand command = (DeleteNoteCommand) parser.parseTaskCommand(
                DeleteNoteCommand.COMMAND_WORD, " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteNoteCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseTaskCommand_editDeadline() throws Exception {
        InternshipTodo todo = new InternshipTodoBuilder().basicBuild();
        ApplicationDeadline deadline = new ApplicationDeadline(LocalDate.parse("2023-07-09"));
        todo.setDeadline(deadline);
        EditDeadlineCommand command = (EditDeadlineCommand) parser.parseTaskCommand(EditDeadlineCommand.COMMAND_WORD,
                " " + INDEX_FIRST_APPLICATION.getOneBased() + " "
                        + PREFIX_DEADLINE + " 2023-07-09");
        assertEquals(new EditDeadlineCommand(INDEX_FIRST_APPLICATION, deadline), command);
    }

    @Test
    public void parseTaskCommand_editNoteContent() throws Exception {
        InternshipTodo todo = new InternshipTodoBuilder().basicBuild();
        NoteContent note = new NoteContent("Test case");
        todo.setNote(note);
        EditNoteContentCommand command = (EditNoteContentCommand) parser.parseTaskCommand(
                EditNoteContentCommand.COMMAND_WORD, " " + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + PREFIX_NOTE_CONTENT + " Test case");
        assertEquals(new EditNoteContentCommand(INDEX_FIRST_APPLICATION, note), command);
    }

    @Test
    public void parseTaskCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseTaskCommand(
                FindTaskCommand.COMMAND_WORD , " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTaskCommand(new TitleContainsKeywordsPredicate(keywords),
                new ContentContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseTaskCommand_listTask() throws Exception {
        assertTrue(parser.parseTaskCommand(ListTaskCommand.COMMAND_WORD, "") instanceof ListTaskCommand);
        assertTrue(parser.parseTaskCommand(ListTaskCommand.COMMAND_WORD, "2") instanceof ListTaskCommand);
    }

    @Test
    public void parseTaskCommand_listTodo() throws Exception {
        assertTrue(parser.parseTaskCommand(ListTodoCommand.COMMAND_WORD, "") instanceof ListTodoCommand);
        assertTrue(parser.parseTaskCommand(ListTodoCommand.COMMAND_WORD, "3") instanceof ListTodoCommand);
    }

    @Test
    public void parseTaskCommand_listNote() throws Exception {
        assertTrue(parser.parseTaskCommand(ListNoteCommand.COMMAND_WORD, "") instanceof ListNoteCommand);
        assertTrue(parser.parseTaskCommand(ListNoteCommand.COMMAND_WORD , " 3") instanceof ListNoteCommand);
    }

    @Test
    public void parseTaskCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseTaskCommand("unknownCommand", ""));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseTaskCommand("unknownCommand", " can have params"));
    }
}

