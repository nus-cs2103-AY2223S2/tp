package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditMeetingsCommand;
import seedu.address.logic.commands.EditMeetingsCommand.EditMeetingDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewMeetingsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingContainsNamesPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.MeetingUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class QuickContactsParserTest {

    private final QuickContactsParser parser = new QuickContactsParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
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
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_findMeeting() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindMeetingCommand command = (FindMeetingCommand) parser.parseCommand(
            FindMeetingCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindMeetingCommand(new MeetingContainsNamesPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_viewMeetings() throws Exception {
        assertTrue(parser.parseCommand(ViewMeetingsCommand.COMMAND_WORD) instanceof ViewMeetingsCommand);
        assertTrue(parser.parseCommand(ViewMeetingsCommand.COMMAND_WORD + " 3") instanceof ViewMeetingsCommand);
    }

    @Test
    public void parseCommand_addMeeting() throws Exception {
        Meeting meeting = new MeetingBuilder().build();
        AddMeetingCommand command = (AddMeetingCommand) parser.parseCommand(MeetingUtil.getAddMeetingCommand(meeting));
        assertEquals(new AddMeetingCommand(meeting), command);
    }

    @Test
    public void parseCommand_editMeeting() throws Exception {
        Meeting meeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(meeting).build();
        EditMeetingsCommand command = (EditMeetingsCommand) parser.parseCommand(
                EditMeetingsCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + MeetingUtil.getEditMeetingDescriptorDetails(descriptor));
        assertEquals(new EditMeetingsCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_deleteMeeting() throws Exception {
        DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
                DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteMeetingCommand(INDEX_FIRST_PERSON), command);
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
