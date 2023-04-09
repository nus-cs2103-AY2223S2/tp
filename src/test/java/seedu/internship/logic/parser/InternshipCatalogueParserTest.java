package seedu.internship.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.CalendarCommand;
import seedu.internship.logic.commands.ClashCommand;
import seedu.internship.logic.commands.DeleteAllCommand;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.EditCommand;
import seedu.internship.logic.commands.ExitCommand;
import seedu.internship.logic.commands.HelpCommand;
import seedu.internship.logic.commands.HomeCommand;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.commands.SelectCommand;
import seedu.internship.logic.commands.StatsCommand;
import seedu.internship.logic.commands.event.EventAddCommand;
import seedu.internship.logic.commands.event.EventCommand;
import seedu.internship.logic.commands.event.EventDeleteCommand;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Position;
import seedu.internship.testutil.EventBuilder;
import seedu.internship.testutil.EventUtil;
import seedu.internship.testutil.InternshipBuilder;
import seedu.internship.testutil.InternshipUtil;

public class InternshipCatalogueParserTest {

    private final InternshipCatalogueParser parser = new InternshipCatalogueParser();

    @Test
    public void parseCommand_add() throws Exception {
        Internship internship = new InternshipBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(InternshipUtil.getAddCommand(internship));
        assertEquals(new AddCommand(internship), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_clash() throws Exception {
        assertTrue(parser.parseCommand(ClashCommand.COMMAND_WORD) instanceof ClashCommand);
        assertTrue(parser.parseCommand(ClashCommand.COMMAND_WORD + " 3") instanceof ClashCommand);
    }

    @Test
    public void parseCommand_stats() throws Exception {
        assertTrue(parser.parseCommand(StatsCommand.COMMAND_WORD) instanceof StatsCommand);
        assertTrue(parser.parseCommand(StatsCommand.COMMAND_WORD + " 3") instanceof StatsCommand);
    }

    @Test
    public void parseCommand_home() throws Exception {
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD) instanceof HomeCommand);
    }

    @Test
    public void parseCommand_calendar() throws Exception {
        assertTrue(parser.parseCommand(CalendarCommand.COMMAND_WORD) instanceof CalendarCommand);
        assertTrue(parser.parseCommand(CalendarCommand.COMMAND_WORD + " 3") instanceof CalendarCommand);
    }

    @Test
    public void parseCommand_deleteAll() throws Exception {
        assertTrue(parser.parseCommand(DeleteAllCommand.COMMAND_WORD + " confirm") instanceof DeleteAllCommand);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectCommand command = (SelectCommand) parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new SelectCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        EditCommand command = (EditCommand) parser.parseCommand(
                EditCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased() + " p/ test");
        EditCommand.EditInternshipDescriptor descriptor = new EditCommand.EditInternshipDescriptor();
        descriptor.setPosition(new Position("test"));
        assertEquals(new EditCommand(INDEX_FIRST_INTERNSHIP, descriptor), command);
    }

    @Test
    public void parseCommand_event_add() throws Exception {
        Event event = new EventBuilder().withInternship(null).build();
        EventAddCommand command = (EventAddCommand) parser.parseCommand(EventUtil.getEventAddCommand(event));
        assertEquals(new EventAddCommand(event), command);
    }

    @Test
    public void parseCommand_event_delete() throws Exception {
        EventDeleteCommand command = (EventDeleteCommand) parser.parseCommand(EventCommand.COMMAND_WORD + " "
                + DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new EventDeleteCommand(INDEX_FIRST_INTERNSHIP), command);
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
