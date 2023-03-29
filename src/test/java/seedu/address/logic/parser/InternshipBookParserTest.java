package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_DESCRIPTION_INTERVIEW;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApplicationCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearApplicationCommand;
import seedu.address.logic.commands.DeleteApplicationCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditApplicationCommand;
import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.commands.ExitSprintCommand;
import seedu.address.logic.commands.FindApplicationCommand;
import seedu.address.logic.commands.HelpApplicationCommand;
import seedu.address.logic.commands.ListApplicationCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.Application;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.ApplicationUtil;
import seedu.address.testutil.EditApplicationDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskUtil;

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
