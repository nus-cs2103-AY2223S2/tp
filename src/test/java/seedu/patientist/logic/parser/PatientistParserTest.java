package seedu.patientist.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.patientist.testutil.Assert.assertThrows;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.AddCommand;
import seedu.patientist.logic.commands.ClearCommand;
import seedu.patientist.logic.commands.DeleteCommand;
import seedu.patientist.logic.commands.EditCommand;
import seedu.patientist.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.patientist.logic.commands.ExitCommand;
import seedu.patientist.logic.commands.FindCommand;
import seedu.patientist.logic.commands.HelpCommand;
import seedu.patientist.logic.commands.ListCommand;
import seedu.patientist.logic.parser.exceptions.ParseException;
import seedu.patientist.model.person.NameContainsKeywordsPredicate;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.testutil.EditPersonDescriptorBuilder;
import seedu.patientist.testutil.PatientBuilder;
import seedu.patientist.testutil.PatientUtil;

public class PatientistParserTest {

    private final PatientistParser parser = new PatientistParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new AddCommand(patient), command);
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
        Patient patient = new PatientBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(patient).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
