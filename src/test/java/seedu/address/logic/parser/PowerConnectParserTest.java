package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.logic.commands.student.StudentDeleteCommand;
import seedu.address.logic.commands.student.StudentListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Class;
import seedu.address.model.person.student.ClassContainsKeywordsPredicate;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;

public class PowerConnectParserTest {

    private static final IndexNumber TEST_INDEX = new IndexNumber("1");
    private static final Class TEST_CLASS = Class.of("1A");

    private final PowerConnectParser parser = new PowerConnectParser();

    @Test
    public void parseStudentCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        StudentAddCommand command = (StudentAddCommand) parser.parseCommand(StudentUtil.getStudentAddCommand(student));
        assertEquals(new StudentAddCommand(student), command);
    }

    @Test
    public void parseStudentCommand_delete() throws Exception {
        StudentDeleteCommand command = (StudentDeleteCommand) parser.parseCommand(
                "student"
                + " " + TEST_CLASS
                + " " + StudentDeleteCommand.COMMAND_WORD
                + " " + PREFIX_INDEXNUMBER
                + TEST_INDEX);
        assertEquals(new StudentDeleteCommand(TEST_INDEX, TEST_CLASS), command);
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
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " parent") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " student") instanceof ListCommand);
    }

    @Test
    public void parseStudentCommand_list() throws Exception {
        StudentListCommand command = (StudentListCommand) parser.parseCommand(
                "student"
                        + " " + TEST_CLASS
                        + " " + StudentListCommand.COMMAND_WORD);
        assertEquals(new StudentListCommand(TEST_CLASS, new ClassContainsKeywordsPredicate(TEST_CLASS)), command);
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
