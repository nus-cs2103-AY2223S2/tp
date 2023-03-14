package codoc.logic.parser;

import static codoc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static codoc.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static codoc.testutil.Assert.assertThrows;
import static codoc.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import codoc.logic.commands.AddCommand;
import codoc.logic.commands.ClearCommand;
import codoc.logic.commands.DeleteCommand;
import codoc.logic.commands.EditCommand;
import codoc.logic.commands.ExitCommand;
import codoc.logic.commands.FindCommand;
import codoc.logic.commands.HelpCommand;
import codoc.logic.commands.ListCommand;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.NameContainsKeywordsPredicate;
import codoc.model.person.Person;
import codoc.testutil.EditPersonDescriptorBuilder;
import codoc.testutil.PersonBuilder;
import codoc.testutil.PersonUtil;

public class CodocParserTest {

    private final CodocParser parser = new CodocParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person), person);
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        Person person = new PersonBuilder().build();
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, person) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", person) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Person person = new PersonBuilder().build();
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), person);
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person originalPerson = new PersonBuilder().build();
        Person editedPerson = new PersonBuilder().buildEditedPerson();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor), originalPerson);
        assertEquals(new EditCommand(descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        Person person = new PersonBuilder().build();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, person) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", person) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        Person person = new PersonBuilder().build();
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")), person);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        Person person = new PersonBuilder().build();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, person) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", person) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        Person person = new PersonBuilder().build();
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, person) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", person) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Person person = new PersonBuilder().build();
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", person));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Person person = new PersonBuilder().build();
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand", person));
    }
}
