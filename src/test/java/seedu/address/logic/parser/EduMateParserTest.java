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
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class EduMateParserTest {

    private final EduMateParser parser = new EduMateParser();

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
        String commandString = EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor);
        EditCommand command = (EditCommand) parser.parseCommand(commandString);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        createParseCommandFind(
                Prefix.NAME,
                Arrays.asList("n/Richard", "Komyo", "Eusoff"),
                Arrays.asList("Richard", "Komyo", "Eusoff")
        );
        createParseCommandFind(
                Prefix.PHONE,
                Arrays.asList("p/89760441", "92752656", "82630347"),
                Arrays.asList("89760441", "92752656", "82630347")
        );
        createParseCommandFind(
                Prefix.ADDRESS,
                Arrays.asList("a/91", "Ang", "Mo", "Kio", "Avenue", "4"),
                Arrays.asList("91", "Ang", "Mo", "Kio", "Avenue", "4")
        );
        createParseCommandFind(
                Prefix.EMAIL,
                Arrays.asList("e/angmeihua@gmail.com", "albertpark@gmail.com", "bartlee@gmail.com"),
                Arrays.asList("angmeihua@gmail.com", "albertpark@gmail.com", "bartlee@gmail.com")
        );
        createParseCommandFind(
                Prefix.TELEGRAM_HANDLE,
                Arrays.asList("t/@albertpark", "@angmeihua", "@bartlee"),
                Arrays.asList("@albertpark", "@angmeihua", "@bartlee")
        );
        createParseCommandFind(
                Prefix.GROUP_TAG,
                Arrays.asList("g/CCA", "TA", "Study"),
                Arrays.asList("CCA", "TA", "Study")
        );
        createParseCommandFind(
                Prefix.MODULE_TAG,
                Arrays.asList("m/CS3233", "CS3245", "CS3211"),
                Arrays.asList("CS3233", "CS3245", "CS3211")
        );
    }

    public void createParseCommandFind(
            Prefix prefix, List<String> keywords, List<String> keywordsWithoutPrefix) throws Exception {
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(
                new FindCommand(
                        new ContainsKeywordsPredicate(keywordsWithoutPrefix, prefix)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
                ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
