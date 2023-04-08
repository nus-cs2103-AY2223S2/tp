package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static ezschedule.testutil.Assert.assertThrows;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.logic.commands.AddCommand;
import ezschedule.logic.commands.ClearCommand;
import ezschedule.logic.commands.DeleteCommand;
import ezschedule.logic.commands.EditCommand;
import ezschedule.logic.commands.EditCommand.EditEventDescriptor;
import ezschedule.logic.commands.ExitCommand;
import ezschedule.logic.commands.FindCommand;
import ezschedule.logic.commands.FindCommand.FindEventDescriptor;
import ezschedule.logic.commands.HelpCommand;
import ezschedule.logic.commands.ListCommand;
import ezschedule.logic.commands.ShowNextCommand;
import ezschedule.logic.parser.exceptions.ParseException;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.testutil.EditEventDescriptorBuilder;
import ezschedule.testutil.EventBuilder;
import ezschedule.testutil.EventUtil;

public class SchedulerParserTest {

    private final SchedulerParser parser = new SchedulerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Event event = new EventBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EventUtil.getAddCommand(event));
        assertEquals(new AddCommand(event), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseClearCommand_withArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand(ClearCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());
        List<Index> indexFirstEvent = new ArrayList<>();
        indexFirstEvent.add(INDEX_FIRST_EVENT);
        assertEquals(new DeleteCommand(indexFirstEvent), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Event event = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(event).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EVENT.getOneBased() + " " + EventUtil.getEditEventDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_EVENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseExitCommand_withArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_find() throws Exception {
        FindEventDescriptor descriptor = new FindEventDescriptor();
        descriptor.setName(new Name("foo bar baz"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + EventUtil.getFindEventDescriptorDetails(descriptor));
        assertEquals(new FindCommand(descriptor), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseHelpCommand_withArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseListCommand_withArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_showNext() throws Exception {
        assertTrue(parser.parseCommand(ShowNextCommand.COMMAND_WORD) instanceof ShowNextCommand);
        assertTrue(parser.parseCommand(ShowNextCommand.COMMAND_WORD + " 3") instanceof ShowNextCommand);
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
