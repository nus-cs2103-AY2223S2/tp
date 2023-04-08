package expresslibrary.logic.parser;

import static expresslibrary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static expresslibrary.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

//import expresslibrary.logic.commands.AddCommand;
import expresslibrary.logic.commands.ClearCommand;
import expresslibrary.logic.commands.DeletePersonCommand;
import expresslibrary.logic.commands.EditPersonCommand;
import expresslibrary.logic.commands.EditPersonCommand.EditPersonDescriptor;
import expresslibrary.logic.commands.ExitCommand;
import expresslibrary.logic.commands.FindPersonCommand;
import expresslibrary.logic.commands.HelpCommand;
import expresslibrary.logic.commands.ListPersonCommand;
import expresslibrary.logic.parser.exceptions.ParseException;
import expresslibrary.model.person.NameContainsKeywordsPredicate;
import expresslibrary.model.person.Person;
import expresslibrary.testutil.EditPersonDescriptorBuilder;
import expresslibrary.testutil.PersonBuilder;
import expresslibrary.testutil.PersonUtil;

public class ExpressLibraryParserTest {

    private final ExpressLibraryParser parser = new ExpressLibraryParser();

    @Test
    public void parseCommand_add() throws Exception {
        // will fix later
        //Person person = new PersonBuilder().build();
        //AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        //assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST, false), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPersonCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " 3") instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
        ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
