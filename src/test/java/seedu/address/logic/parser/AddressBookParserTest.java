package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISOEVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddIsolatedEventCommand;
import seedu.address.logic.commands.AddRecurringEventCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteIsolatedEventCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditIsolatedEventCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.group.GroupCreateCommand;
import seedu.address.logic.commands.group.GroupDeleteCommand;
import seedu.address.logic.commands.group.GroupFindCommand;
import seedu.address.logic.commands.group.GroupListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.MemberOfGroupPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

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
    public void parseCommand_isolatedEvent() throws Exception {
        AddIsolatedEventCommand command = (AddIsolatedEventCommand) parser.parseCommand(
                AddIsolatedEventCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + "ie/biking" + " " + "f/09/03/2023 14:00" + " " + "t/09/03/2023 15:00");
        assertTrue(command.COMMAND_WORD == "event_create");
    }

    @Test
    public void parseCommand_recurringEvent() throws Exception {
        AddRecurringEventCommand command = (AddRecurringEventCommand) parser.parseCommand(
                AddRecurringEventCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + "re/biking" + " " + "d/MONDAY" + " " + "f/14:00" + " " + "t/15:00");
        assertTrue(command.COMMAND_WORD == "event_create_recur");
    }
    @Test
    public void parseCommand_deleteIsolatedEvent() throws Exception {
        DeleteIsolatedEventCommand command = (DeleteIsolatedEventCommand) parser.parseCommand(
                DeleteIsolatedEventCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + INDEX_FIRST_PERSON.getOneBased());
        assertTrue(command.COMMAND_WORD == "ie_delete");

    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor, true), command);
    }

    @Test
    public void parseCommand_editIsolatedEvent() throws Exception {
        EditIsolatedEventCommand command = (EditIsolatedEventCommand) parser.parseCommand(
                EditIsolatedEventCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_ISOEVENT + "biking");
        assertTrue(command.COMMAND_WORD == "ie_edit");
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
    public void parseCommand_groupCreate() throws Exception {
        GroupCreateCommand command = (GroupCreateCommand) parser.parseCommand(GroupCreateCommand.COMMAND_WORD
                + " g/2103");
        assertEquals(new GroupCreateCommand(new Group("2103")), command);
    }

    @Test
    public void parseCommand_groupFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        GroupFindCommand command = (GroupFindCommand) parser.parseCommand(GroupFindCommand.COMMAND_WORD
                + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new GroupFindCommand(new GroupNameContainsKeywordsPredicate(keywords),
                new MemberOfGroupPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_groupDelete() throws Exception {
        GroupDeleteCommand command = (GroupDeleteCommand) parser.parseCommand(
                GroupDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new GroupDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_groupList() throws Exception {
        GroupListCommand command = (GroupListCommand) parser.parseCommand(GroupListCommand.COMMAND_WORD);
        assertEquals(new GroupListCommand(), command);
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
