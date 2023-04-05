package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCORE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteScoreCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportProgressCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkTaskCompleteCommand;
import seedu.address.logic.commands.MarkTaskInProgressCommand;
import seedu.address.logic.commands.MarkTaskLateCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.score.Score;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.ScoreBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;
import seedu.address.testutil.TaskBuilder;

public class MathutoringParserTest {

    private final MathutoringParser parser = new MathutoringParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_addScore() throws Exception {
        Score score = new ScoreBuilder().build();
        AddScoreCommand command = (AddScoreCommand) parser.parseCommand(
                AddScoreCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased()
                        + " " + StudentUtil.getAddScoreDetails(score));
        assertEquals(new AddScoreCommand(INDEX_FIRST_STUDENT, score), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(
                AddTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased()
                        + " " + StudentUtil.getAddTaskDetails(task));
        assertEquals(new AddTaskCommand(INDEX_FIRST_STUDENT, task), command);
    }

    @Test
    public void parseCommand_check() throws Exception {
        CheckCommand command = (CheckCommand) parser.parseCommand(
                CheckCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new CheckCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_deleteScore() throws Exception {
        DeleteScoreCommand command = (DeleteScoreCommand) parser.parseCommand(
                DeleteScoreCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + INDEX_FIRST_SCORE.getOneBased());
        assertEquals(new DeleteScoreCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_SCORE), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
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
    public void parseCommand_filter() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterCommand(new TagContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_exportProgress() throws Exception {
        String filePath = System.getProperty("user.home");
        assertTrue(parser.parseCommand(ExportProgressCommand.COMMAND_WORD + " 1")
                instanceof ExportProgressCommand);
        assertTrue(parser.parseCommand(ExportProgressCommand.COMMAND_WORD + " 1 " + PREFIX_FILEPATH + filePath)
                instanceof ExportProgressCommand);
    }
    @Test
    public void parseCommand_markTaskComplete() throws Exception {
        MarkTaskCompleteCommand command = (MarkTaskCompleteCommand) parser.parseCommand(
                MarkTaskCompleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkTaskCompleteCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_markTaskInProgress() throws Exception {
        MarkTaskInProgressCommand command = (MarkTaskInProgressCommand) parser.parseCommand(
                MarkTaskInProgressCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkTaskInProgressCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_markTaskLate() throws Exception {
        MarkTaskLateCommand command = (MarkTaskLateCommand) parser.parseCommand(
                MarkTaskLateCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkTaskLateCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_switch() throws Exception {
        assertTrue(parser.parseCommand(SwitchCommand.COMMAND_WORD) instanceof SwitchCommand);
        assertTrue(parser.parseCommand(SwitchCommand.COMMAND_WORD + " 3") instanceof SwitchCommand);
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
