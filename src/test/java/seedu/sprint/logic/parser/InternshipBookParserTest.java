package seedu.sprint.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.sprint.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION_INTERVIEW;
import static seedu.sprint.testutil.Assert.assertThrows;
import static seedu.sprint.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.commands.AddApplicationCommand;
import seedu.sprint.logic.commands.AddTaskCommand;
import seedu.sprint.logic.commands.ClearApplicationCommand;
import seedu.sprint.logic.commands.DeleteApplicationCommand;
import seedu.sprint.logic.commands.DeleteTaskCommand;
import seedu.sprint.logic.commands.EditApplicationCommand;
import seedu.sprint.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.sprint.logic.commands.EditTaskCommand;
import seedu.sprint.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.sprint.logic.commands.ExitSprintCommand;
import seedu.sprint.logic.commands.FindApplicationCommand;
import seedu.sprint.logic.commands.HelpApplicationCommand;
import seedu.sprint.logic.commands.ListApplicationCommand;
import seedu.sprint.logic.commands.RedoCommand;
import seedu.sprint.logic.commands.UndoCommand;
import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.application.Application;
import seedu.sprint.model.application.NameContainsKeywordsPredicate;
import seedu.sprint.testutil.ApplicationBuilder;
import seedu.sprint.testutil.ApplicationUtil;
import seedu.sprint.testutil.EditApplicationDescriptorBuilder;
import seedu.sprint.testutil.EditTaskDescriptorBuilder;
import seedu.sprint.testutil.TaskUtil;

public class InternshipBookParserTest {

    private final InternshipBookParser parser = new InternshipBookParser();

    @Test
    public void parseCommand_addApp() throws Exception {
        Application application = new ApplicationBuilder().build();
        AddApplicationCommand command = (AddApplicationCommand) parser
                .parseCommand(ApplicationUtil.getAddApplicationCommand(application));
        assertEquals(new AddApplicationCommand(application), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withTask(VALID_DEADLINE, VALID_DESCRIPTION)
                .build();
        AddTaskCommand command = (AddTaskCommand) parser
                .parseCommand(AddTaskCommand.COMMAND_WORD
                + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + ApplicationUtil.getEditApplicationDescriptorDetails(descriptor));
        assertEquals(new AddTaskCommand(INDEX_FIRST_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearApplicationCommand.COMMAND_WORD) instanceof ClearApplicationCommand);
        assertTrue(parser.parseCommand(ClearApplicationCommand.COMMAND_WORD + " 3")
                instanceof ClearApplicationCommand);
    }

    @Test
    public void parseCommand_deleteApp() throws Exception {
        DeleteApplicationCommand command = (DeleteApplicationCommand) parser.parseCommand(
                DeleteApplicationCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteApplicationCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser
                .parseCommand(DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_editApp() throws Exception {
        Application application = new ApplicationBuilder().build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(application).build();
        EditApplicationCommand command = (EditApplicationCommand) parser
                .parseCommand(EditApplicationCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + ApplicationUtil.getEditApplicationDescriptorDetails(descriptor));
        assertEquals(new EditApplicationCommand(INDEX_FIRST_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_INTERVIEW)
                .build();
        EditTaskCommand command = (EditTaskCommand) parser
                .parseCommand(EditTaskCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICATION.getOneBased() + " "
                + TaskUtil.getEditTaskDescriptorDetails(descriptor)
                );
        assertEquals(new EditTaskCommand(INDEX_FIRST_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitSprintCommand.COMMAND_WORD) instanceof ExitSprintCommand);
        assertTrue(parser.parseCommand(ExitSprintCommand.COMMAND_WORD + " 3") instanceof ExitSprintCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindApplicationCommand command = (FindApplicationCommand) parser.parseCommand(
                FindApplicationCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindApplicationCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpApplicationCommand.COMMAND_WORD) instanceof HelpApplicationCommand);
        assertTrue(parser.parseCommand(HelpApplicationCommand.COMMAND_WORD + " 3") instanceof HelpApplicationCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListApplicationCommand.COMMAND_WORD) instanceof ListApplicationCommand);
        assertTrue(parser.parseCommand(ListApplicationCommand.COMMAND_WORD + " 3") instanceof ListApplicationCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpApplicationCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
