package seedu.internship.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internship.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.AddCommand;
import seedu.internship.logic.commands.DeleteCommand;
import seedu.internship.logic.commands.ExitCommand;
import seedu.internship.logic.commands.HelpCommand;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.Internship;
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
