package bookopedia.logic.parser;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookopedia.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static bookopedia.testutil.Assert.assertThrows;
import static bookopedia.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bookopedia.logic.commands.AddCommand;
import bookopedia.logic.commands.AddParcelCommand;
import bookopedia.logic.commands.ClearCommand;
import bookopedia.logic.commands.DeleteCommand;
import bookopedia.logic.commands.EditCommand;
import bookopedia.logic.commands.EditCommand.EditPersonDescriptor;
import bookopedia.logic.commands.ExitCommand;
import bookopedia.logic.commands.FindCommand;
import bookopedia.logic.commands.HelpCommand;
import bookopedia.logic.commands.ListCommand;
import bookopedia.logic.commands.MarkCommand;
import bookopedia.logic.commands.ViewCommand;
import bookopedia.logic.parser.exceptions.ParseException;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.parcel.Parcel;
import bookopedia.model.person.NameContainsKeywordsPredicate;
import bookopedia.model.person.Person;
import bookopedia.testutil.EditPersonDescriptorBuilder;
import bookopedia.testutil.PersonBuilder;
import bookopedia.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

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
    public void parseCommand_addParcel() throws Exception {
        AddParcelCommand command = (AddParcelCommand) parser.parseCommand(AddParcelCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + CliSyntax.PREFIX_PARCEL + "lazada");
        Parcel newParcel = new Parcel("lazada");
        assertEquals(new AddParcelCommand(INDEX_FIRST_PERSON, newParcel), command);
    }

    @Test
    public void parseCommand_mark() throws Exception {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;
        MarkCommand command = (MarkCommand) parser.parseCommand(MarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + CliSyntax.PREFIX_STATUS + deliveryStatus);
        assertEquals(new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        assertTrue(parser.parseCommand(ViewCommand.COMMAND_WORD + " 1") instanceof ViewCommand);
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
