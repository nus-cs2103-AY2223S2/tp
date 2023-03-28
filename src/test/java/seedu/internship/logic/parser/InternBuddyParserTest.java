package seedu.internship.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.ClearCommand;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.EditCommand;
import seedu.internship.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.internship.logic.commands.ExitCommand;
import seedu.internship.logic.commands.FindCommand;
import seedu.internship.logic.commands.HelpCommand;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;
import seedu.internship.testutil.InternshipBuilder;
import seedu.internship.testutil.InternshipUtil;

public class InternBuddyParserTest {

    private final InternBuddyParser parser = new InternBuddyParser();

    @Test
    public void parseCommand_add() throws Exception {
        Internship internship = new InternshipBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(InternshipUtil.getAddCommand(internship));
        assertEquals(new AddCommand(internship), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Internship internship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(internship).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_INTERNSHIP.getOneBased() + " "
                + InternshipUtil.getEditInternshipDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_INTERNSHIP, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> nameKeywords = Arrays.asList("bar bar", "baz", "foo foo");
        List<String> roleKeywords = Arrays.asList("ha ha ha", "blah");
        List<String> statusKeywords = Arrays.asList("new", "rejected");
        List<String> keyDate = Arrays.asList("2023-02-02", "2023-02-01");
        List<String> tagKeywords = Arrays.asList("boo", "blah blah");
        // Note: Elements in the lists must be ordered this way as this is how HashSet orders them.
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD
                        + nameKeywords.stream()
                            .map(name -> " " + PREFIX_COMPANY_NAME + name)
                            .collect(Collectors.joining(""))
                        + roleKeywords.stream()
                            .map(role -> " " + PREFIX_ROLE + role)
                            .collect(Collectors.joining(" "))
                        + statusKeywords.stream()
                            .map(status -> " " + PREFIX_STATUS + status)
                            .collect(Collectors.joining(""))
                        + keyDate.stream()
                            .map(date -> " " + PREFIX_DATE + date)
                            .collect(Collectors.joining(""))
                        + tagKeywords.stream()
                            .map(tag -> " " + PREFIX_TAG + tag)
                            .collect(Collectors.joining("")));
        assertEquals(new FindCommand(
                new InternshipContainsKeywordsPredicate(nameKeywords, roleKeywords, statusKeywords, keyDate,
                        tagKeywords)),
                command);
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
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
