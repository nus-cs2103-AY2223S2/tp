package seedu.event.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.event.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.event.testutil.Assert.assertThrows;
import static seedu.event.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.event.testutil.TypicalTimes.CLOCK_FIXED_AT_TIME_NOW;
import static seedu.event.testutil.TypicalTimes.TYPICAL_DAYS;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.event.logic.commands.AddCommand;
import seedu.event.logic.commands.ClearCommand;
import seedu.event.logic.commands.DeleteCommand;
import seedu.event.logic.commands.EditCommand;
import seedu.event.logic.commands.EditCommand.EditEventDescriptor;
import seedu.event.logic.commands.ExitCommand;
import seedu.event.logic.commands.FindCommand;
import seedu.event.logic.commands.HelpCommand;
import seedu.event.logic.commands.LinkContactCommand;
import seedu.event.logic.commands.ListCommand;
import seedu.event.logic.commands.MarkCommand;
import seedu.event.logic.commands.NewContactCommand;
import seedu.event.logic.commands.RemindCommand;
import seedu.event.logic.commands.RevenueCommand;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.contact.Contact;
import seedu.event.model.event.Event;
import seedu.event.model.event.NameContainsKeywordsPredicate;
import seedu.event.model.event.StartTimeWithinDaysPredicate;
import seedu.event.testutil.ContactBuilder;
import seedu.event.testutil.ContactUtil;
import seedu.event.testutil.EditEventDescriptorBuilder;
import seedu.event.testutil.EventBuilder;
import seedu.event.testutil.EventUtil;

public class EventBookParserTest {

    private final EventBookParser parser = new EventBookParser(CLOCK_FIXED_AT_TIME_NOW);

    @Test
    public void parseCommand_add() throws Exception {
        Event event = new EventBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EventUtil.getAddCommand(event));
        assertEquals(new AddCommand(event), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_EVENT), command);
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
    public void parseCommand_newContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        NewContactCommand command = (NewContactCommand) parser.parseCommand(ContactUtil.getNewContactCommand(contact));
        assertEquals(new NewContactCommand(contact), command);
    }

    @Test
    public void parseCommand_remind() throws Exception {
        LocalDateTime nowTime = LocalDateTime.now();

        RemindCommand command = (RemindCommand) parser.parseCommand(
                RemindCommand.COMMAND_WORD + " " + TYPICAL_DAYS);
        assertEquals(
                new RemindCommand(new StartTimeWithinDaysPredicate(CLOCK_FIXED_AT_TIME_NOW, TYPICAL_DAYS)), command);
    }

    @Test
    public void parseCommand_mark() throws Exception {
        MarkCommand command = (MarkCommand) parser.parseCommand(
            MarkCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());
        assertEquals(new MarkCommand(INDEX_FIRST_EVENT), command);
    }

    @Test
    public void parseCommand_linkContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        LinkContactCommand command = (LinkContactCommand) parser.parseCommand(
                LinkContactCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased() + " "
                        + contact.getPhone().value);
        assertEquals(new LinkContactCommand(INDEX_FIRST_EVENT, contact.getPhone()), command);
    }

    @Test
    public void parseCommand_revenue() throws Exception {
        assertTrue(parser.parseCommand(RevenueCommand.COMMAND_WORD) instanceof RevenueCommand);
        assertTrue(parser.parseCommand(RevenueCommand.COMMAND_WORD + " 3") instanceof RevenueCommand);
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
