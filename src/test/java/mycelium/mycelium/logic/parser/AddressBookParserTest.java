package mycelium.mycelium.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.AddClientCommand;
import mycelium.mycelium.logic.commands.AddCommand;
import mycelium.mycelium.logic.commands.AddProjectCommand;
import mycelium.mycelium.logic.commands.ClearCommand;
import mycelium.mycelium.logic.commands.DeleteClientCommand;
import mycelium.mycelium.logic.commands.DeleteCommand;
import mycelium.mycelium.logic.commands.DeleteProjectCommand;
import mycelium.mycelium.logic.commands.EditCommand;
import mycelium.mycelium.logic.commands.ExitCommand;
import mycelium.mycelium.logic.commands.FindCommand;
import mycelium.mycelium.logic.commands.HelpCommand;
import mycelium.mycelium.logic.commands.ListCommand;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.NameContainsKeywordsPredicate;
import mycelium.mycelium.model.person.Person;
import mycelium.mycelium.testutil.Assert;
import mycelium.mycelium.testutil.ClientBuilder;
import mycelium.mycelium.testutil.EditPersonDescriptorBuilder;
import mycelium.mycelium.testutil.PersonBuilder;
import mycelium.mycelium.testutil.PersonUtil;
import mycelium.mycelium.testutil.ProjectBuilder;
import mycelium.mycelium.testutil.TypicalIndexes;

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
            DeleteCommand.COMMAND_WORD + " " + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
            + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(
            descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor), command);
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
    public void parseCommand_createClient() throws Exception {
        String input = AddClientCommand.COMMAND_ACRONYM + " -cn Jamal -e jamal@hogriders.org";
        AddClientCommand got = (AddClientCommand) parser.parseCommand(input);
        AddClientCommand
            want =
            new AddClientCommand(new ClientBuilder().withName("Jamal").withEmail("jamal@hogriders.org").build());
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_createProject() throws Exception {
        String input = AddProjectCommand.COMMAND_ACRONYM + " -pn Bing -e jamal@hogriders.org";
        AddProjectCommand got = (AddProjectCommand) parser.parseCommand(input);
        AddProjectCommand
            want =
            new AddProjectCommand(new ProjectBuilder().withName("Bing").withClientEmail("jamal@hogriders.org").build());
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_deleteClient() throws Exception {
        String input = DeleteClientCommand.COMMAND_ACRONYM + " -e jamal@hogriders.org";
        DeleteClientCommand got = (DeleteClientCommand) parser.parseCommand(input);
        DeleteClientCommand want = new DeleteClientCommand(new Email("jamal@hogriders.org"));
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_deleteProject() throws Exception {
        String input = DeleteProjectCommand.COMMAND_ACRONYM + " -pn Bing";
        DeleteProjectCommand got = (DeleteProjectCommand) parser.parseCommand(input);
        DeleteProjectCommand want = new DeleteProjectCommand("Bing");
        assertEquals(want, got);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class,
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class,
            Messages.MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
