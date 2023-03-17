package seedu.vms.logic.parser.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.vms.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.basic.HelpCommand;
import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.logic.commands.patient.ClearCommand;
import seedu.vms.logic.commands.patient.DeleteCommand;
import seedu.vms.logic.commands.patient.EditCommand;
import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.logic.commands.patient.FindCommand;
import seedu.vms.logic.commands.patient.ListCommand;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.patient.NameContainsKeywordsPredicate;
import seedu.vms.model.patient.Patient;
import seedu.vms.testutil.EditPatientDescriptorBuilder;
import seedu.vms.testutil.PatientBuilder;
import seedu.vms.testutil.PatientUtil;

public class PatientParserTest {

    private final PatientParser parser = new PatientParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddCommand command = (AddCommand) parser.parse(PatientUtil.getAddCommand(patient)).getCommand();
        assertEquals(new AddCommand(patient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parse(ClearCommand.COMMAND_WORD).getCommand() instanceof ClearCommand);
        assertTrue(parser.parse(ClearCommand.COMMAND_WORD + " 3").getCommand() instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parse(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased()).getCommand();
        assertEquals(new DeleteCommand(INDEX_FIRST_PATIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditCommand command = (EditCommand) parser.parse(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PATIENT.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor))
                .getCommand();
        assertEquals(new EditCommand(INDEX_FIRST_PATIENT, descriptor), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parse(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")))
                .getCommand();
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parse(ListCommand.COMMAND_WORD).getCommand() instanceof ListCommand);
        assertTrue(parser.parse(ListCommand.COMMAND_WORD + " 3").getCommand() instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parse("unknownCommand"));
    }
}
